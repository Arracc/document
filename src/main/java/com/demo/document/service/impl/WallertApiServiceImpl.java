package com.demo.document.service.impl;

import com.demo.document.dto.BalanceChangeDetailDTO;
import com.demo.document.dto.OrderDTO;
import com.demo.document.dto.WalletDTO;
import com.demo.document.enums.ChangeTypeEnum;
import com.demo.document.enums.OrderStatusEnum;
import com.demo.document.exception.BussinessException;
import com.demo.document.service.OrderService;
import com.demo.document.service.WalletApiService;
import com.demo.document.service.BalanceChangeDetailService;
import com.demo.document.service.WalletService;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName WallertApiServiceImpl
 * @Date 2022/10/29 0:12
 * @Version 1.0
 */
@Service
public class WallertApiServiceImpl implements WalletApiService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private BalanceChangeDetailService balanceChangeDetailService;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 支付订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean pay(Long userId, Long orderId) throws BussinessException {
        if (userId == null || orderId == null) {
            throw new BussinessException("用户id或订单id不能为空");
        }

        // 获取订单锁 钱包锁
        RLock orderFairLock = redissonClient.getFairLock("order:orderId:" + orderId);
        RLock walletFairLock = redissonClient.getFairLock("wallet:userId:" + userId);
        boolean isOrderLocked = false;
        boolean isWalletLocked = false;
        try {
            isOrderLocked = walletFairLock.tryLock(5, 5, TimeUnit.SECONDS);
            isWalletLocked = walletFairLock.tryLock(5, 5, TimeUnit.SECONDS);
            // 没拿到锁 退出支付
            if (!isOrderLocked || !isWalletLocked) {
                throw new BussinessException("订单正在处理中或账户中有其他交易正在处理中，暂时无法支付");
            }

            // 查询订单信息
            OrderDTO orderDTO = orderService.queryOrderById(orderId);
            if (orderDTO == null || !Objects.equals(orderDTO.getUserId(), userId) ||
                    !Objects.equals(orderDTO.getStatus(), OrderStatusEnum.UNPAY.getType())) {
                throw new BussinessException("订单信息或状态异常");
            }
            Long price = orderDTO.getPrice();

            // 查询钱包
            WalletDTO walletDTO = walletService.queryWalletDTOByUserId(userId);
            if (walletDTO == null) {
                throw new BussinessException("未查询到用户钱包信息");
            }
            Long walletId = walletDTO.getId();

            // 校验余额是否足够支付
            long afterPay = walletDTO.getBalance() - price;
            if (afterPay < 0) {
                throw new BussinessException("钱包余额不足");
            }

            // 新增金额变更明细
            BalanceChangeDetailDTO detailDTO = new BalanceChangeDetailDTO();
            detailDTO.setWalletId(walletId);
            detailDTO.setOrderId(orderId);
            detailDTO.setChangeAmount(-price);
            detailDTO.setAfterChangeAmount(afterPay);
            detailDTO.setChangeType(ChangeTypeEnum.CONSUME.getType());
            Date date = new Date();
            detailDTO.setUpdateTime(date);
            detailDTO.setCreateTime(date);
            Boolean saveDetailResult = balanceChangeDetailService.addChangeDetail(detailDTO);
            if (Boolean.FALSE.equals(saveDetailResult)) {
                // 记录日志

                throw new BussinessException("扣减金额失败");
            }

            // 修改钱包金额
            WalletDTO walletUpdateDTO = new WalletDTO();
            walletUpdateDTO.setId(walletId);
            walletUpdateDTO.setBalance(afterPay);
            Boolean updateWalletResult = walletService.updateBlanceById(walletUpdateDTO);
            if (Boolean.FALSE.equals(updateWalletResult)) {
                // 记录日志

                throw new BussinessException("扣减金额失败");
            }

            // 修改订单状态
            OrderDTO orderUpdateDTO = new OrderDTO();
            orderUpdateDTO.setId(orderId);
            orderUpdateDTO.setStatus(OrderStatusEnum.PAID.getType());
            Boolean orderUpdateResult = orderService.updateOrderById(orderUpdateDTO);
            if (Boolean.FALSE.equals(orderUpdateResult)) {
                // 记录日志

                throw new BussinessException("修改订单状态失败");
            }

        } catch (Exception e) {
            // 记录日志

            throw new BussinessException("支付失败，请稍后重试，失败原因:" + e.getMessage());
        } finally {
            if (isOrderLocked) {
                orderFairLock.unlock();
            }
            if (isWalletLocked) {
                walletFairLock.unlock();
            }
        }

