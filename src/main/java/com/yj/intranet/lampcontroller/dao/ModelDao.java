package com.yj.intranet.lampcontroller.dao;

import com.yj.core.database.MyBatisMapper;
import com.yj.intranet.lampcontroller.domain.Model;
import com.yj.intranet.lampcontroller.domain.Route;

import java.util.List;
import java.util.Map;

/**
 * @author yxy
 */
@MyBatisMapper
public interface ModelDao {
    public Model findOne(int modelId);

    public Model getModelByName(String modelName);

    public void addModel(Model model);

    public void deleteModel(int modelID);

    public void updateModel(Model model);

    public List<Model> listModel();

    public List<Model> listModelByPage(int pageNo, int pageSize);

    public List<Route> findOther(int modelID);

    public void saveModelRoute(Map<String, Object> mruMap);

    public void deleteModelFromModelRouteMapping(int modelID);

    public void bulkDelete(Map<String, Object> conditionMap);

    public List<Model> listSimpleModel();

}
