package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.intranet.lampcontroller.platform.controller.PlatformServiceController;
import com.yj.intranet.lampcontroller.service.SignInResultEntity;
import com.yj.intranet.lampcontroller.service.UsersService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.ChangePwdSubmitForm;
import com.yj.intranet.lampcontroller.web.form.LoginSubmitForm;
import com.yj.intranet.lampcontroller.web.view.BackClientView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author yxy
 */
@Controller
@RequestMapping(URLConstants.URL_SERVICE_PREFIX)
public class UsersServiceController extends PlatformServiceController {

    @Inject
    private UsersService usersService;

    @RequestMapping(value = URLConstants.URL_SERVICE_USER_ENTRY, method = RequestMethod.POST)
    @ResponseBody
    public BackClientView entryService(@Valid LoginSubmitForm loginSubmitForm) {
        BackClientView backClientView = new BackClientView();
        //SignInResultEntity signInResultEntity = usersService.clientLogin(loginSubmitForm.getUserName(), loginSubmitForm.getPassword());
        SignInResultEntity signInResultEntity = usersService.clientLogin2(loginSubmitForm.getUserName(), loginSubmitForm.getPassword());
        if (signInResultEntity != null) {
            backClientView.setUserName(signInResultEntity.getUser().getUserName());
            backClientView.setBackClientDataAreaList(signInResultEntity.getBackClientDataAreaList());
//            backClientView.setFilterRoutesList(signInResultEntity.getFilterRoutesList()); //useless
//            backClientView.setFilterRoutesDataList(signInResultEntity.getFilterRoutesDataList());////显示所有分类和其对应的线路
            backClientView.setFilterRoutesDataArea(signInResultEntity.getFilterRoutesDataArea());
            backClientView.setModelsList(signInResultEntity.getModelsList());
            backClientView.setRouteFilterDetailList(signInResultEntity.getRouteFilterDetailList());
            backClientView.setStatus(signInResultEntity.getStatus());
        }
        return backClientView;
    }

    @RequestMapping(value = URLConstants.URL_SERVICE_USER_UPDATEPWD, method = RequestMethod.POST)
    @ResponseBody
    public int updatePwdService(@Valid ChangePwdSubmitForm changePwdSubmitForm) {
        return usersService.updatePassword(changePwdSubmitForm.getUserName(), changePwdSubmitForm.getOldPassword(), changePwdSubmitForm.getNewPassword());
    }

}
