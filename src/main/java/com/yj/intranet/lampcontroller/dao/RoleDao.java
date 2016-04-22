package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.Role;

import java.util.List;

/**
 * @author yxy
 */
@MyBatisMapper
public interface RoleDao {

    public List<Role> findRolesByUserId(int userID);

    public Role findOne(int roleID);

    public void addRole(Role role);

    public void deleteRole(int roleID);

    public void updateRole(Role role);

    public List<Role> listRole();

}
