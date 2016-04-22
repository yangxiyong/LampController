package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.ApkInfo;
import com.yj.intranet.lampcontroller.service.util.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author yxy
 */
public interface ApkInfoService {

    public ApkInfo load(int apkID);

    public Page find(String versionId, int pageNo, int pageSize);

    public void create(ApkInfo apkInfo);

    public void remove(int apkID);

    public void update(ApkInfo apkInfo);

    public List<ApkInfo> showAll();

    public Page findPage(int pageNo, int pageSize);

    public ApkInfo getLastApkInfo();

    public String fileUpload(MultipartFile address, String path);

    public ApkInfo checkVesrionId(String apkName, String versionID);

    public String getAbsolutePath(int apkID);
}
