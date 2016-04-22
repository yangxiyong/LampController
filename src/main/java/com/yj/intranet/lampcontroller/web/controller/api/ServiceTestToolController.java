package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author mort
 */
@Controller
@RequestMapping(URLConstants.URL_SERVICE_PREFIX)
public class ServiceTestToolController extends PlatformMasterController {

    @RequestMapping(URLConstants.URL_SERVICE_TEST_TOOLS)
    public String apiTools(Map<String, Object> model) {
        return "api/tools";
    }

}
