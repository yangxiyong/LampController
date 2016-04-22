package com.yj.intranet.lampcontroller.web.view;

/**
 * Created by XiYong Yang on 2015/5/22.
 */
public class BackFilterRoutesDataView {
    private BackFilterRoutesDataArea filterRoutesDataArea;
    private String message;
    private int status;

    public BackFilterRoutesDataArea getFilterRoutesDataArea() {
        return filterRoutesDataArea;
    }

    public void setFilterRoutesDataArea(BackFilterRoutesDataArea filterRoutesDataArea) {
        this.filterRoutesDataArea = filterRoutesDataArea;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
