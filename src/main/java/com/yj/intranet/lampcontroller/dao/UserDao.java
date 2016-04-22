package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.User;

/**
 * @author mort
 */
@MyBatisMapper
public interface UserDao {
    public User findOne(String userName, String password);
}
