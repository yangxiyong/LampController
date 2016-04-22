package com.yj.intranet.lampcontroller.service;

import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.service.util.Page;

import java.util.List;


/**
 * @author yxy
 */
public interface ControlService {

    public Control load(int controlID);

    public Page find(String name, int pageNo, int pageSize);

    public void createControl(Control control);

    public void remove(int controlID);

    public void updateControl(Control control);

    public List<Control> showAll();

    public Page findPage(int pageNo, int pageSize);

    public Control getByName(String controlName);

    public Control getByIP(String controlIP);
}
