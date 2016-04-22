package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.ControlService;
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
import java.util.Map;

/**
 * Created by yxy on 2014/12/25.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_CONTROL_PREFIX)
public class ControlController extends PlatformMasterController {

    @Inject
    private ControlService controlService;

    private Control mControl;

    @RequestMapping(value = URLConstants.URL_COMMON_FIND, method = RequestMethod.POST)
    public String load(@RequestParam("controlId") int controlID, Map<String, Object> model) {

        if (controlID != 0) {
            mControl = controlService.load(controlID);
            model.put("controlBean", mControl);
        }
        return "control_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_UPDATE, method = RequestMethod.POST)
    public String update(@Valid Control control, Map<String, Object> model) {
        controlService.updateControl(control);
        Page page = controlService.findPage(1, 0);
        model.put("page", page);
        return "control_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_EDIT, method = RequestMethod.GET)
    public String edit(@RequestParam("cid") int controlID, Map<String, Object> model) {
        if (controlID == 0) {
            return "control_add";
        } else {
            mControl = controlService.load(controlID);
            model.put("controlBean", mControl);
        }
        return "control_update";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_ADD, method = RequestMethod.POST)
    public String add(@Valid Control control, Map<String, Object> model) {
        controlService.createControl(control);
        Page page = controlService.findPage(1, 0);
        model.put("page", page);
        return "control_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam("cid") int controlID, Map<String, Object> model) {
        controlService.remove(controlID);
        Page page = controlService.findPage(1, 0);
        model.put("page", page);
        return "control_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_FIND_NAME, method = RequestMethod.POST)
    public String find(@RequestParam("cName") String controlName, Map<String, Object> model) {
        if (controlName != null || !controlName.isEmpty()) {
            Page page = controlService.find(controlName, 1, 0);
            model.put("page", page);
        }
        return "control_main";
    }

    //进入control main主页
    @RequestMapping(value = URLConstants.URL_PAGE, method = RequestMethod.GET)
    public String queryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> model) {
        Page page = controlService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        model.put("page", page);
        return "control_main";
    }


}
