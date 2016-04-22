package com.yj.intranet.lampcontroller.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author yxy
 */
@Entity
public class Model {

    private int modelID;
    @NotNull
    @Length(max = 20, message = "the length of name must between 1 and 20")
    private String modelName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate = new Date();
    private String createBy;
    private String remark;
    private List<ModelRoute> routes;

    public List<ModelRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<ModelRoute> routes) {
        this.routes = routes;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
