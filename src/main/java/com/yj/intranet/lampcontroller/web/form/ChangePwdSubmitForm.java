package com.yj.intranet.lampcontroller.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * Created by yxy on 2014/12/17.
 */
public class ChangePwdSubmitForm {
    @NotEmpty
    @Length(max = 20, message = "the length of user name must between 1 and 20")
    private String userName;
    @NotEmpty
    @Length(max = 200, message = "the length of password must between 1 and 200")
    private String oldPassword;
    @NotEmpty
    @Length(max = 200, message = "the length of password must between 1 and 200")
    private String newPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
