package com.demo.document.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName WalletChangeDetailDTO
 * @Date 2022/10/28 22:26
 * @Version 1.0
 */
public class BalanceChangeDetailDTO implements Serializable {

    private static final long serialVersionUID = 6163665263812902320L;

    /**
     * 钱包金额变更明细id
     */
    private Long id;

    /**
     * 钱包id
     */
    private Long walletId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 金额变更数量
     */
    private Long changeAmount;

    /**
     * 金额变更后数量
     */
    private Long afterChangeAmount;

    /**
     * 变更类型 1:充值 2:消费 3:退款
     */
    private Integer changeType;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(Long changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Long getAfterChangeAmount() {
        return afterChangeAmount;
    }

    public void setAfterChangeAmount(Long afterChangeAmount) {
        this.afterChangeAmount = afterChangeAmount;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
