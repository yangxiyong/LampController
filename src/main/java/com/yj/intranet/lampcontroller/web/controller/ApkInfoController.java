package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.domain.ApkInfo;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.ApkInfoService;
import com.yj.intranet.lampcontroller.service.util.Page;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.QueryPageForm;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2014/12/30.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_APK_PREFIX)
public class ApkInfoController extends PlatformMasterController {

    @Inject
    private ServletContext servletContext;

    @Inject
    private ApkInfoService apkInfoService;

    private ApkInfo mApkInfo;
    private CommonsMultipartResolver multipartResolver;
    private MultipartFile multipartFile;

    @RequestMapping(value = URLConstants.URL_COMMON_FIND, method = RequestMethod.POST)
    public String load(@RequestParam("id") int apkID, Map<String, Object> model) {

        if (apkID != 0) {
            mApkInfo = apkInfoService.load(apkID);
            model.put("bean", mApkInfo);
        }
        return "apkInfo_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_UPDATE, method = RequestMethod.POST)
    public String update(@Valid ApkInfo apkInfo, Map<String, Object> model) {
        apkInfoService.update(apkInfo);
        Page page = apkInfoService.findPage(1, 15);
        model.put("page", page);
        return "apkInfo_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_EDIT, method = RequestMethod.GET)
    public String edit(@RequestParam("id") int apkID, Map<String, Object> model) {
        if (apkID == 0) {
            return "apkInfo_add";
        } else {
            mApkInfo = apkInfoService.load(apkID);
            model.put("bean", mApkInfo);
        }
        return "apkInfo_update";
    }

    /**
     * 非注解的servlet上传文件
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = URLConstants.URL_COMMON_ADD, method = RequestMethod.POST)
    public String add(Map<String, Object> model, HttpServletRequest request) {
        long currentTimeMillis = System.currentTimeMillis();
        // request.getSession().getServletContext().getContextPath()
        String realPath;
        String relativePath;
        String contextPath = "/assets/downloads/";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置1M的缓冲区,这个值决定了是fileinputstream还是bytearrayinputstream
        factory.setSizeThreshold(1024 * 1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(10 * 1024 * 1024);
        upload.setSizeMax(20 * 1024 * 1024);
        //处理上传路径乱码设置
        upload.setHeaderEncoding("UTF-8");
        try {
            List items = upload.parseRequest(request);
            Iterator ite = items.iterator();
            ApkInfo apk = new ApkInfo();
            while (ite.hasNext()) {
                FileItem item = (FileItem) ite.next();
                //非文件流,表单参数
                if (item.isFormField()) {
                    //处理表单域字段值乱码
                    switch (item.getFieldName()) {
                        case "apkName":
                            apk.setApkName(item.getString("UTF-8"));
                            break;
                        case "versionID":
                            apk.setVersionID(item.getString());
                            break;
                        case "whatNews":
                            apk.setWhatNews(item.getString("UTF-8"));
                            break;
                        case "createBy":
                            apk.setCreateBy(item.getString("UTF-8"));
                            break;
                        default:
                            break;
                    }
                } else {
                    //文件的全路径
                    String name = item.getName();
                    String fileName, newName, extName = "";
                    boolean flag = true;
                    //获得后缀
                    if (name.lastIndexOf(".") >= 0) {
                        extName = name.substring(name.lastIndexOf("."));
                    }
                    fileName = name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf("."));
                    //允许上传的文件类型
                    List fileTypes = new ArrayList();
                    fileTypes.add(".apk");
                    fileTypes.add(".txt");
                    if (!fileTypes.contains(extName.toLowerCase())) {
                        flag = false;
                    }
                    if (flag) {
                        newName = fileName + "_" + currentTimeMillis;
                        relativePath = contextPath + newName + extName;
                        realPath = servletContext.getRealPath(relativePath);
                        //相对地址
                        File newFile = new File(realPath);
                        item.write(newFile);
                        apk.setAddress(relativePath);
                        // model.put("successFile:", relativePath);
                    }
                }
            }
            apkInfoService.create(apk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    /*  URLBuilder builder = new URLBuilder();
        builder.setContextPath(servletContext.getContextPath());
        builder.setLogicalURL();
        builder.buildFullURL()*/
        Page page = apkInfoService.findPage(1, 15);
        model.put("page", page);
        return "apkInfo_main";
    }

    @RequestMapping(value = URLConstants.URL_PAGE_ASY, method = RequestMethod.POST)
    public String addAPK(Map<String, Object> model) {
       /*  String contextPath = request.getSession().getServletContext().getContextPath();
        //SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        long currentTimeMillis= System.currentTimeMillis();
        multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(100*1024*1024);
        multipartResolver.setMaxInMemorySize(10*1024*1024);

        if(multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest=multipartResolver.resolveMultipart(request);
            multipartFile = multipartHttpServletRequest.getFile("address");
        }
        String fullFileName=multipartFile.getOriginalFilename();
        String fileName=fullFileName.substring(0,fullFileName.lastIndexOf("."));//不带扩展名的文件名
        String suffix=fullFileName.substring(fullFileName.lastIndexOf(".")); //后缀
        String relativePathr="/assets/downloads/"+fileName +currentTimeMillis+suffix;
        String realPath=contextPath+relativePathr;
        ApkInfo apkInfo=new ApkInfo();
        apkInfo.setApkName(request.getParameter("apkName"));
        apkInfo.setVersionID(request.getParameter("versionID"));
        apkInfo.setWhatNews(request.getParameter("whatNews"));
        apkInfo.setCreateBy(request.getParameter("createBy"));
      //  String path=apkInfoService.fileUpload(apkForm.getAddress());
        String path=apkInfoService.fileUpload(multipartFile,realPath);*/
       /*method2:
        File file = new File()
        multipartFile.transferTo();
        if(!path.equals("")){
            apkInfo.setAddress(path);
            apkInfoService.create(apkInfo);
        }*/
        Page page = apkInfoService.findPage(1, 15);
        model.put("page", page);
        return "apkInfo_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam("id") int apkID, Map<String, Object> model) {
        apkInfoService.remove(apkID);
        Page page = apkInfoService.findPage(1, 15);
        model.put("page", page);
        return "apkInfo_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_FIND_NAME, method = RequestMethod.POST)
    public String find(@RequestParam("name") String apkName, Map<String, Object> model) {
        if (apkName != null || !apkName.isEmpty()) {
            Page page = apkInfoService.find(apkName, 1, 15);
            model.put("page", page);
        }
        return "apkInfo_main";
    }

    //进入apkInfo main主页
    @RequestMapping(value = URLConstants.URL_PAGE, method = RequestMethod.GET)
    public String queryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> model) {
        Page page = apkInfoService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        model.put("page", page);
        return "apkInfo_main";
    }

}
