package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.platform.controller.PlatformAJAXController;
import com.yj.intranet.lampcontroller.service.ControlService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by yxy on 2015/01/04.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_CONTROL_PREFIX)
public class ControlAJAXController extends PlatformAJAXController {

    @Inject
    private ControlService controlService;

    @RequestMapping(value = URLConstants.URL_COMMON_CHECK_NAME, method = RequestMethod.GET)
    @ResponseBody
    public String checkName(@RequestParam("name") String controlName, @RequestParam("ip") String controlIP) {
        System.out.println("check name:" + controlName);
        Control control1;
        Control control2;
        StringBuffer sb = new StringBuffer();
        if (StringUtils.hasText(controlName)) {
            control1 = controlService.getByName(controlName);
            if (control1 == null) {
                sb.append("name1;");
            } else {
                sb.append("name0;");
            }
        }
        if (StringUtils.hasText(controlIP)) {
            control2 = controlService.getByIP(controlIP);
            if (control2 == null) {
                sb.append("ip1;");
            } else {
                sb.append("ip0;");
            }
        }
        String result = new String(sb);
        System.out.println("result str:" + result);
        return result;
    }

}
