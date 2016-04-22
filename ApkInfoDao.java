package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.ApkInfo;

import java.util.List;

/**
 * @author yxy
 */
@MyBatisMapper
public interface ApkInfoDao {


    public ApkInfo findOne(int apkID);

    public void addApkInfo(ApkInfo apkInfo);

    public void deleteApkInfo(int apkID);

    public void updateApkInfo(ApkInfo apkInfo);

    public List<ApkInfo> listApkInfo();

    public List<ApkInfo> listApkInfoByPage(int pageNo, int pageSize);

    public List<ApkInfo> queryByVersionId(String versionID, int pageNo, int pageSize);

    public ApkInfo findLastID();

    public ApkInfo checkNameVersion(String apkName, String versionID);
}
