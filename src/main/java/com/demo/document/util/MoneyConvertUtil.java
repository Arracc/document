package com.demo.document.util;

import java.math.BigDecimal;
import org.springframework.util.StringUtils;

/**
 * @ClassName MoneyConvertUtil
 * @Date 2022/10/28 22:38
 * @Version 1.0
 */
public class MoneyConvertUtil {

    /**
     * 把金额单位从分转为元
     *
     * @param moneyAmount
     * @return
     */
    public static BigDecimal convertFromCentToYuan(Long moneyAmount) {
        try {
            if (moneyAmount == null) {
                return null;
            }
            return new BigDecimal(String.valueOf(moneyAmount)).divide(new BigDecimal("100"), 2);
        } catch (Exception e) {
            // 记录日志

            return null;
        }
    }


    /**
     * 把金额单位从分转为元 取绝对值
     *
     * @param moneyAmount
     * @return
     */
    public static BigDecimal convertFromCentToYuanAbs(Long moneyAmount) {
        try {
            if (moneyAmount == null) {
                return null;
            }
            return new BigDecimal(String.valueOf(moneyAmount)).divide(new BigDecimal("100"), 2).abs();
        } catch (Exception e) {
            // 记录日志

            return null;
        }
    }


    /**
     * 把以元为单位的字符串金额转为以分为单位的ong金额
     *
     * @param moneyAmount
     * @return
     */
    public static Long convertFromYuanStrToCent(String moneyAmount) {
        try {
            if (!StringUtils.hasText(moneyAmount)) {
                return null;
            }
            return new BigDecimal(moneyAmount).longValue();
        } catch (Exception e) {
            // 记录日志

            return null;
        }
    }
}