        return true;
    }


    /**
     * 订单退款
     *
     * @param userId
     * @param orderId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean refund(Long userId, Long orderId) throws BussinessException {
        if (userId == null || orderId == null) {
            throw new BussinessException("用户id或订单id不能为空");
        }

        // 获取订单锁 钱包锁
        RLock orderFairLock = redissonClient.getFairLock("order:orderId:" + orderId);
        RLock walletFairLock = redissonClient.getFairLock("wallet:userId:" + userId);
        boolean isOrderLocked = false;
        boolean isWalletLocked = false;
        try {
            isOrderLocked = walletFairLock.tryLock(5, 5, TimeUnit.SECONDS);
            isWalletLocked = walletFairLock.tryLock(5, 5, TimeUnit.SECONDS);
            // 没拿到锁 退出退款
            if (!isOrderLocked || !isWalletLocked) {
                throw new BussinessException("订单正在处理中或账户中有其他交易正在处理中，暂时无法处理当前退款请求");
            }

            // 查询订单信息
            OrderDTO orderDTO = orderService.queryOrderById(orderId);
            if (orderDTO == null || !Objects.equals(orderDTO.getUserId(), userId) ||
                    !Objects.equals(orderDTO.getStatus(), OrderStatusEnum.PAID.getType())) {
                throw new BussinessException("订单信息或状态异常");
            }
            Long price = orderDTO.getPrice();

            // 查询钱包
            WalletDTO walletDTO = walletService.queryWalletDTOByUserId(userId);
            if (walletDTO == null) {
                throw new BussinessException("未查询到用户钱包信息");
            }
            Long walletId = walletDTO.getId();
            long balanceAfterRefund = walletDTO.getBalance() + price;

            // 新增金额变更明细
            BalanceChangeDetailDTO detailDTO = new BalanceChangeDetailDTO();
            detailDTO.setWalletId(walletId);
            detailDTO.setOrderId(orderId);
            detailDTO.setChangeAmount(price);
            detailDTO.setAfterChangeAmount(balanceAfterRefund);
            detailDTO.setChangeType(ChangeTypeEnum.REFUND.getType());
            Date date = new Date();
            detailDTO.setUpdateTime(date);
            detailDTO.setCreateTime(date);
            Boolean saveDetailResult = balanceChangeDetailService.addChangeDetail(detailDTO);
            if (Boolean.FALSE.equals(saveDetailResult)) {
                // 记录日志

                throw new BussinessException("退款失败");
            }

            // 修改钱包金额
            WalletDTO walletUpdateDTO = new WalletDTO();
            walletUpdateDTO.setId(walletId);
            walletUpdateDTO.setBalance(balanceAfterRefund);
            Boolean updateWalletResult = walletService.updateBlanceById(walletUpdateDTO);
            if (Boolean.FALSE.equals(updateWalletResult)) {
                // 记录日志

                throw new BussinessException("退款失败");
            }

            // 修改订单状态
            OrderDTO orderUpdateDTO = new OrderDTO();
            orderUpdateDTO.setId(orderId);
            orderUpdateDTO.setStatus(OrderStatusEnum.REFUND.getType());
            Boolean orderUpdateResult = orderService.updateOrderById(orderUpdateDTO);
            if (Boolean.FALSE.equals(orderUpdateResult)) {
                // 记录日志

                throw new BussinessException("修改订单状态失败");
            }

        } catch (Exception e) {
            // 记录日志

            throw new BussinessException("退款失败，请稍后重试，失败原因:" + e.getMessage());
        } finally {
            if (isOrderLocked) {
                orderFairLock.unlock();
            }
            if (isWalletLocked) {
                walletFairLock.unlock();
            }
        }

        return true;
    }


    /**
     * 根据用户id查询用户钱包金额变更明细列表
     *
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BussinessException
     */
    @Override
    public List<BalanceChangeDetailDTO> queryBalanceChangeDetailList(Long userId, Integer pageIndex, Integer pageSize)
            throws BussinessException {
        if(userId == null){
            throw new BussinessException("用户id不能为空");
        }

        // 获取钱包
        WalletDTO walletDTO = walletService.queryWalletDTOByUserId(userId);
        if (walletDTO == null) {
            throw new BussinessException("未查询到用户钱包信息");
        }

        // 查询钱包余额变更明细
        return balanceChangeDetailService
                .queryChangeDetailPagedListByWalletId(walletDTO.getId(), pageIndex, pageSize);
    }


}
