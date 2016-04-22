package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.intranet.lampcontroller.platform.controller.PlatformServiceController;
import com.yj.intranet.lampcontroller.service.ModelService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.LoadModelSubmitForm;
import com.yj.intranet.lampcontroller.web.view.BackClientDataArea;
import com.yj.intranet.lampcontroller.web.view.BackClientView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author yxy
 */
@Controller
@RequestMapping(URLConstants.URL_SERVICE_PREFIX)
public class ModelServiceController extends PlatformServiceController {

    @Inject
    ModelService modelService;

    @RequestMapping(value = URLConstants.URL_SERVICE_MODEL_LOAD, method = RequestMethod.POST)
    @ResponseBody
    public BackClientView loadService(@Valid LoadModelSubmitForm loadModelSubmitForm) {
        BackClientView backClientView = new BackClientView();
        List<BackClientDataArea> dataAreaList = modelService.load(loadModelSubmitForm.getModelId(), loadModelSubmitForm.getUserName());
        if (dataAreaList != null) {
            backClientView.setUserName(loadModelSubmitForm.getUserName());
            backClientView.setBackClientDataAreaList(dataAreaList);
            backClientView.setStatus(1);
        }
        return backClientView;
    }


}
