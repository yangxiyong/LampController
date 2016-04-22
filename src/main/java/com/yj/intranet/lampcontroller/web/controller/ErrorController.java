package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mort
 */

@Controller
public class ErrorController extends PlatformMasterController {

    @RequestMapping(value = URLConstants.URL_NO_PERMISSION)
    public String noPermission() {
        return "nopermission";
    }
}
