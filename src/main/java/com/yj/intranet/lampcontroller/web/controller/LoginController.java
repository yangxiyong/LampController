package com.yj.intranet.lampcontroller.web.controller;

import com.yj.core.platform.web.DeploymentSettings;
import com.yj.core.platform.web.site.session.SessionContext;
import com.yj.intranet.lampcontroller.domain.Users;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.UsersService;
import com.yj.intranet.lampcontroller.web.constants.SessionConstants;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.LoginSubmitForm;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author mort
 */
@Controller
public class LoginController extends PlatformMasterController {

    @Inject
    private SessionContext sessionContext;
    @Inject
    DeploymentSettings deploymentSettings;

    @Inject
    private UsersService usersService;

    @RequestMapping(value = URLConstants.URL_LOGIN_SUBMIT, method = RequestMethod.POST)
    public String loginSubmit(Map<String, Object> model, @Valid LoginSubmitForm loginForm) {
        Users currentUser = usersService.load(loginForm.getUserName(), loginForm.getPassword());
        if (currentUser == null) {
            model.put("errorName", loginForm.getUserName());
            model.put("errorMsg", "Failed login. Please try again.");
            return "login";
        } else {
            sessionContext.set(SessionConstants.CURRENT_USER_ID, String.valueOf(currentUser.getUserID()));
            sessionContext.set(SessionConstants.CURRENT_USER_GROUP, String.valueOf(currentUser.getGroup().getGroupID()));
            sessionContext.set(SessionConstants.CURRENT_USER, currentUser.getUserName());
            sessionContext.set(SessionConstants.CURRENT_USER_PWD, currentUser.getPasswords());
            //todo user roles
            sessionContext.set(SessionConstants.CURRENT_USER_ROLES, new ArrayList()); //Expecting a string, date or number here, Expression userRoles is instead a freemarker.template.SimpleSequence
            //保存域名
            sessionContext.set(SessionConstants.CURRENT_DEPLOYMENT_CONTEXT, deploymentSettings.getDeploymentContext());
            String destinationUrl = retrieveLoginDestinationUrl();
            if (!StringUtils.hasText(destinationUrl)) {
                destinationUrl = URLConstants.URL_HOME;
            }
            return "redirect:" + destinationUrl;
        }
    }

    private String retrieveLoginDestinationUrl() {
        String destinationUrl = sessionContext.get(SessionConstants.LOGIN_REDIRECT_DESTINATION_URL);
        sessionContext.set(SessionConstants.LOGIN_REDIRECT_DESTINATION_URL, null);
        return destinationUrl;
    }

    @RequestMapping(value = URLConstants.URL_LOGIN_SUBMIT, method = RequestMethod.GET)
    public String loginSubmit() {
        return "forward:" + URLConstants.URL_LOGIN;
    }

    @RequestMapping(value = URLConstants.URL_LOGIN)
    public String login(Map<String, Object> model) {
        return "login";
    }

    @RequestMapping(value = URLConstants.URL_LOGOUT, method = RequestMethod.GET)
    public String logout() {
        sessionContext.invalidate();
        return "redirect:" + URLConstants.URL_LOGIN;
    }
}
