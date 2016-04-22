package com.yj.intranet.lampcontroller.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author yxy
 */
@Entity
public class GroupRouteMapping {

    private int groupID;
    private int routeID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate = new Date();
    private String createBy;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
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
}
