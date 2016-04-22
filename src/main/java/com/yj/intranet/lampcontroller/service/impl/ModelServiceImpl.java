package com.yj.intranet.lampcontroller.service.impl;

import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.dao.ModelDao;
import com.yj.intranet.lampcontroller.domain.Model;
import com.yj.intranet.lampcontroller.domain.ModelRoute;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.service.ModelService;
import com.yj.intranet.lampcontroller.service.util.Page;
import com.yj.intranet.lampcontroller.web.view.BackClientDataArea;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yxy
 */
@Service
@Singleton
public class ModelServiceImpl implements ModelService {

    @Inject
    ModelDao modelDao;

    @Override
    public List load(int modelId, String userName) {
        List<BackClientDataArea> routeDataAreaList = new ArrayList();
        Model model = modelDao.findOne(modelId);
        List<ModelRoute> list = model.getRoutes();
        if (list == null) {
            return null;
        }
        for (ModelRoute route : list) {
            BackClientDataArea clientDataArea = new BackClientDataArea();
            clientDataArea.setRouteNo(route.getRouteNo());
            clientDataArea.setRouteName(route.getRouteName());
            clientDataArea.setControlIP(route.getControl().getControlIP());
            clientDataArea.setControlPort(route.getControl().getControlPort());
            System.out.println("modelService getSwitchStatus:" + route.getSwitchStatus());
            clientDataArea.setSwitchStatus(route.getSwitchStatus());
            routeDataAreaList.add(clientDataArea);
        }
        return routeDataAreaList;
    }

    @Override
    public Model findById(int modelID) {
        if (modelID != 0) {
            System.out.println("modelID != 0:");
            return modelDao.findOne(modelID);
        }
        System.out.println("modelID == 0:");
        return null;
    }

    @Override
    public Model getByName(String modelName) {
        if (!StringUtils.hasText(modelName))
            return null;
        return modelDao.getModelByName(modelName);
    }

    @Override
    public void create(Model model) {
        if (model != null) {
            modelDao.addModel(model);
        }
    }

    @Override
    public void remove(int modelID) {
        if (modelID != 0) {
            modelDao.deleteModel(modelID);
        }
    }

    @Override
    public void update(Model model) {
        if (model != null) {
            modelDao.updateModel(model);
        }
    }

    @Override
    public List<Model> showAll() {
        return modelDao.listModel();
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
        List<Model> modelPageList = modelDao.listModelByPage(offset, page.getPageSize());
        page.setList(modelPageList);
        int totalCount = modelPageList.size();
        page.setTotalCount(totalCount);
        return page;
    }

    public List<Route> findOtherRoute(int modelID) {
        return modelDao.findOther(modelID);
    }

    @Transactional(rollbackOn = Exception.class)
    public void allocate(String routeIdStr, int modelID, String userName) {
        String[] str = routeIdStr.split(";");
        List<ModelRoute> mrlist = new ArrayList();
        for (int i = 0; i < str.length; i++) {
            String[] str2 = str[i].split(",");
            ModelRoute route = new ModelRoute();
            route.setRouteID(Integer.valueOf(str2[0]));
            route.setSwitchStatus(Integer.valueOf(str2[1]));
            mrlist.add(route);
        }
        Map<String, Object> map = new HashMap();
        map.put("mId", modelID);
        map.put("uName", userName);
        map.put("list", mrlist);
        modelDao.deleteModelFromModelRouteMapping(modelID);
        modelDao.saveModelRoute(map);
    }


    //add in 2015-05-25
    @Override
    public List<Model> showSimpleAll() {
        return modelDao.listSimpleModel();
    }


}
