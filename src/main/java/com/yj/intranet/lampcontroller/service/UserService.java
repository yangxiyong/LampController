package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.User;

/**
 * @author mort
 */
public interface UserService {
    public User load(String userName, String password);
}
