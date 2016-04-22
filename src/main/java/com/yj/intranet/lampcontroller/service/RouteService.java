package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.service.util.Page;

import java.util.HashSet;
import java.util.List;

/**
 * @author yxy
 */
public interface RouteService {

    public Route load(int routeID);

    public Route getByName(String routeName);

    public HashSet getAvailableControlRoute();

    public List pickRouteNo(int controlId);

    public void create(Route route);

    public void remove(int routeID);

    public void update(Route route);

    public List<Route> showAll();


    public Page findPage(int pageNo, int pageSize);

    public Page find(String name, int pageNo, int pageSize);

    public int updateName(String id, String newRouteName);
}
