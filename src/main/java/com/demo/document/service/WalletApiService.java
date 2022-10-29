package com.demo.document.service;

import com.demo.document.dto.BalanceChangeDetailDTO;
import com.demo.document.exception.BussinessException;
import java.util.List;

/**
 * @ClassName WalletApiService
 * @Date 2022/10/29 0:12
 * @Version 1.0
 */
public interface WalletApiService {

    /**
     * 支付订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    boolean pay(Long userId, Long orderId) throws BussinessException;


    /**
     * 订单退款
     *
     * @param userId
     * @param orderId
     * @return
     */
    boolean refund(Long userId, Long orderId) throws BussinessException;


    /**
     * 根据用户id查询用户钱包金额变更明细列表
     *
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws BussinessException
     */
    List<BalanceChangeDetailDTO> queryBalanceChangeDetailList(Long userId, Integer pageIndex, Integer pageSize)
            throws BussinessException;

}
