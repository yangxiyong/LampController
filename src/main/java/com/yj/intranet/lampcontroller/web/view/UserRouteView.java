package com.yj.intranet.lampcontroller.web.view;

import java.util.List;

/**
 * Created by Administrator on 2014/12/17.
 */
public class UserRouteView {
    private String userName;
    /* 0 failed 1 success*/
    private int status;
    private List<OfficeArea> officeAreas;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OfficeArea> getOfficeAreas() {
        return officeAreas;
    }

    public void setOfficeAreas(List<OfficeArea> officeAreas) {
        this.officeAreas = officeAreas;
    }
}
