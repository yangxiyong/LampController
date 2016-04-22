package com.yj.intranet.lampcontroller.service.impl;


import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.dao.GroupsDao;
import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.service.GroupsService;
import com.yj.intranet.lampcontroller.service.util.Page;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yxy
 */
@Service
@Singleton
public class GroupsServiceImpl implements GroupsService {
    @Inject
    private GroupsDao groupsDao;


    @Override
    public Groups load(int groupID) {
        if (groupID == 0)
            return null;
        return groupsDao.findOne(groupID);
    }

    @Override
    public Page find(String name, int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        List<Groups> list = groupsDao.findByName(name, 1, 0);
        int totalCount = list.size();
        page.setTotalCount(totalCount);
        List<Groups> groupPageList = groupsDao.findByName(name, 1, 3);
        page.setList(groupPageList);
        return page;
    }

    @Override
    public Groups getByName(String groupName) {
        if (!StringUtils.hasText(groupName))
            return null;
        return groupsDao.getGroupByName(groupName);
    }

    @Override
    public void createGroup(Groups group) {
        if (group != null) {
            groupsDao.addGroup(group);
        }
    }

    @Override
    public void remove(int groupID) {
        if (groupID != 0) {
            groupsDao.deleteGroup(groupID);
        }
    }

    @Override
    public void updateGroup(Groups group) {
        if (group != null) {
            groupsDao.updateGroup(group);
        }
    }

    @Override
    public List<Groups> showAll() {
        return groupsDao.listGroup();
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page findPage(int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        //查询总记录数
        int totalCount = groupsDao.findTotalCount();
        page.setTotalCount(totalCount);
        //查询出所有记录 对sql追加limit
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        StringBuffer sb = new StringBuffer();
        sb.append(" limit ").append(offset).append(",").append(offset + page.getPageSize());
        System.out.println("sb:" + sb);
        List<Groups> groupPageList = groupsDao.listGroupByPage(offset, offset + page.getPageSize());
        page.setList(groupPageList);
        return page;
    }

    public List<Route> findOtherRoute(int groupID) {
        return groupsDao.findOther(groupID);
    }

    @Transactional(rollbackOn = Exception.class)
    public void allocate(String routeIdStr, int groupID, String userName) {
        String[] str = routeIdStr.split(",");
        Map<String, Object> map = new HashMap();
        map.put("gId", groupID);
        map.put("uName", userName);
        map.put("array", str);
        groupsDao.deleteGroupFromGroupRouteMapping(groupID);
        groupsDao.saveGroupRoute(map);
        /*method2:
        List<Route> idList=routeDao.findRoutesByGroupId(groupID); //or routeDao.findRoutesByGroupId
        map.put("list",idList);
        groupsDao.bulkDelete(map);
        groupsDao.saveGroupRoute(map);*/
    }

}
