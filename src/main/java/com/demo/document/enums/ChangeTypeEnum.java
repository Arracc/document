package com.demo.document.enums;

/**
 * @ClassName ChangeTypeEnum
 * @Date 2022/10/29 0:28
 * @Version 1.0
 */
public enum ChangeTypeEnum {
    RECHARGE(1,"充值"),
    WITHDRAW(2,"提现"),
    CONSUME(3,"消费"),
    REFUND(4, "退款");

    private Integer type;

    private String desc;

    ChangeTypeEnum(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public Integer getType(){
        return this.type;
    }

    public String getDesc(){
        return this.desc;
    }

}
