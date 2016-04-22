package com.yj.intranet.lampcontroller.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mort
 */
public class User implements Serializable {
    private static final long serialVersionUID = 213400032965376818L;

    private String userName;
    private String password;
    private Date createDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
