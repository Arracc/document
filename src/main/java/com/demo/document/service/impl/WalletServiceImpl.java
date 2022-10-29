package com.demo.document.service.impl;

import com.demo.document.dto.WalletDTO;
import com.demo.document.service.WalletService;
import org.springframework.stereotype.Service;

/**
 * @ClassName WalletServiceImpl
 * @Date 2022/10/28 22:23
 * @Version 1.0
 */
@Service
public class WalletServiceImpl implements WalletService {

    /**
     * 根据用户id获取钱包DTO
     *
     * @param userId
     * @return
     */
    @Override
    public WalletDTO queryWalletDTOByUserId(Long userId){
        return new WalletDTO();
    }

    /**
     * 根据主键id更新钱包余额
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean updateBlanceById(WalletDTO dto){
        return true;
    }

}
