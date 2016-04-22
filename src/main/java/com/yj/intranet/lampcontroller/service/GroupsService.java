package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.service.util.Page;

import java.util.List;

/**
 * @author yxy
 */
public interface GroupsService {

    public Groups load(int groupID);

    public Page find(String name, int pageNo, int pageSize);

    public Groups getByName(String groupName);

    public void createGroup(Groups group);

    public void remove(int groupID);

    public void updateGroup(Groups group);

    public List<Groups> showAll();

    public Page findPage(int pageNo, int pageSize);

    public List<Route> findOtherRoute(int groupID);

    public void allocate(String routeIdStr, int groupID, String userName);
}
