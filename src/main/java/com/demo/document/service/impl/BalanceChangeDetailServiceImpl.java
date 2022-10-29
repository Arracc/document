package com.demo.document.service.impl;

import com.demo.document.dto.BalanceChangeDetailDTO;
import com.demo.document.service.BalanceChangeDetailService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ClassName BalanceChangeDetailServiceImpl
 * @Date 2022/10/29 0:58
 * @Version 1.0
 */
@Service
public class BalanceChangeDetailServiceImpl implements BalanceChangeDetailService {

    /**
     * 新增余额变更明细
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean addChangeDetail(BalanceChangeDetailDTO dto){
        return true;
    }


    /**
     * 分页查询钱包变更明细列表
     *
     * @param walletId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<BalanceChangeDetailDTO> queryChangeDetailPagedListByWalletId(Long walletId,
            Integer pageIndex, Integer pageSize){
        // 分页

        // 查询

        return new ArrayList<>();
    }

}
