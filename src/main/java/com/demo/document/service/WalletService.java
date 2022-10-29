package com.demo.document.service;

import com.demo.document.dto.WalletDTO;

/**
 * @ClassName WalletService
 * @Date 2022/10/28 22:23
 * @Version 1.0
 */
public interface WalletService {

    /**
     * 根据用户id获取钱包DTO
     *
     * @param userId
     * @return
     */
    WalletDTO queryWalletDTOByUserId(Long userId);

    Boolean updateBlanceById(WalletDTO dto);

}
