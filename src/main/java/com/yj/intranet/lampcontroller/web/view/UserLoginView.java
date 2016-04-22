package com.yj.intranet.lampcontroller.web.view;

import java.util.List;

/**
 * @author mort
 */
public class UserLoginView {
    private String userName;
    private List<String> officeAreas;
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getOfficeAreas() {
        return officeAreas;
    }

    public void setOfficeAreas(List<String> officeAreas) {
        this.officeAreas = officeAreas;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
