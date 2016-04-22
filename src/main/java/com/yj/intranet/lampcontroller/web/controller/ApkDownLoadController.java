package com.yj.intranet.lampcontroller.web.controller;

import com.yj.core.platform.web.DeploymentSettings;
import com.yj.core.platform.web.url.URLBuilder;
import com.yj.intranet.lampcontroller.domain.ApkInfo;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.ApkInfoService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.view.BackApkView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yxy on 2014/12/30.
 */
@Controller
public class ApkDownLoadController extends PlatformMasterController {

    @Inject
    private ServletContext servletContext;

    @Inject
    private ApkInfoService apkInfoService;
    @Inject
    private DeploymentSettings deploymentSettings;

    @RequestMapping(value = URLConstants.URL_DOWN_APK_PREFIX, method = RequestMethod.GET)
    public String turnDown(Map<String, Object> model, HttpServletRequest request) {
        //获取地址
        BackApkView backApkInfo = new BackApkView();
        ApkInfo apkInfo = apkInfoService.getLastApkInfo();
        if (apkInfo != null) {
            String relativePath = apkInfo.getAddress();
            URLBuilder builder = new URLBuilder();
            //http or https
            builder.setScheme(request.getScheme());
            //服务器地址 localhost or 服务器ip
            builder.setServerName(request.getServerName());
            builder.setDeploymentPorts(deploymentSettings.getHTTPPort(), deploymentSettings.getHTTPSPort());
            builder.setContextPath(servletContext.getContextPath());
            builder.setLogicalURL(relativePath);
            backApkInfo.setAddress(builder.buildFullURL());

            backApkInfo.setVersionId(apkInfo.getVersionID());
            backApkInfo.setWhatNews(apkInfo.getWhatNews());
            backApkInfo.setApkName(apkInfo.getApkName());
        }
        model.put("bean", backApkInfo);
        return "download_apk";
    }

}
