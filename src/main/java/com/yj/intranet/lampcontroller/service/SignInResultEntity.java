package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.RouteFilter;
import com.yj.intranet.lampcontroller.domain.Users;
import com.yj.intranet.lampcontroller.web.view.BackClientDataArea;
import com.yj.intranet.lampcontroller.web.view.BackFilterRoutesDataArea;
import com.yj.intranet.lampcontroller.web.view.BackModelDataArea;

import java.util.List;

/**
 * Created by yxy on 2014/12/18.
 */
public class SignInResultEntity {
    private Users user;
    private Groups group;
    private int status;
    private List<BackClientDataArea> backClientDataAreaList;
    //private List<RouteFilter> filterRoutesList;//useless//
    //private List<BackFilterRoutesDataArea> filterRoutesDataList;//显示所有分类和其对应的线路
    private BackFilterRoutesDataArea filterRoutesDataArea;
    private List<BackModelDataArea> modelsList;
    private List<RouteFilter> routeFilterDetailList;

    public List<BackModelDataArea> getModelsList() {
        return modelsList;
    }

    public void setModelsList(List<BackModelDataArea> modelsList) {
        this.modelsList = modelsList;
    }

    public List<BackClientDataArea> getBackClientDataAreaList() {
        return backClientDataAreaList;
    }

    public void setBackClientDataAreaList(List<BackClientDataArea> backClientDataAreaList) {
        this.backClientDataAreaList = backClientDataAreaList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RouteFilter> getRouteFilterDetailList() {
        return routeFilterDetailList;
    }

    public void setRouteFilterDetailList(List<RouteFilter> routeFilterDetailList) {
        this.routeFilterDetailList = routeFilterDetailList;
    }

    public BackFilterRoutesDataArea getFilterRoutesDataArea() {
        return filterRoutesDataArea;
    }

    public void setFilterRoutesDataArea(BackFilterRoutesDataArea filterRoutesDataArea) {
        this.filterRoutesDataArea = filterRoutesDataArea;
    }
}
