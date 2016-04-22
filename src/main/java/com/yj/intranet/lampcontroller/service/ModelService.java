package com.yj.intranet.lampcontroller.service;


import com.yj.intranet.lampcontroller.domain.Model;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.service.util.Page;

import java.util.List;

/**
 * @author yxy
 */
public interface ModelService {

    public List load(int modelID, String userName);

    public Model findById(int modelID);

    public Model getByName(String modelName);

    public void create(Model model);

    public void remove(int modelID);

    public void update(Model model);

    public List<Model> showAll();

    public Page findPage(int pageNo, int pageSize);

    public List<Route> findOtherRoute(int modelID);

    public void allocate(String routeIdStr, int groupID, String userName);


    public List<Model> showSimpleAll();

}
