package com.demo.document.enums;

/**
 * @ClassName OrderStatusEnum
 * @Date 2022/10/29 10:38
 * @Version 1.0
 */
public enum OrderStatusEnum {
    UNPAY(1, "未付款"),
    PAYING(2, "付款中"),
    PAID(3, "已付款"),
    REFUND(4, "已退款");

    private Integer type;

    private String desc;

    OrderStatusEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

}
