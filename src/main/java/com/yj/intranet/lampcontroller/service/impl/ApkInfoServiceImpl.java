package com.yj.intranet.lampcontroller.service.impl;


import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.dao.ApkInfoDao;
import com.yj.intranet.lampcontroller.domain.ApkInfo;
import com.yj.intranet.lampcontroller.service.ApkInfoService;
import com.yj.intranet.lampcontroller.service.util.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.List;

/**
 * @author yxy
 */
@Service
@Singleton
public class ApkInfoServiceImpl implements ApkInfoService {
    @Inject
    private ApkInfoDao apkInfoDao;

    @Inject
    private ServletContext servletContext;


    private static String uploadFilePath = "d:\\test\\";
    private String fileName;

    @Override
    public ApkInfo load(int apkID) {
        if (apkID == 0)
            return null;
        return apkInfoDao.findOne(apkID);
    }

    @Override
    public Page find(String versionId, int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        List<ApkInfo> list = apkInfoDao.queryByVersionId(versionId, 1, 0);
        int totalCount = list.size();
        page.setTotalCount(totalCount);
        List<ApkInfo> apkInfoPageList = apkInfoDao.queryByVersionId(versionId, 1, 15);
        page.setList(apkInfoPageList);
        return page;
    }

    @Override
    public void create(ApkInfo apkInfo) {
        if (apkInfo != null) {
            apkInfoDao.addApkInfo(apkInfo);
        }
    }

    @Override
    public void remove(int apkID) {
        if (apkID != 0) {
            apkInfoDao.deleteApkInfo(apkID);
        }
    }

    @Override
    public void update(ApkInfo apkInfo) {
        if (apkInfo != null) {
            apkInfoDao.updateApkInfo(apkInfo);
        }
    }

    @Override
    public List<ApkInfo> showAll() {
        return apkInfoDao.listApkInfo();
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page findPage(int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        List<ApkInfo> list = apkInfoDao.listApkInfo();
        page.setTotalCount(list.size());
        List<ApkInfo> apkInfoPageList = apkInfoDao.listApkInfoByPage(offset, page.getPageSize());
        page.setList(apkInfoPageList);
        return page;
    }

    @Override
    public ApkInfo getLastApkInfo() {
        return apkInfoDao.findLastID();
    }

    @Override
    public String fileUpload(MultipartFile address, String path) {
        try {
            System.out.println("=============== path:" + path);
            MultipartFile uploadFile = address;
            fileName = uploadFile.getOriginalFilename();
            InputStream is = uploadFile.getInputStream();
            File tempFile = new File(path);
            if (tempFile.exists()) {
                boolean delResult = tempFile.delete();
                System.out.println("删除已存在的文件:" + delResult);
            }
            if (!"".equals(fileName)) {
                FileOutputStream fos = new FileOutputStream(path);
                byte[] buffer = new byte[8129];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        System.out.println("path:" + path);
        return path;
    }

    @Override
    public ApkInfo checkVesrionId(String apkName, String versionID) {
        if (StringUtils.hasText(apkName) && StringUtils.hasText(versionID))
            return apkInfoDao.checkNameVersion(apkName, versionID);
        return null;
    }

    @Override
    public String getAbsolutePath(int apkID) {
        if (apkID != 0) {
            ApkInfo apk = apkInfoDao.findOne(apkID);
            if (apk != null) {
                return apk.getAddress();
            }
        }
        return "#";
    }
}
