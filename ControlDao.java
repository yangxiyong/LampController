package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.Control;

import java.util.List;

/**
 * @author yxy
 */
@MyBatisMapper
public interface ControlDao {

    public Control getControlByID(int controlID);

    public Control getControlByName(String controlName);

    public Control getControlByIP(String controlIP);

    public void addControl(Control control);

    public void deleteControl(int controlID);

    public void updateControl(Control control);

    public List<Control> listControl();

    public List<Control> listControlByPage(int pageNo, int pageSize);

    public List<Control> findByName(String controlName, int pageNo, int pageSize);
}
