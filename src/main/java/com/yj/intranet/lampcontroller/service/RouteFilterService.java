package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.domain.RouteFilter;
import com.yj.intranet.lampcontroller.service.util.Page;

import java.util.List;

/**
 * Created by XiYong Yang on 2015/5/22.
 */
public interface RouteFilterService {
    //for api
    public RouteFilter getFilterRoutesByFilterId(int userId, int filterId);


    //首页列表
    public Page findPage(int pageNo, int pageSize);

    //编辑功能
    public RouteFilter load(int filterId);

    //根据ID查找出分类同时带有模式
    public RouteFilter loadWithModel(int filterId);

    //根据ID查找出分类同时带有控制器
    public RouteFilter loadWithControl(int filterId);

    public List<Control> listControlByFilterId(int filterId);

    //保存功能
    public void createFilter(RouteFilter routeFilter);

    //删除
    public void remove(int filterId);

    //更新
    public void updateFilter(RouteFilter routeFilter);

    //查询根据名称
    public Page find(String name, int pageNo, int pageSize);

    // 保存分类模式对应关系
    public void allocateModel(String routeIdStr, int groupID, String userName);

    // 保存分类模式对应关系
    public void allocateControl(String routeIdStr, int groupID, String userName);
}
