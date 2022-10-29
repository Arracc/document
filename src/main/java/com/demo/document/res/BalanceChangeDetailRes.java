package com.demo.document.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BalanceChangeDetailRes
 * @Date 2022/10/29 11:21
 * @Version 1.0
 */
public class BalanceChangeDetailRes implements Serializable {

    private static final long serialVersionUID = -66205931142233683L;

    /**
     * 变更金额
     */
    private String changeAmount;

    /**
     * 变更后金额
     */
    private String afterChangeAmount;

    /**
     * 变更类型 1:充值 2:提现 3:消费 4:退款
     */
    private Integer changeType;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 变更时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public String getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(String changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getAfterChangeAmount() {
        return afterChangeAmount;
    }

    public void setAfterChangeAmount(String afterChangeAmount) {
        this.afterChangeAmount = afterChangeAmount;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
