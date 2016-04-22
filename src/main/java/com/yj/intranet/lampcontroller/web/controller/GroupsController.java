package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.GroupsService;
import com.yj.intranet.lampcontroller.service.RouteService;
import com.yj.intranet.lampcontroller.service.util.Page;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.QueryPageForm;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2014/12/18.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_GROUP_PREFIX)
public class GroupsController extends PlatformMasterController {

    @Inject
    private GroupsService groupsService;
    @Inject
    private RouteService routeService;

    private Groups mGroup;

    @RequestMapping(value = URLConstants.URL_COMMON_FIND, method = RequestMethod.POST)
    public String load(@RequestParam("groupId") int groupID, Map<String, Object> model) {
        if (groupID != 0) {
            mGroup = groupsService.load(groupID);
            model.put("groupBean", mGroup);
        }
        return "group_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_UPDATE, method = RequestMethod.POST)
    public String update(@Valid Groups group, Map<String, Object> model) {
        groupsService.updateGroup(group);
        Page page = groupsService.findPage(1, 0);
        model.put("groupPage", page);
        return "group_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_EDIT, method = RequestMethod.GET)
    public String edit(@RequestParam("gid") int groupID, Map<String, Object> model) {
        System.out.println(" edit groupID:" + groupID);
        if (groupID == 0) {
            return "group_add";
        } else {
            mGroup = groupsService.load(groupID);
            model.put("groupBean", mGroup);
        }
        return "group_update";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_ADD, method = RequestMethod.POST)
    public String add(@Valid Groups group, Map<String, Object> model) {
        groupsService.createGroup(group);
        Page page = groupsService.findPage(1, 0);
        model.put("groupPage", page);
        return "group_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam("gid") int groupID, Map<String, Object> model) {
        groupsService.remove(groupID);
        Page page = groupsService.findPage(1, 0);
        model.put("groupPage", page);
        return "group_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_FIND_NAME, method = RequestMethod.POST)
    public String find(@RequestParam("gName") String groupName, Map<String, Object> model) {
        if (groupName != null || !groupName.isEmpty()) {
            Page page = groupsService.find(groupName, 1, 0);
            model.put("groupPage", page);
        }
        return "group_main";
    }

    //进入group main主页
    @RequestMapping(value = URLConstants.URL_PAGE, method = RequestMethod.GET)
    public String queryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> model) {
        Page page = groupsService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        model.put("groupPage", page);
        return "group_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_ADVANCE, method = RequestMethod.GET)
    public String showGroupRoute(@RequestParam("gid") int groupID, Map<String, Object> model) {
        if (groupID != 0) {
            List<Route> list = new ArrayList();
            mGroup = groupsService.load(groupID);
            list = routeService.showAll();
            // list.removeAll(mGroup.getRoutes()); //当MODELROUTEMAPPING有数据时,removeAll失效
            System.out.println("list size:" + list.size());
            List<Route> routeList = mGroup.getRoutes();
            List<Route> tmList = new ArrayList();
            System.out.println("routeList size:" + routeList.size());
            for (int j = 0; j < list.size(); j++) {
                for (int i = 0; i < routeList.size(); i++) {
                    if (list.get(j).getRouteID() == routeList.get(i).getRouteID()) {
                        System.out.println("getRouteName s:" + list.get(j).getRouteName());
                        //list.remove(j); //remove 删除下标会移动，导致有一半数据是不会被remove到的
                        tmList.add(list.get(j));
                        break;
                    }
                }
            }
            list.removeAll(tmList);
            System.out.println("other list size:" + list.size());
            model.put("otherRoutes", list);
            model.put("bean", mGroup);
        }
        return "group_route";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_SAVE, method = RequestMethod.POST)
    public String allocateRoute(@RequestParam("temp_routeId_val") String routeIDs, @RequestParam("param_groupId") int groupID,
                                @RequestParam("param_userName") String userName, Map<String, Object> model) {
        groupsService.allocate(routeIDs, groupID, userName);
        Page page = groupsService.findPage(1, 0);
        model.put("groupPage", page);
        return "group_main";
    }
}
