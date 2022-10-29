package com.demo.document.req;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * @ClassName RefundReq
 * @Date 2022/10/29 11:05
 * @Version 1.0
 */
public class RefundReq implements Serializable {

    private static final long serialVersionUID = -5572334890621433323L;

    /**
     * 订单id
     */
    @NotNull(message = "订单id不能为空")
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}
