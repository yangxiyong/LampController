package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.core.platform.web.DeploymentSettings;
import com.yj.core.platform.web.url.URLBuilder;
import com.yj.intranet.lampcontroller.domain.ApkInfo;
import com.yj.intranet.lampcontroller.platform.controller.PlatformServiceController;
import com.yj.intranet.lampcontroller.service.ApkInfoService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.view.BackApkView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yxy
 */
@Controller
@RequestMapping(URLConstants.URL_SERVICE_PREFIX)
public class ApkInfoServiceController extends PlatformServiceController {

    @Inject
    ApkInfoService apkInfoService;

    @Inject
    private DeploymentSettings deploymentSettings;

    @Inject
    private ServletContext servletContext;

    @RequestMapping(value = URLConstants.URL_SERVICE_UPDATE_VERSION, method = RequestMethod.POST)
    @ResponseBody
    public BackApkView updatedVersionService(HttpServletRequest request) {
        BackApkView backApkInfo = new BackApkView();
        ApkInfo apkInfo = apkInfoService.getLastApkInfo();
        if (apkInfo != null) {
            String relativePath = apkInfo.getAddress();
            URLBuilder builder = new URLBuilder();
            builder.setScheme(request.getScheme());
            builder.setServerName(request.getServerName());
            System.out.println("serverName:" + request.getServerName()); //serverName:localhost
            builder.setDeploymentPorts(deploymentSettings.getHTTPPort(), deploymentSettings.getHTTPSPort());
            builder.setContextPath(servletContext.getContextPath());
            builder.setLogicalURL(relativePath);
            backApkInfo.setAddress(builder.buildFullURL());

            backApkInfo.setVersionId(apkInfo.getVersionID());
            backApkInfo.setWhatNews(apkInfo.getWhatNews());
            backApkInfo.setStatus(1);
        }
        return backApkInfo;
    }


}
