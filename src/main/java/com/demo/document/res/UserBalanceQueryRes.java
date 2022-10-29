package com.demo.document.res;

import java.io.Serializable;

/**
 * @ClassName UserWalletBalanceQueryRes
 * @Date 2022/10/28 22:35
 * @Version 1.0
 */
public class UserBalanceQueryRes implements Serializable {

    private static final long serialVersionUID = 2012924448469226775L;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户钱包余额
     */
    private String balance;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
