package com.demo.document.util;

import com.demo.document.dto.UserDTO;

/**
 * @ClassName AuthUtil
 * @Date 2022/10/28 22:12
 * @Version 1.0
 */
public class AuthUtil {

    /**
     * 获取用户信息
     *
     * @return UserDTO
     */
    public static UserDTO getUserInfo() {
        try {
            return new UserDTO();
        } catch (Exception e) {
            // 记录日志
            return null;
        }
    }

}
