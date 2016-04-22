package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.Model;
import com.yj.intranet.lampcontroller.domain.RouteFilter;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.*;
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
@RequestMapping(value = URLConstants.URL_FILTER_PREFIX)
public class FilterController extends PlatformMasterController {

    @Inject
    private GroupsService groupsService;
    @Inject
    private RouteService routeService;

    private Groups mGroup;

    @Inject
    private RouteFilterService routeFilterService;
    @Inject
    private ModelService modelService;
    @Inject
    private ControlService controlService;

    //进入filter main主页
    @RequestMapping(value = URLConstants.URL_PAGE, method = RequestMethod.GET)
    public String queryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> model) {
        Page page = routeFilterService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        model.put("filterPage", page);
        return "filter_main";
    }

    //添加或修改filter
    @RequestMapping(value = URLConstants.URL_COMMON_EDIT, method = RequestMethod.GET)
    public String edit(@RequestParam("id") int filterId, Map<String, Object> model) {
        System.out.println(" edit filterId:" + filterId);
        if (filterId == 0) {
            return "filter_add";
        } else {
            RouteFilter routeFilter = routeFilterService.load(filterId);
            model.put("filterBean", routeFilter);
        }
        return "filter_update";
    }

    //保存filter
    @RequestMapping(value = URLConstants.URL_COMMON_ADD, method = RequestMethod.POST)
    public String add(@Valid RouteFilter filter, Map<String, Object> model) {
        routeFilterService.createFilter(filter);
        Page page = routeFilterService.findPage(1, 0);
        model.put("filterPage", page);
        return "filter_main";
    }

    //删除filter
    @RequestMapping(value = URLConstants.URL_COMMON_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id, Map<String, Object> model) {
        routeFilterService.remove(id);
        Page page = routeFilterService.findPage(1, 0);
        model.put("filterPage", page);
        return "filter_main";
    }

    //更新filter
    @RequestMapping(value = URLConstants.URL_COMMON_UPDATE, method = RequestMethod.POST)
    public String update(@Valid RouteFilter routeFilter, Map<String, Object> model) {
        routeFilterService.updateFilter(routeFilter);
        Page page = routeFilterService.findPage(1, 0);
        model.put("filterPage", page);
        return "filter_main";
    }

    //根据名称查询
    @RequestMapping(value = URLConstants.URL_COMMON_FIND_NAME, method = RequestMethod.POST)
    public String find(@RequestParam("queryCondition") String queryCondition, Map<String, Object> model) {
        if (queryCondition != null || !queryCondition.isEmpty()) {
            Page page = routeFilterService.find(queryCondition, 1, 0);
            model.put("filterPage", page);
        }
        return "filter_main";
    }

    //分类模式管理
    @RequestMapping(value = URLConstants.URL_ADVANCE_FILTER_MODEL, method = RequestMethod.GET)
    public String showFilterModel(@RequestParam("id") int filterId, Map<String, Object> model) {
        if (filterId != 0) {
            List<Model> list = new ArrayList();
            //根据ID查找出分类同时带有模式
            RouteFilter routeFilter = routeFilterService.loadWithModel(filterId);

            list = this.modelService.showSimpleAll();

            List<Model> routeList = routeFilter.getModels();
            List<Model> tmList = new ArrayList();
            for (int j = 0; j < list.size(); j++) {
                for (int i = 0; i < routeList.size(); i++) {
                    if (list.get(j).getModelID() == routeList.get(i).getModelID()) {
                        System.out.println("getRouteName s:" + list.get(j).getModelName());
                        tmList.add(list.get(j));
                        break;
                    }
                }
            }
            list.removeAll(tmList);
            model.put("otherRoutes", list);
            model.put("bean", routeFilter);
        }
        return "filter_model";
    }

    //保存分类模式对应关系
    @RequestMapping(value = URLConstants.URL_ADVANCE_FILTER_MODEL_SAVE, method = RequestMethod.POST)
    public String allocateRoute(@RequestParam("temp_routeId_val") String routeIDs, @RequestParam("param_groupId") int groupID,
                                @RequestParam("param_userName") String userName, Map<String, Object> model) {
        routeFilterService.allocateModel(routeIDs, groupID, userName);
        Page page = routeFilterService.findPage(1, 0);
        model.put("filterPage", page);
        return "filter_main";
    }

    //分类控制器（线路）管理
    @RequestMapping(value = URLConstants.URL_ADVANCE_FILTER_CONTROL, method = RequestMethod.GET)
    public String showFilterControl(@RequestParam("id") int filterId, Map<String, Object> model) {
        if (filterId != 0) {
            List<Control> list = new ArrayList();
            //根据ID查找出分类同时带有模式
            RouteFilter routeFilter = routeFilterService.loadWithControl(filterId);
            list = this.controlService.showAll();

//          List<Control> routeList = routeFilter.getControls();// 一直是null的
            List<Control> routeList = routeFilterService.listControlByFilterId(filterId);
            List<Control> tmList = new ArrayList();
            for (int j = 0; j < list.size(); j++) {
                for (int i = 0; i < routeList.size(); i++) {
                    if (list.get(j).getControlID() == routeList.get(i).getControlID()) {
                        tmList.add(list.get(j));
                        break;
                    }
                }
            }
            list.removeAll(tmList);
            routeFilter.setControls(routeList);
            model.put("otherRoutes", list);
            model.put("bean", routeFilter);
        }
        return "filter_control";
    }

    //保存分类控制器对应关系
    @RequestMapping(value = URLConstants.URL_ADVANCE_FILTER_ROUTE_SAVE, method = RequestMethod.POST)
    public String allocateControl(@RequestParam("temp_routeId_val") String routeIDs, @RequestParam("param_groupId") int groupID,
                                  @RequestParam("param_userName") String userName, Map<String, Object> model) {
        routeFilterService.allocateControl(routeIDs, groupID, userName);
        Page page = routeFilterService.findPage(1, 0);
        model.put("filterPage", page);
        return "filter_main";
    }


}
