package com.yj.intranet.lampcontroller.web.controller.api;

import com.yj.core.platform.web.DeploymentSettings;
import com.yj.core.platform.web.url.URLBuilder;
import com.yj.intranet.lampcontroller.domain.ApkInfo;
import com.yj.intranet.lampcontroller.platform.controller.PlatformAJAXController;
import com.yj.intranet.lampcontroller.service.ApkInfoService;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * Created by yxy on 2014/12/18.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_APK_PREFIX)
public class ApkInfoAJAXController extends PlatformAJAXController {

    @Inject
    ApkInfoService apkInfoService;

    @Inject
    private DeploymentSettings deploymentSettings;

    @Inject
    private ServletContext servletContext;

    @RequestMapping(value = URLConstants.URL_COMMON_CHECK_NAME, method = RequestMethod.GET)
    @ResponseBody
    public int checkVersion(@RequestParam("apkName") String apkName, @RequestParam("versionID") String versionID) {
        ApkInfo apkInfo = apkInfoService.checkVesrionId(apkName, versionID);
        if (apkInfo != null) {
            return 0;
        }
        return 1;
    }

    @RequestMapping(value = URLConstants.URL_COMMON_DOWN_LOAD, method = RequestMethod.GET)
    @ResponseBody
    public String downLoad(@RequestParam("id") int apkID, HttpServletRequest request) {
        String relativePath = apkInfoService.getAbsolutePath(apkID);
        URLBuilder urlBuilder = new URLBuilder();
        urlBuilder.setScheme(request.getScheme());
        urlBuilder.setServerName(request.getServerName());
        urlBuilder.setDeploymentPorts(deploymentSettings.getHTTPPort(), deploymentSettings.getHTTPSPort());
        urlBuilder.setContextPath(servletContext.getContextPath());
        urlBuilder.setLogicalURL(relativePath);
        String path = urlBuilder.buildFullURL();
        String encodedURI = path;
        try {
            encodedURI = URLEncoder.encode(path, "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("encodedURI:" + encodedURI);
        return encodedURI;
    }

}
