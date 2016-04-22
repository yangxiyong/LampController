package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.intranet.lampcontroller.domain.Groups;
import com.yj.intranet.lampcontroller.platform.controller.PlatformAJAXController;
import com.yj.intranet.lampcontroller.service.GroupsService;
import com.yj.intranet.lampcontroller.service.util.Page;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.QueryPageForm;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import com.yj.intranet.lampcontroller.web.view.BackAjaxData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by yxy on 2014/12/18.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_GROUP_PREFIX)
public class GroupsAJAXController extends PlatformAJAXController {

    @Inject
    private GroupsService groupsService;

    @RequestMapping(value = URLConstants.URL_PAGE_ASY, method = RequestMethod.GET)
    @ResponseBody
    public BackAjaxData asyQueryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> model, HttpServletRequest request) {
        BackAjaxData backAjaxData = new BackAjaxData();
        Page page = groupsService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        backAjaxData.setPageNo(page.getPageNo());
        backAjaxData.setPageSize(page.getPageSize());
        backAjaxData.setPageCount(page.getPageCount());
        backAjaxData.setPageList(page.getList());
        backAjaxData.setStatus(1);
        return backAjaxData;
    }

    @RequestMapping(value = URLConstants.URL_COMMON_CHECK_NAME, method = RequestMethod.POST)
    @ResponseBody
    public int checkName(@RequestParam String name) {
        Groups group = groupsService.getByName(name);
        if (group != null) {
            return 0;
        }
        return 1;
    }
}
