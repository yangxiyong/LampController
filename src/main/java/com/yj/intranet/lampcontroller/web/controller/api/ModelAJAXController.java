package com.yj.intranet.lampcontroller.web.controller.api;


import com.yj.intranet.lampcontroller.domain.Model;
import com.yj.intranet.lampcontroller.platform.controller.PlatformAJAXController;
import com.yj.intranet.lampcontroller.service.ModelService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by yxy on 2015/01/06.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_MODEL_PREFIX)
public class ModelAJAXController extends PlatformAJAXController {

    @Inject
    private ModelService modelService;

    @RequestMapping(value = URLConstants.URL_COMMON_CHECK_NAME, method = RequestMethod.POST)
    @ResponseBody
    public int checkName(@RequestParam String name) {
        Model model = modelService.getByName(name);
        if (model != null) {
            return 0;
        }
        return 1;
    }
}
