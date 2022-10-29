package com.demo.document.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName WalletDTO
 * @Date 2022/10/28 22:24
 * @Version 1.0
 */
public class WalletDTO implements Serializable {

    private static final long serialVersionUID = -1275261922405325484L;

    /**
     * 钱包id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 钱包余额
     */
   private Long balance;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
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
