package com.yj.intranet.lampcontroller.web.view;

/**
 * Created by yxy on 2014/12/17.
 */
public class BackClientDataArea {
    private int routeId;
    private int routeNo;
    private String routeName;
    private String controlIP;
    private String controlPort;
    private int switchStatus;

    public int getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(int switchStatus) {
        this.switchStatus = switchStatus;
    }

    public int getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(int routeNo) {
        this.routeNo = routeNo;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getControlIP() {
        return controlIP;
    }

    public void setControlIP(String controlIP) {
        this.controlIP = controlIP;
    }

    public String getControlPort() {
        return controlPort;
    }

    public void setControlPort(String controlPort) {
        this.controlPort = controlPort;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
}
