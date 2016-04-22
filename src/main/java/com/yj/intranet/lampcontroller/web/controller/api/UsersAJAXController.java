package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.intranet.lampcontroller.domain.Users;
import com.yj.intranet.lampcontroller.platform.controller.PlatformAJAXController;
import com.yj.intranet.lampcontroller.service.UsersService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by yxy on 2014/12/18.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_USER_PREFIX)
public class UsersAJAXController extends PlatformAJAXController {

    @Inject
    private UsersService usersService;

    @RequestMapping(value = URLConstants.URL_COMMON_CHECK_NAME, method = RequestMethod.GET)
    @ResponseBody
    public int checkName(@RequestParam("uName") String userName) {
        Users user = usersService.findByNameOrId(userName, 0);
        if (user != null) {
            return 0;
        }
        return 1;
    }

    @RequestMapping(value = URLConstants.URL_USER_CHECK_PWD, method = RequestMethod.GET)
    @ResponseBody
    public int checkPwd(@RequestParam("pwd") String passwords, @RequestParam("name") String name) {
        return usersService.checkPassword(passwords, name);
    }
}
