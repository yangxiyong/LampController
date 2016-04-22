package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.RouteFilter;
import com.yj.intranet.lampcontroller.domain.Users;
import com.yj.intranet.lampcontroller.service.util.Page;

import java.util.List;

/**
 * @author yxy
 */
public interface UsersService {
    public Users load(String userName, String password);

    public Users findByNameOrId(String userName, int userID);

    public void createUser(Users user);

    public void remove(int userID);

    public void updateUser(Users user);

    public int updatePassword(String userName, String oldPwd, String newPwd);

    public List<Users> showAll();

    public SignInResultEntity clientLogin(String userName, String password);

    public Page find(String name, int pageNo, int pageSize);

    public Page findPage(int pageNo, int pageSize);

    public int checkPassword(String pwd, String name);

    public List<RouteFilter> getRouteFilter(int userId);

    public List<RouteFilter> listRouteFilterIncludeModelRoute(int userId);

    public SignInResultEntity clientLogin2(String userName, String password);

}
