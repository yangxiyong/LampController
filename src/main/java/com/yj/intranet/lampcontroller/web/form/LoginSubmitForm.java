package com.yj.intranet.lampcontroller.web.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author mort
 */
public class LoginSubmitForm {
    @NotEmpty
    @Length(max = 20, message = "the length of user name must between 1 and 20")
    private String userName;
    @NotEmpty
    @Length(max = 200, message = "the length of password must between 1 and 200")
    private String password;

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
}
