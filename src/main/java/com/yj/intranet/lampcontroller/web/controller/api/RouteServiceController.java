package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.platform.controller.PlatformServiceController;
import com.yj.intranet.lampcontroller.service.RouteService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.view.BackView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by XiYong Yang on 2015/5/20.
 */
@Controller
@RequestMapping(value = URLConstants.URL_SERVICE_PREFIX)
public class RouteServiceController extends PlatformServiceController {
    private static final Log LOG = LogFactory.getLog(RouteServiceController.class);

    @Inject
    private RouteService routeService;

    @RequestMapping(value = URLConstants.URL_SERVICE_ROUTE_NAME_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public BackView updateRouteName(@RequestParam("routeId") String routeId, @RequestParam("routeName") String routeName) {
        BackView backView = new BackView();
        if (!StringUtils.hasText(routeId) || !StringUtils.hasText(routeName)) {
            backView.setMessage("请求参数有误!");
            backView.setStatus(601);
            return backView;
        }
        try {
            //线路名不能重复 603
            int result = this.routeService.updateName(routeId, routeName);
            if (result == 0) {
                backView.setMessage("线路名已存在!");
                backView.setStatus(603);
                return backView;
            }
            backView.setMessage("修改成功!");
            backView.setStatus(200);
            return backView;
        } catch (Exception e) {
            LOG.error(e);
            backView.setMessage("服务器内容出错!");
            backView.setStatus(500);
            return backView;
        }
    }

}
