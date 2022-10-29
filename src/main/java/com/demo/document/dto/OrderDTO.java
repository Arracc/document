package com.demo.document.dto;

import java.io.Serializable;

/**
 * @ClassName OrderDTO
 * @Date 2022/10/29 9:50
 * @Version 1.0
 */
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = -8628298023561743012L;

    /**
     * 订单id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单总价
     */
    private Long price;

    /**
     * 订单状态
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
