package com.yj.intranet.lampcontroller.service.impl;

import com.yj.intranet.lampcontroller.dao.RouteFilterDao;
import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.domain.RouteFilter;
import com.yj.intranet.lampcontroller.service.RouteFilterService;
import com.yj.intranet.lampcontroller.service.util.Page;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiYong Yang on 2015/5/22.
 */
@Service
@Singleton
public class RouteFilterServiceImpl implements RouteFilterService {
    @Inject
    private RouteFilterDao routeFilterDao;

    //for api
    @Override
    public RouteFilter getFilterRoutesByFilterId(int userId, int filterId) {
        return this.routeFilterDao.getFilterRoutesByFilterId(userId, filterId);
    }

    //web
    //

    /**
     * 分类管理 分页列表功能
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page findPage(int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        //查询总记录数
        int totalCount = routeFilterDao.findTotalCount();
        page.setTotalCount(totalCount);
        //查询出所有记录 对sql追加limit
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        StringBuffer sb = new StringBuffer();
        sb.append(" limit ").append(offset).append(",").append(offset + page.getPageSize());
        System.out.println("sb:" + sb);
        List<RouteFilter> filterPageList = routeFilterDao.listFilterByPage(offset, offset + page.getPageSize());
        page.setList(filterPageList);
        return page;
    }

    @Override
    public RouteFilter load(int filterId) {
        if (filterId == 0)
            return null;
        return routeFilterDao.findOne(filterId);
    }

    //根据ID查找出分类同时带有模式
    @Override
    public RouteFilter loadWithModel(int filterId) {
        if (filterId == 0)
            return null;
        return routeFilterDao.findOneWithModel(filterId);
    }

    //根据ID查找出分类同时带有控制器
    @Override
    public RouteFilter loadWithControl(int filterId) {
        if (filterId == 0)
            return null;
        return routeFilterDao.findOneWithModel(filterId);
    }

    //根据ID查找出分类同时带有控制器
    @Override
    public List<Control> listControlByFilterId(int filterId) {
        if (filterId == 0)
            return null;
        return routeFilterDao.listControlByFilterId(filterId);
    }

    @Override
    public void createFilter(RouteFilter routeFilter) {
        if (routeFilter != null) {
            routeFilterDao.addFilter(routeFilter);
        }
    }

    @Override
    public void remove(int filterId) {
        if (filterId != 0) {
            routeFilterDao.deleteFilter(filterId);
        }
    }

    @Override
    public void updateFilter(RouteFilter routeFilter) {
        if (routeFilter != null) {
            routeFilterDao.updateFilter(routeFilter);
        }
    }

    //根据名称查询
    @Override
    public Page find(String name, int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        List<RouteFilter> list = routeFilterDao.findByName(name, 1, 0);
        int totalCount = list.size();
        page.setTotalCount(totalCount);
        List<RouteFilter> groupPageList = routeFilterDao.findByName(name, 1, 3);
        page.setList(groupPageList);
        return page;
    }

    //保存分类模式对应关系
    @Transactional(rollbackOn = Exception.class)
    public void allocateModel(String routeIdStr, int groupID, String userName) {
        String[] str = routeIdStr.split(",");
        Map<String, Object> map = new HashMap();
        map.put("gId", groupID);
        map.put("uName", userName);
        map.put("array", str);
        routeFilterDao.deleteModelFromFilterModelMapping(groupID);
        routeFilterDao.saveFilterModel(map);
    }

    //保存分类模式对应关系
    @Transactional(rollbackOn = Exception.class)
    public void allocateControl(String routeIdStr, int groupID, String userName) {
        String[] str = routeIdStr.split(",");
        Map<String, Object> map = new HashMap();
        map.put("gId", groupID);
        map.put("uName", userName);
        map.put("array", str);
        routeFilterDao.deleteControlFromFilterControlMapping(groupID);
        routeFilterDao.saveFilterControl(map);
    }
}
