package com.demo.document.controller;

import com.demo.document.dto.BalanceChangeDetailDTO;
import com.demo.document.dto.OrderDTO;
import com.demo.document.dto.UserDTO;
import com.demo.document.dto.WalletDTO;
import com.demo.document.entiry.Result;
import com.demo.document.enums.OrderStatusEnum;
import com.demo.document.exception.BussinessException;
import com.demo.document.req.BalanceChangeDetailListQueryReq;
import com.demo.document.req.PayReq;
import com.demo.document.req.RefundReq;
import com.demo.document.res.BalanceChangeDetailRes;
import com.demo.document.res.UserBalanceQueryRes;
import com.demo.document.service.OrderService;
import com.demo.document.service.WalletApiService;
import com.demo.document.service.WalletService;
import com.demo.document.util.AuthUtil;
import com.demo.document.util.MoneyConvertUtil;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName WalletController
 * @Date 2022/10/28 22:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WalletApiService walletApiService;

    /**
     * 查询用户钱包余额
     *
     * @return
     */
    @RequestMapping("/queryUserBalance")
    public Result<UserBalanceQueryRes> queryUserBalance() {
        // 鉴权 获取请求所属用户信息
        UserDTO userInfo = AuthUtil.getUserInfo();
        if (userInfo == null) {
            return Result.fail("用户不存在");
        }

        // 获取钱包
        WalletDTO walletDTO = walletService.queryWalletDTOByUserId(userInfo.getId());
        if (walletDTO == null) {
            return Result.fail("未查询到用户钱包信息");
        }

        // 余额单位转换
        BigDecimal balance = MoneyConvertUtil.convertFromCentToYuan(walletDTO.getBalance());
        if (balance == null) {
            return Result.fail("查询用户钱包信息失败");
        }

        // 封装返回信息
        UserBalanceQueryRes res = new UserBalanceQueryRes();
        res.setUserName(userInfo.getUserName());
        res.setBalance(balance.toString());

        return Result.success(res);
    }


    /**
     * 付款
     *
     * @param req
     * @return
     */
    @RequestMapping("/pay")
    public Result<Boolean> pay(@RequestBody @Valid PayReq req) {
        // 鉴权 获取请求所属用户信息
        UserDTO userInfo = AuthUtil.getUserInfo();
        if (userInfo == null) {
            return Result.fail("用户不存在");
        }
        Long userId = userInfo.getId();

        // 查询订单信息
        Long orderId = req.getOrderId();
        OrderDTO orderDTO = orderService.queryOrderById(orderId);
        if (orderDTO == null || !Objects.equals(orderDTO.getUserId(), userId)) {
            return Result.fail("非法请求");
        }

        if (!Objects.equals(orderDTO.getStatus(), OrderStatusEnum.UNPAY.getType())) {
            return Result.fail("订单已支付或暂时无法支付");
        }

        // 付款
        Boolean payResult;
        try {
            payResult = walletApiService.pay(userId, orderId);
        } catch (BussinessException e) {
            return Result.fail(e.getMessage());
        }

        if (Boolean.FALSE.equals(payResult)) {
            return Result.success(false);
        }

        return Result.success(true);
    }


    /**
     * 退款
     *
     * @param req
     * @return
     */
    @RequestMapping("/refund")
    public Result<Boolean> refund(@RequestBody @Valid RefundReq req) {
        // 鉴权 获取请求所属用户信息
        UserDTO userInfo = AuthUtil.getUserInfo();
        if (userInfo == null) {
            return Result.fail("用户不存在");
        }
        Long userId = userInfo.getId();

        // 查询订单信息
        Long orderId = req.getOrderId();
        OrderDTO orderDTO = orderService.queryOrderById(orderId);
        if (orderDTO == null || !Objects.equals(orderDTO.getUserId(), userId)) {
            return Result.fail("非法请求");
        }

        if (!Objects.equals(orderDTO.getStatus(), OrderStatusEnum.PAID.getType())) {
            return Result.fail("订单未支付，无法申请退款");
        }

        // 退款
        Boolean refundResult;
        try {
            refundResult = walletApiService.pay(userId, orderId);
        } catch (BussinessException e) {
            return Result.fail(e.getMessage());
        }

        if (Boolean.FALSE.equals(refundResult)) {
            return Result.success(false);
        }

        return Result.success(true);
    }


    /**
     * 分页查询钱包余额变更明细列表
     *
     * @param req
     * @return
     */
    @RequestMapping("/queryBalanceChangeDetailList")
    public Result<List<BalanceChangeDetailRes>> queryBalanceChangeDetailList(
            @RequestBody BalanceChangeDetailListQueryReq req) {
        // 鉴权 获取请求所属用户信息
        UserDTO userInfo = AuthUtil.getUserInfo();
        if (userInfo == null) {
            return Result.fail("用户不存在");
        }
        Long userId = userInfo.getId();

        Integer pageIndex = req.getPageIndex();
        Integer pageSize = req.getPageSize();
        if (pageIndex == null || pageIndex <= 0) {
            pageSize = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 20;
        }

        // 分页查询变更明细列表
        List<BalanceChangeDetailDTO> detailList;
        try {
            detailList = walletApiService
                    .queryBalanceChangeDetailList(userId, pageIndex, pageSize);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }

        if (CollectionUtils.isEmpty(detailList)) {
            return Result.success(Collections.emptyList());
        }

        // 封装返回结果
        List<BalanceChangeDetailRes> resList = detailList.stream()
                .map(o -> {
                    BalanceChangeDetailRes res = new BalanceChangeDetailRes();
                    BigDecimal changeAmount = MoneyConvertUtil
                            .convertFromCentToYuanAbs(o.getChangeAmount());
                    res.setChangeAmount(changeAmount != null ? changeAmount.toString() : "");
                    BigDecimal afterChangeAmount = MoneyConvertUtil
                            .convertFromCentToYuan(o.getAfterChangeAmount());
                    res.setAfterChangeAmount(
                            afterChangeAmount != null ? afterChangeAmount.toString() : "");
                    res.setChangeType(o.getChangeType());
                    res.setOrderId(o.getOrderId());
                    res.setCreateTime(o.getCreateTime());
                    return res;
                })
                .collect(Collectors.toList());

        return Result.success(resList);
    }

}
