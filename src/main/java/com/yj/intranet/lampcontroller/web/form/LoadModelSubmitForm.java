package com.yj.intranet.lampcontroller.web.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by yxy on 2014/12/17.
 */
public class LoadModelSubmitForm {
    @NotEmpty
    private String userName;
    @NotNull
    private int modelId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
