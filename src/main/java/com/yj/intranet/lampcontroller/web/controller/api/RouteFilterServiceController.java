package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.domain.RouteFilter;
import com.yj.intranet.lampcontroller.platform.controller.PlatformServiceController;
import com.yj.intranet.lampcontroller.service.RouteFilterService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.view.BackClientDataArea;
import com.yj.intranet.lampcontroller.web.view.BackFilterRoutesDataArea;
import com.yj.intranet.lampcontroller.web.view.BackFilterRoutesDataView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiYong Yang on 2015/5/22.
 */
@Controller
@RequestMapping(value = URLConstants.URL_SERVICE_PREFIX)
public class RouteFilterServiceController extends PlatformServiceController {

    private static final Log LOG = LogFactory.getLog(RouteFilterServiceController.class);

    @Inject
    private RouteFilterService routeFilterService;

    @RequestMapping(value = URLConstants.URL_SERVICE_FILTER_SWITCH, method = RequestMethod.POST)
    @ResponseBody
    public BackFilterRoutesDataView switchFilter(@RequestParam String filterId) {
        BackFilterRoutesDataView backFilterRoutesDataView = new BackFilterRoutesDataView();
        if (!StringUtils.hasText(filterId)) {
            backFilterRoutesDataView.setStatus(601);
            backFilterRoutesDataView.setMessage("请求参数有误!");
            return backFilterRoutesDataView;
        }
        try {
            //int uid = Integer.valueOf(userId);
            int fid = Integer.valueOf(filterId);
            RouteFilter routeFilter = this.routeFilterService.getFilterRoutesByFilterId(0, fid);
            if (routeFilter == null || routeFilter.getRoutes().isEmpty()) {
                backFilterRoutesDataView.setStatus(204);
                backFilterRoutesDataView.setMessage("返回数据为空!");
                return backFilterRoutesDataView;
            }
            BackFilterRoutesDataArea backFilterRoutesDataArea = new BackFilterRoutesDataArea();
            List<BackClientDataArea> routeDataList = new ArrayList();
            for (Route route : routeFilter.getRoutes()) {
                BackClientDataArea clientDataArea = new BackClientDataArea();
                clientDataArea.setRouteId(route.getRouteID());
                clientDataArea.setRouteNo(route.getRouteNo());
                clientDataArea.setRouteName(route.getRouteName());
                clientDataArea.setControlIP(route.getControl().getControlIP());
                clientDataArea.setControlPort(route.getControl().getControlPort());
                routeDataList.add(clientDataArea);
            }
            backFilterRoutesDataArea.setFilterId(routeFilter.getId());
            backFilterRoutesDataArea.setFilterName(routeFilter.getFilterName());
            backFilterRoutesDataArea.setBackClientDataAreaList(routeDataList);

            backFilterRoutesDataView.setFilterRoutesDataArea(backFilterRoutesDataArea);
            backFilterRoutesDataView.setStatus(200);
            backFilterRoutesDataView.setMessage("操作成功!");
            return backFilterRoutesDataView;
        } catch (Exception e) {
            LOG.error(e);
            backFilterRoutesDataView.setStatus(500);
            backFilterRoutesDataView.setMessage("内部服务器有误!");
            return backFilterRoutesDataView;
        }
    }

}
