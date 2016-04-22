package com.yj.intranet.lampcontroller.service.impl;


import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.dao.ControlDao;
import com.yj.intranet.lampcontroller.domain.Control;
import com.yj.intranet.lampcontroller.service.ControlService;
import com.yj.intranet.lampcontroller.service.util.Page;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author yxy
 */
@Service
@Singleton
public class ControlServiceImpl implements ControlService {
    @Inject
    private ControlDao controlDao;

    @Override
    public Control load(int controlID) {
        if (controlID == 0)
            return null;
        return controlDao.getControlByID(controlID);
    }

    @Override
    public Control getByName(String controlName) {
        if (!StringUtils.hasText(controlName))
            return null;
        return controlDao.getControlByName(controlName);
    }

    @Override
    public Control getByIP(String controlIP) {
        if (!StringUtils.hasText(controlIP))
            return null;
        return controlDao.getControlByIP(controlIP);
    }

    @Override
    public Page find(String name, int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        List<Control> list = controlDao.findByName(name, 1, 0);
        int totalCount = list.size();
        page.setTotalCount(totalCount);
        List<Control> controlPageList = controlDao.findByName(name, 1, 3);
        page.setList(controlPageList);
        return page;
    }

    @Override
    public void createControl(Control control) {
        if (control != null) {
            controlDao.addControl(control);
        }
    }

    @Override
    public void remove(int controlID) {
        if (controlID != 0) {
            controlDao.deleteControl(controlID);
        }
    }

    @Override
    public void updateControl(Control control) {
        if (control != null) {
            controlDao.updateControl(control);
        }
    }

    @Override
    public List<Control> showAll() {
        return controlDao.listControl();
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
        List<Control> list = controlDao.listControl();
        page.setTotalCount(list.size());
        List<Control> controlPageList = controlDao.listControlByPage(offset, page.getPageSize());
        page.setList(controlPageList);
        return page;
    }

}
