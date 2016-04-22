package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.platform.controller.PlatformAJAXController;
import com.yj.intranet.lampcontroller.service.RouteService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by yxy on 2015/01/06.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_ROUTE_PREFIX)
public class RouteAJAXController extends PlatformAJAXController {

    @Inject
    private RouteService routeService;

    @RequestMapping(value = URLConstants.URL_COMMON_CHECK_NAME, method = RequestMethod.POST)
    @ResponseBody
    public int checkName(@RequestParam String name) {
        Route route = routeService.getByName(name);
        if (route != null) {
            return 0;
        }
        return 1;
    }

    @RequestMapping(value = URLConstants.URL_ROUTE_AVAILABLE_ROUTENO, method = RequestMethod.POST)
    @ResponseBody
    public List getAvailableRouteNo(@RequestParam int controlId) {
        List list = routeService.pickRouteNo(controlId);
        return list;
    }
}
