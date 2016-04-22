package com.yj.intranet.lampcontroller.web.view;

import java.util.List;

/**
 * Created by yxy on 2014/12/17.
 */
public class BackModelDataArea {
    private int modelId;
    private String modelName;
    private List<BackClientDataArea> routes;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<BackClientDataArea> getRoutes() {
        return routes;
    }

    public void setRoutes(List<BackClientDataArea> routes) {
        this.routes = routes;
    }
}
