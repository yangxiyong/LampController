package com.yj.intranet.lampcontroller.web.controller;

import com.yj.intranet.lampcontroller.domain.Model;
import com.yj.intranet.lampcontroller.domain.ModelRoute;
import com.yj.intranet.lampcontroller.domain.Route;
import com.yj.intranet.lampcontroller.platform.controller.PlatformMasterController;
import com.yj.intranet.lampcontroller.service.ModelService;
import com.yj.intranet.lampcontroller.service.RouteService;
import com.yj.intranet.lampcontroller.service.util.Page;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.form.QueryPageForm;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yxy on 2014/12/29.
 */
@Controller
@LoginRequired
@RequestMapping(value = URLConstants.URL_MODEL_PREFIX)
public class ModelController extends PlatformMasterController {

    @Inject
    private ModelService modelService;
    @Inject
    private RouteService routeService;

    private Model mModel;

    @RequestMapping(value = URLConstants.URL_COMMON_FIND, method = RequestMethod.POST)
    public String loadModel(@RequestParam("modelId") int modelID, Map<String, Object> model) {
        if (modelID != 0) {
            mModel = modelService.findById(modelID);
            model.put("bean", mModel);
        }
        return "model_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_UPDATE, method = RequestMethod.POST)
    public String updateModel(@Valid Model model, Map<String, Object> viewData) {
        modelService.update(model);
        Page page = modelService.findPage(1, 0);
        viewData.put("page", page);
        return "model_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_EDIT, method = RequestMethod.GET)
    public String editModel(@RequestParam("id") int modelID, Map<String, Object> viewData) {
        System.out.println(" edit modelID:" + modelID);
        if (modelID == 0) {
            return "model_add";
        } else {
            mModel = modelService.findById(modelID);
            viewData.put("bean", mModel);
        }
        return "model_update";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_ADD, method = RequestMethod.POST)
    public String addModel(@Valid Model model, Map<String, Object> viewData) {
        modelService.create(model);
        Page page = modelService.findPage(1, 0);
        viewData.put("page", page);
        return "model_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_DELETE, method = RequestMethod.GET)
    public String deleteModel(@RequestParam("id") int modelID, Map<String, Object> viewData) {
        modelService.remove(modelID);
        Page page = modelService.findPage(1, 0);
        viewData.put("page", page);
        return "model_main";
    }

    //进入model main主页
    @RequestMapping(value = URLConstants.URL_PAGE, method = RequestMethod.GET)
    public String queryPage(@Valid QueryPageForm queryPageForm, Map<String, Object> viewData) {
        Page page = modelService.findPage(queryPageForm.getPageNo(), queryPageForm.getPageSize());
        viewData.put("page", page);
        return "model_main";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_ADVANCE, method = RequestMethod.GET)
    public String showModelRoute(@RequestParam("id") int modelID, Map<String, Object> viewData) {
        if (modelID != 0) {
            List<Route> list = new ArrayList();
            mModel = modelService.findById(modelID);
            //System.out.println("mModel size:" + mModel.getRoutes().size());
            list = routeService.showAll();
            //System.out.println("list size:" + list.size());
            //list.removeAll(mModel.getRoutes());//当MODELROUTEMAPPING有数据时,removeAll失效
            List<ModelRoute> routeList = mModel.getRoutes();
            List<Route> tmList = new ArrayList();
            System.out.println("routeList size:" + routeList.size());
            for (int j = 0; j < list.size(); j++) {
                for (int i = 0; i < routeList.size(); i++) {
                    if (list.get(j).getRouteID() == routeList.get(i).getRouteID()) {
                        //System.out.println("getRouteName s:" + list.get(j).getRouteName());
                        //list.remove(j);
                        tmList.add(list.get(j));
                        break;
                    }
                }
            }
            list.removeAll(tmList);
            //System.out.println("other list size:" + list.size());
            viewData.put("otherRoutes", list);
            viewData.put("bean", mModel);
        }
        return "model_route";
    }

    @RequestMapping(value = URLConstants.URL_COMMON_SAVE, method = RequestMethod.POST)
    public String allocateRoute(@RequestParam("temp_routeId_val") String routeIDs, @RequestParam("param_modelId") int modelID,
                                @RequestParam("param_userName") String userName, Map<String, Object> viewData) {
        System.out.println("temp_routeId_val:" + routeIDs);
        modelService.allocate(routeIDs, modelID, userName);
        Page page = modelService.findPage(1, 0);
        viewData.put("page", page);
        return "model_main";
    }
}
