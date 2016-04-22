package com.yj.intranet.lampcontroller.web.controller;

import com.yj.core.platform.web.site.session.SessionContext;
import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.domain.Users;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.GroupsService;
import com.yj.intranet.lampcontroller.service.UsersService;
import com.yj.intranet.lampcontroller.service.util.Page;
import com.yj.intranet.lampcontroller.web.constants.SessionConstants;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.ChangePwdSubmitForm;
import com.yj.intranet.lampcontroller.web.form.QueryPageForm;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2014/12/25.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_USER_PREFIX)
public class UsersController extends PlatformMasterController {

    @Inject
    private UsersService usersService;
    @Inject
    private GroupsService groupsService;
    @Inject
    SessionContext sessionContext;

    private Users mUser;


    @RequestMapping(value = URLConstants.URL_COMMON_UPDATE, method = RequestMethod.POST)
    public String update(@Valid Users user, Map<String, Object> model) {
        usersService.updateUser(user);
        model.put("myName2", sessionContext.get(SessionConstants.CURRENT_USER));
        model.put("myGroupId2", sessionContext.get(SessionConstants.CURRENT_USER_GROUP));
        Page page = usersService.findPage(1, 0);
        model.put("page", page);
        return "user_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_EDIT, method = RequestMethod.GET)
    public String edit(@RequestParam("uid") int userID, Map<String, Object> model) {
        System.out.println(" edit userID:" + userID);
        List<Groups> groups = groupsService.showAll();
        model.put("groupList", groups);
        if (userID == 0) {
            return "user_add";
        } else {
            mUser = usersService.findByNameOrId("", userID);
            model.put("userBean", mUser);
        }
        return "user_update";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_ADD, method = RequestMethod.POST)
    public String add(@Valid Users user, Map<String, Object> model) {
        System.out.println("user in groupId:" + user.getGroup().getGroupID());
        usersService.createUser(user);
        Page page = usersService.findPage(1, 0);
        model.put("page", page);
        return "user_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam("uid") int userID, Map<String, Object> model) {
        usersService.remove(userID);
        Page page = usersService.findPage(1, 0);
        model.put("page", page);
        return "user_main";
    }

    @RequestMapping(value = URLConstants.URL_CONTROL_FIND_NAME, method = RequestMethod.POST)
    public String find(@RequestParam("uName") String userName, Map<String, Object> model) {
        if (userName != null || !userName.isEmpty()) {
            Page page = usersService.find(userName, 1, 0);
            model.put("page", page);
        }
        return "user_main";
    }

    //进入user main主页
    @RequestMapping(value = URLConstants.URL_PAGE, method = RequestMethod.GET)
    public String queryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> model) {
        System.out.println("go user queryPage start");
        System.out.println("go  user queryPage  pageNo:" + queryPageForm.getPageNo());
        Page page = usersService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        model.put("page", page);
        System.out.println("go user queryPage end");
        return "user_main";
    }

    @RequestMapping(value = URLConstants.URL_USER_TURN_UPDATE_PWD, method = RequestMethod.GET)
    public String goUpdatePassword(@RequestParam("id") String id, Map<String, Object> model) {
        if (!StringUtils.hasText(id)) {
            return "user_main";
        } else {
            mUser = usersService.findByNameOrId("", Integer.valueOf(id));
            model.put("userBean", mUser);
        }
        return "user_updatePwd";
    }

    @RequestMapping(value = URLConstants.URL_USER_UPDATE_PWD, method = RequestMethod.POST)
    public String goUpdatePassword(@Valid ChangePwdSubmitForm changePwdSubmitForm, Map<String, Object> model) {
        usersService.updatePassword(changePwdSubmitForm.getUserName(), changePwdSubmitForm.getOldPassword(), changePwdSubmitForm.getNewPassword());

        Page page = usersService.findPage(1, 0);
        model.put("page", page);
        return "user_main";
    }

}
