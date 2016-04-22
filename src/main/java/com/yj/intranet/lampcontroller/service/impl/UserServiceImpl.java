package com.yj.intranet.lampcontroller.service.impl;

import com.yj.intranet.lampcontroller.dao.UserDao;
import com.yj.intranet.lampcontroller.domain.User;
import com.yj.intranet.lampcontroller.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author mort
 */
@Service
@Singleton
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User load(String userName, String password) {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password))
            return null;
        return userDao.findOne(userName, password);
    }
}
