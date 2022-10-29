package com.demo.document.req;

import java.io.Serializable;

/**
 * @ClassName BalanceChangeDetailListQueryReq
 * @Author arracc
 * @Date 2022/10/29 11:30
 * @Version 1.0
 */
public class BalanceChangeDetailListQueryReq implements Serializable {

    private static final long serialVersionUID = -4728970203852048124L;

    /**
     * 页数
     */
    private Integer pageIndex;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
