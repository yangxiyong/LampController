package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.intranet.lampcontroller.domain.User;
import com.yj.intranet.lampcontroller.platform.controller.PlatformServiceController;
import com.yj.intranet.lampcontroller.service.UserService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.LoginSubmitForm;
import com.yj.intranet.lampcontroller.web.view.UserLoginView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author mort
 */
@Controller
@RequestMapping(URLConstants.URL_SERVICE_PREFIX)
public class UserServiceController extends PlatformServiceController {

    @Inject
    private UserService userService;

    @RequestMapping(value = URLConstants.URL_SERVICE_USER_LOGIN, method = RequestMethod.POST)
    @ResponseBody
    public UserLoginView loginService(@Valid LoginSubmitForm loginSubmitForm) {
        User user = userService.load(loginSubmitForm.getUserName(), loginSubmitForm.getPassword());
        UserLoginView view = new UserLoginView();
        if (user == null) {
            view.setMessage("Failed login. User name or password are not correct!");
        } else {
            view.setUserName(user.getUserName());
            view.setMessage("Success");
        }
        return view;
    }
}
