package com.demo.document.req;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName PayReq
 * @Date 2022/10/29 0:06
 * @Version 1.0
 */
public class PayReq implements Serializable {

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
