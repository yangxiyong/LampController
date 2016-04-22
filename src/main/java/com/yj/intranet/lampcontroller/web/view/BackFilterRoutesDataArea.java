package com.yj.intranet.lampcontroller.web.view;

import java.util.List;

/**
 * Created by XiYong Yang on 2015/5/22.
 */
public class BackFilterRoutesDataArea {
    private int filterId;
    private String filterName;
    private List<BackClientDataArea> backClientDataAreaList;

    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public List<BackClientDataArea> getBackClientDataAreaList() {
        return backClientDataAreaList;
    }

    public void setBackClientDataAreaList(List<BackClientDataArea> backClientDataAreaList) {
        this.backClientDataAreaList = backClientDataAreaList;
    }
}
