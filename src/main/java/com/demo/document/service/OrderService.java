package com.demo.document.service;

import com.demo.document.dto.OrderDTO;

/**
 * @ClassName OrderService
 * @Date 2022/10/29 9:49
 * @Version 1.0
 */
public interface OrderService {

    /**
     * 根据订单id查询订单信息
     *
     * @param id
     * @return
     */
    OrderDTO queryOrderById(Long id);


    /**
     * 根据订单id更新订单信息
     *
     * @param dto
     * @return
     */
    Boolean updateOrderById(OrderDTO dto);

}
