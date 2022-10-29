package com.demo.document.service.impl;

import com.demo.document.dto.UserDTO;
import com.demo.document.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Date 2022/10/28 22:05
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDTO getUserDTO(){
        return new UserDTO();
    }

}
