package com.yj.intranet.lampcontroller.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yxy
 */
@Entity
public class Control {

    private int controlID;
    @NotNull
    @Length(max = 20, message = "the length of control name must between 1 and 20")
    private String controlName;
    @Range(min = 0, max = 65535)
    private String controlPort;
    private String controlIP;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate = new Date();
    private String createBy;

    public int getControlID() {
        return controlID;
    }

    public void setControlID(int controlID) {
        this.controlID = controlID;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getControlPort() {
        return controlPort;
    }

    public void setControlPort(String controlPort) {
        this.controlPort = controlPort;
    }

    public String getControlIP() {
        return controlIP;
    }

    public void setControlIP(String controlIP) {
        this.controlIP = controlIP;
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
