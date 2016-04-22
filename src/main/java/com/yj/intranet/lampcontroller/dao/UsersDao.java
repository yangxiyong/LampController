package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.Users;

import java.util.List;

/**
 * @author yxy
 */
@MyBatisMapper
public interface UsersDao {
    public Users findOne(String userName, String password);

    public Users findByID(int userID);

    public Users findByName(String userName);

    public List<Users> queryByName(String userName, int pageNo, int pageSize);

    public void addUser(Users user);

    public void deleteUser(int userID);

    public void updateUser(Users user);

    public void updatePwd(String userName, String newPwd);

    public List<Users> listUser();

    public List<Users> listUserByPage(int pageNo, int pageSize);
}
