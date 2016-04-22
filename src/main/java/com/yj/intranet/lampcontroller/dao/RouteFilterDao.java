package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.domain.RouteFilter;

import java.util.List;
import java.util.Map;

/**
 * Created by XiYong Yang on 2015/5/20.
 */

@MyBatisMapper
public interface RouteFilterDao {
    //for api
    public List<RouteFilter> listRouteFilter(int userId);

    public List<RouteFilter> listRouteFilterIncludeModelRoute(int userId);

    public List<RouteFilter> listFilterRoutes(int userId);

    public RouteFilter getFilterRoutesByFilterId(int userId, int filterId);

    //首页列表
    public int findTotalCount();

    public List<RouteFilter> listFilterByPage(int pageNo, int pageSize);

    //编辑
    public RouteFilter findOne(int filterId);

    //根据ID查找出分类同时带有模式
    public RouteFilter findOneWithModel(int filterId);

    //根据ID查找出分类同时带有控制器
    public RouteFilter findOneWithControl(int filterId);

    public List<Control> listControlByFilterId(int filterId);

    //保存
    public void addFilter(RouteFilter routeFilter);

    //删除
    public void deleteFilter(int filterId);

    //更新
    public void updateFilter(RouteFilter routeFilter);

    //根据名称查询
    public List<RouteFilter> findByName(String filterName, int pageNo, int pageSize);

    //保存分类模式对应关系
    public void saveFilterModel(Map<String, Object> gruMap);

    public void deleteModelFromFilterModelMapping(int groupID);

    //保存分类模式对应关系
    public void saveFilterControl(Map<String, Object> gruMap);

    public void deleteControlFromFilterControlMapping(int groupID);

}
