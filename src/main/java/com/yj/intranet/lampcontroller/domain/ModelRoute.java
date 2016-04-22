package com.yj.intranet.lampcontroller.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * @author yxy
 */
@Entity
public class ModelRoute {

    private int routeID;
    private String routeName;
    private int routeNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate = new Date();
    private String createBy;
    private Control control;
    private List<Groups> groups;
    private List<Model> models;
    private int switchStatus;


    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(int routeNo) {
        this.routeNo = routeNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }


    public int getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(int switchStatus) {
        this.switchStatus = switchStatus;
    }
}
