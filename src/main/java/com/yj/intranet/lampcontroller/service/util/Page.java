package com.yj.intranet.lampcontroller.service.util;

import java.util.List;

/**
 * Created by yxy on 2014/12/22.
 */
public class Page {
    private int pageNo;
    private int pageSize;
    private int pageCount;
    private int totalCount;
    @SuppressWarnings("unchecked")
    private List list;

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo > 1 ? pageNo : 1;
        this.pageSize = pageSize > 0 ? pageSize : 25;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }


    @SuppressWarnings("unchecked")
    public List getList() {
        return list;
    }

    @SuppressWarnings("unchecked")
    public void setList(List list) {
        this.list = list;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.pageCount = totalCount % pageSize == 0 ? totalCount / pageSize
                : totalCount / pageSize + 1;
        if (pageNo > pageCount) {
            pageNo = pageCount;
        }

    }

}
