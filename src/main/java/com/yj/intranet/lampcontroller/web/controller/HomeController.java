package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mort
 */
@Controller
@LoginRequired
public class HomeController extends PlatformMasterController {

    @RequestMapping(value = URLConstants.URL_HOME)
    public String home() {
        return "home";
    }
}
