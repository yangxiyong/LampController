package com.yj.intranet.lampcontroller.service.impl;


import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.dao.ControlDao;
import com.yj.intranet.lampcontroller.dao.RouteDao;
import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.service.RouteService;
import com.yj.intranet.lampcontroller.service.util.Page;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author yxy
 */
@Service
@Singleton
public class RouteServiceImpl implements RouteService {
    @Inject
    private RouteDao routeDao;
    @Inject
    private ControlDao controlDao;

    private static List<Route> mAvailableRoute;

    @Override
    public Route load(int routeID) {
        if (routeID == 0)
            return null;
        return routeDao.findOne(routeID);
    }

    @Override
    public Route getByName(String routeName) {
        if (!StringUtils.hasText(routeName))
            return null;
        return routeDao.getRouteByName(routeName);
    }

    public List updateAvailableRoute() {
        // mAvailableRoute=routeDao.getAvailableController();//method1:

        //method2:
        //get all route
        mAvailableRoute = routeDao.listRoute();
        //disable route
        List<Route> mDisableRoute = routeDao.getDisableController();
        System.out.println("mDisableRoute size:" + mDisableRoute.size());
        //removeAll有效
        mAvailableRoute.removeAll(mDisableRoute);
        System.out.println("mAvailableRoute size:" + mAvailableRoute.size());

        // get available control
        //get all control
        List<Control> controlIdList = controlDao.listControl();
        System.out.println("controlIdList size1:" + controlIdList.size());
        List disableControl = new ArrayList();
        List temp = new ArrayList();
        for (int i = 0; i < mDisableRoute.size(); i += 8) {
            System.out.println("get Control name:" + mDisableRoute.get(i).getControl().getControlName());
            //controlIdList.remove(mDisableRoute.get(i).getControl());
            disableControl.add(mDisableRoute.get(i).getControl().getControlID());
        }
        for (int j = 0; j < disableControl.size(); j++) {
            for (int i = 0; i < controlIdList.size(); i++) {
                if (disableControl.get(j) == controlIdList.get(i).getControlID()) {
                    //controlIdList.remove(i);
                    temp.add(controlIdList.get(i));
                    break;
                }
            }
        }
        controlIdList.removeAll(temp);
        //System.out.println("tmp size:" + tmp.size());
        //System.out.println("flag1:" + controlIdList.contains(controlIdList.get(0)));//true
        //System.out.println("flag1:" + controlIdList.get(0).toString());//:com.yj.intranet.lampcontroller.domain.Control@266fc58e
        //System.out.println("flag:"+tmp.get(0).toString());//false com.yj.intranet.lampcontroller.domain.Control@74130158
        //  controlIdList.removeAll(tmp);
        // System.out.println("controlIdList size2:" + controlIdList.size());
        return controlIdList;
    }

    @Override
    public HashSet getAvailableControlRoute() {

      /*  List controlIdList=new ArrayList();
        for(Route r:mAvailableRoute){
            controlIdList.add(r.getControl());
        } */
        HashSet hs = new HashSet(updateAvailableRoute());
        System.out.println("hs size:" + hs.size());
        return hs;
    }

    @Override
    public List pickRouteNo(int controlId) {
        List neededRouteNolist = new ArrayList();
        List rememberRouteNoList = new ArrayList();
        if (mAvailableRoute == null) {
            updateAvailableRoute();
        }
        for (int i = 1; i < 9; i++) {
            neededRouteNolist.add(i);
        }
        for (Route r : mAvailableRoute) {
            if (r.getControl().getControlID() == controlId) {
                rememberRouteNoList.add(r.getRouteNo());
            }
        }
        neededRouteNolist.removeAll(rememberRouteNoList);
        return neededRouteNolist;
    }

    @Override
    public Page find(String name, int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        List<Route> list = routeDao.findByName(name, 1, 0);
        int totalCount = list.size();
        page.setTotalCount(totalCount);
        List<Route> routePageList = routeDao.findByName(name, 1, 20);
        page.setList(routePageList);
        return page;
    }

    @Override
    public void create(Route route) {
        if (route != null) {
            routeDao.addRoute(route);
        }
    }

    @Override
    public void remove(int routeID) {
        if (routeID != 0) {
            routeDao.deleteRoute(routeID);
        }
    }

    @Override
    public void update(Route route) {
        if (route != null) {
            routeDao.updateRoute(route);
        }
    }

    @Override
    public List<Route> showAll() {
        return routeDao.listRoute();
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page findPage(int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        List<Route> routePageList = routeDao.listRouteByPage(offset, page.getPageSize());
        page.setList(routePageList);
        int totalCount = routePageList.size();
        page.setTotalCount(totalCount);
        return page;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public int updateName(String id, String newRouteName) {
        Route route = this.getByName(newRouteName);
        if (route == null) {
            routeDao.updateName(id, newRouteName);
            return 1;
        }
        return 0;
    }

}

