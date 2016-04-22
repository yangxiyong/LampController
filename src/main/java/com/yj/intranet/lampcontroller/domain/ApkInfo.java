package com.yj.intranet.lampcontroller.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author yxy
 */
@Entity
public class ApkInfo {

    private int apkID;
    @NotNull
    @Length(max = 50, message = "the length of apk  name must between 1 and 50")
    private String apkName;
    private String versionID;
    private String whatNews;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate = new Date();
    private String createBy;

    public int getApkID() {
        return apkID;
    }

    public void setApkID(int apkID) {
        this.apkID = apkID;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getVersionID() {
        return versionID;
    }

    public void setVersionID(String versionID) {
        this.versionID = versionID;
    }

    public String getWhatNews() {
        return whatNews;
    }

    public void setWhatNews(String whatNews) {
        this.whatNews = whatNews;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
