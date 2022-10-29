package com.demo.document.service;

import com.demo.document.dto.BalanceChangeDetailDTO;
import java.util.List;

/**
 * @ClassName BalanceChangeDetailService
 * @Date 2022/10/29 0:57
 * @Version 1.0
 */
public interface BalanceChangeDetailService {

    /**
     * 新增余额变更明细
     *
     * @param dto
     * @return
     */
    Boolean addChangeDetail(BalanceChangeDetailDTO dto);


    /**
     * 分页查询钱包变更明细列表
     *
     * @param walletId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<BalanceChangeDetailDTO> queryChangeDetailPagedListByWalletId(Long walletId,
            Integer pageIndex, Integer pageSize);

}
