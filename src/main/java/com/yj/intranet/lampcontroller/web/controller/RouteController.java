package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.ControlService;
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
import java.util.HashSet;
import java.util.Map;

/**
 * Created by yxy on 2014/12/26.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_ROUTE_PREFIX)
public class RouteController extends PlatformMasterController {

    @Inject
    private RouteService routeService;
    @Inject
    private ControlService controlService;

    private Route mRoute;

    @RequestMapping(value = URLConstants.URL_COMMON_FIND, method = RequestMethod.POST)
    public String load(@RequestParam("id") int routeID, Map<String, Object> model) {
        if (routeID != 0) {
            mRoute = routeService.load(routeID);
            model.put("bean", mRoute);
        }
        return "route_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_UPDATE, method = RequestMethod.POST)
    public String update(@Valid Route route, Map<String, Object> model) {
        routeService.update(route);
        Page page = routeService.findPage(1, 20);
        model.put("page", page);
        return "route_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_EDIT, method = RequestMethod.GET)
    public String edit(@RequestParam("id") int routeID, Map<String, Object> model) {
        System.out.println(" edit routeID:" + routeID);
        // List<Control> controls=controlService.showAll();
        HashSet controls = routeService.getAvailableControlRoute();
        System.out.println(" edit available controls size:" + controls.size());
        // model.put("selectList", controls);
        model.put("selectList", controls);
        if (routeID == 0) {
            return "route_add";
        } else {
            mRoute = routeService.load(routeID);
            model.put("bean", mRoute);
        }
        return "route_update";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_ADD, method = RequestMethod.POST)
    public String add(@Valid Route route, Map<String, Object> model) {
        System.out.println("route by:" + route.getCreateBy());
        routeService.create(route);
        Page page = routeService.findPage(1, 20);
        model.put("page", page);
        return "route_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam("id") int routeID, Map<String, Object> model) {
        routeService.remove(routeID);
        Page page = routeService.findPage(1, 20);
        model.put("page", page);
        return "route_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_FIND_NAME, method = RequestMethod.POST)
    public String find(@RequestParam("name") String routeName, Map<String, Object> model) {
        if (routeName != null || !routeName.isEmpty()) {
            Page page = routeService.find(routeName, 1, 20);
            model.put("page", page);
        }
        return "route_main";
    }

    //进入route main主页
    @RequestMapping(value = URLConstants.URL_PAGE, method = RequestMethod.GET)
    public String queryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> model) {
        Page page = routeService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        model.put("page", page);
        return "route_main";
    }


}
