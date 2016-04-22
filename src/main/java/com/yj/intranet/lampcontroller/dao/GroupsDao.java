package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.Route;

import java.util.List;
import java.util.Map;

/**
 * @author yxy
 */
@MyBatisMapper
public interface GroupsDao {
    public Groups findOne(int groupID);

    public Groups findByUserName(String userName);

    public Groups getGroupByName(String userName);

    public List<Groups> findByName(String groupName, int pageNo, int pageSize);

    public void addGroup(Groups group);

    public void deleteGroup(int groupID);

    public void updateGroup(Groups group);

    public List<Groups> listGroup();

    public int findTotalCount();

    public List<Groups> listGroupByPage(int pageNo, int pageSize);


    public List<Route> findOther(int groupID);


    public void saveGroupRoute(Map<String, Object> gruMap);

    public void deleteGroupFromGroupRouteMapping(int groupID);

    public void bulkDelete(Map<String, Object> conditionMap);
}

