package com.yj.intranet.lampcontroller.web.form;


import javax.validation.constraints.Min;

/**
 * Created by yxy on 2014/12/17.
 */
public class QueryPageForm {
    @Min(value = 0)
    private int pageNo;
    @Min(value = 0)
    private int pageSize;

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
}
