package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.Route;

import java.util.List;

/**
 * @author yxy
 */
@MyBatisMapper
public interface RouteDao {

    public Route getRouteControlByRouteId(int routeID);

    public List<Route> findRoutesByGroupId(int groupID);

    public Route findOne(int routeID);

    public Route getRouteByName(String routeName);

    public List<Route> getAvailableController();

    public List<Route> getDisableController();

    public void addRoute(Route route);

    public void deleteRoute(int routeID);

    public void updateRoute(Route route);

    public List<Route> listRoute();


    public List<Route> listRouteByPage(int pageNo, int pageSize);

    public List<Route> findByName(String routeName, int pageNo, int pageSize);

    public void updateName(String routeID, String routName);
    public List<Route> listRoute(int controlID);

}
