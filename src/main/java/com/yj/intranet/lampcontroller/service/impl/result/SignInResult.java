package com.yj.intranet.lampcontroller.service.impl.result;

import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.Users;
import com.yj.intranet.lampcontroller.web.view.BackClientDataArea;

import java.util.List;

/**
 * @author mort
 */
public class SignInResult {
    private Users user;
    private Groups group;
    private int status;
    private String message;
    private List<BackClientDataArea> backClientDataAreaList;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
