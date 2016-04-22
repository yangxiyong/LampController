package com.yj.intranet.lampcontroller.web.view;

import com.yj.intranet.lampcontroller.domain.RouteFilter;

import java.util.List;

/**
 * Created by yxy on 2014/12/17.
 */
public class BackClientView {
    private String userName;
    private List<BackClientDataArea> backClientDataAreaList;
    //private List<RouteFilter> filterRoutesList;//useless
    //private List<BackFilterRoutesDataArea> filterRoutesDataList; //显示所有分类和其对应的线路
    private BackFilterRoutesDataArea filterRoutesDataArea;
    private List<BackModelDataArea> modelsList;
    private List<RouteFilter> routeFilterDetailList;
    private int status;

    public List<BackModelDataArea> getModelsList() {
        return modelsList;
    }

    public void setModelsList(List<BackModelDataArea> modelsList) {
        this.modelsList = modelsList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<BackClientDataArea> getBackClientDataAreaList() {
        return backClientDataAreaList;
    }

    public void setBackClientDataAreaList(List<BackClientDataArea> backClientDataAreaList) {
        this.backClientDataAreaList = backClientDataAreaList;
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
