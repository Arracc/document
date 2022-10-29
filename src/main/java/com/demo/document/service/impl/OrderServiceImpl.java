package com.demo.document.service.impl;

import com.demo.document.dto.OrderDTO;
import com.demo.document.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Date 2022/10/29 9:50
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 根据订单id查询订单信息
     *
     * @param id
     * @return
     */
    @Override
    public OrderDTO queryOrderById(Long id){
        return new OrderDTO();
    }


    /**
     * 根据订单id更新订单信息
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean updateOrderById(OrderDTO dto){
        return true;
    }

}
