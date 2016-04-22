package com.yj.intranet.lampcontroller.service.impl;


import com.yj.core.platform.web.site.session.SessionContext;
import com.yj.core.util.StringUtils;
import com.yj.intranet.lampcontroller.dao.GroupsDao;
import com.yj.intranet.lampcontroller.dao.ModelDao;
import com.yj.intranet.lampcontroller.dao.RouteFilterDao;
import com.yj.intranet.lampcontroller.dao.UsersDao;
import com.yj.intranet.lampcontroller.domain.*;
import com.yj.intranet.lampcontroller.service.SignInResultEntity;
import com.yj.intranet.lampcontroller.service.UsersService;
import com.yj.intranet.lampcontroller.service.util.Page;
import com.yj.intranet.lampcontroller.web.constants.SessionConstants;
import com.yj.intranet.lampcontroller.web.util.HashUtil;
import com.yj.intranet.lampcontroller.web.view.BackClientDataArea;
import com.yj.intranet.lampcontroller.web.view.BackFilterRoutesDataArea;
import com.yj.intranet.lampcontroller.web.view.BackModelDataArea;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yxy
 */
@Service
@Singleton
public class UsersServiceImpl implements UsersService {
    @Inject
    private UsersDao usersDao;
    @Inject
    private GroupsDao groupsDao;
    @Inject
    private ModelDao modelDao;
    @Inject
    private RouteFilterDao routeFilterDao;
    @Inject
    private SessionContext sessionContext;

    @Override
    public SignInResultEntity clientLogin2(String userName, String password) {
        SignInResultEntity signInResultEntity = this.signIn(userName, password);
        if (signInResultEntity == null) {
            return null;
        }
        //查询用户是否存在过滤条件
        //查询出过滤条件含模式不含线路
        List<RouteFilter> routeFiltersList = this.getRouteFilter(signInResultEntity.getUser().getUserID());
        if (routeFiltersList != null) {
            haveFilter(signInResultEntity);
        } else {
            noFilter(signInResultEntity);
        }
        return signInResultEntity;
    }

    /**
     * 有过滤需求的登录
     *
     * @param signInResultEntity
     */
    public void haveFilter(SignInResultEntity signInResultEntity) {
        //查询出过滤条件含模式含线路
        List<RouteFilter> routeFilterDetailList = this.listRouteFilterIncludeModelRoute(signInResultEntity.getUser().getUserID());
        //1.获取线路的数量(含过滤条件) 默认第一个
        //默认第一个filter
        RouteFilter routeFilter = this.routeFilterDao.getFilterRoutesByFilterId(signInResultEntity.getUser().getUserID(), 0);
        BackFilterRoutesDataArea backFilterRoutesDataArea = new BackFilterRoutesDataArea();
        backFilterRoutesDataArea.setFilterId(routeFilter.getId());
        backFilterRoutesDataArea.setFilterName(routeFilter.getFilterName());
        List<BackClientDataArea> routeDataList = new ArrayList();
        for (Route route : routeFilter.getRoutes()) {
            BackClientDataArea clientDataArea = new BackClientDataArea();
            clientDataArea.setRouteId(route.getRouteID());
            clientDataArea.setRouteNo(route.getRouteNo());
            clientDataArea.setRouteName(route.getRouteName());
            clientDataArea.setControlIP(route.getControl().getControlIP());
            clientDataArea.setControlPort(route.getControl().getControlPort());
            routeDataList.add(clientDataArea);
        }
        backFilterRoutesDataArea.setBackClientDataAreaList(routeDataList);
        signInResultEntity.setFilterRoutesDataArea(backFilterRoutesDataArea);
        signInResultEntity.setRouteFilterDetailList(routeFilterDetailList);
           /*  1.获取线路的数量（含过滤条件，全部罗列）
            List<BackFilterRoutesDataArea> filterRoutesDataList = new ArrayList();
            List<RouteFilter> filterRoutesList= this.routeFilterDao.listFilterRoutes(signInResultEntity.getUser().getUserID());
            for(int i=0;i< filterRoutesList.size();i++){
                BackFilterRoutesDataArea backFilterRoutesDataArea= new BackFilterRoutesDataArea();
                backFilterRoutesDataArea.setFilterId(filterRoutesList.get(i).getId());
                backFilterRoutesDataArea.setFilterName(filterRoutesList.get(i).getFilterName());
                List<BackClientDataArea> routeDataList = new ArrayList();
                for (Route route : filterRoutesList.get(i).getRoutes()) {
                    BackClientDataArea clientDataArea = new BackClientDataArea();
                    clientDataArea.setRouteId(route.getRouteID());
                    clientDataArea.setRouteNo(route.getRouteNo());
                    clientDataArea.setRouteName(route.getRouteName());
                    clientDataArea.setControlIP(route.getControl().getControlIP());
                    clientDataArea.setControlPort(route.getControl().getControlPort());
                    routeDataList.add(clientDataArea);
                }
                backFilterRoutesDataArea.setBackClientDataAreaList(routeDataList);
                filterRoutesDataList.add(backFilterRoutesDataArea);
            }
            signInResultEntity.setFilterRoutesDataList(filterRoutesDataList);
            //signInResultEntity.setFilterRoutesList(filterRoutesList);
            */
    }

    /**
     * 无过滤需求的登录
     *
     * @param signInResultEntity
     */
    public void noFilter(SignInResultEntity signInResultEntity) {
        List<BackClientDataArea> dataList = new ArrayList();
        List<Route> list;
        List<BackModelDataArea> modelDataAreaListsList = new ArrayList();
        //1.获取线路的数量
        int groupId = signInResultEntity.getGroup().getGroupID();
        Groups group = groupsDao.findOne(groupId);
        list = group.getRoutes();
        for (Route route : list) {
            BackClientDataArea clientDataArea = new BackClientDataArea();
            clientDataArea.setRouteId(route.getRouteID());
            clientDataArea.setRouteNo(route.getRouteNo());
            clientDataArea.setRouteName(route.getRouteName());
            clientDataArea.setControlIP(route.getControl().getControlIP());
            clientDataArea.setControlPort(route.getControl().getControlPort());
            dataList.add(clientDataArea);
        }
        signInResultEntity.setBackClientDataAreaList(dataList);

        //2.模式线路相关 （不存在过滤条件的情况下）
        List<Model> modelList = modelDao.listModel();
        if (modelList != null) {
            for (Model model : modelList) {
                BackModelDataArea modelDataArea = new BackModelDataArea();
                modelDataArea.setModelId(model.getModelID());
                modelDataArea.setModelName(model.getModelName());
                List<ModelRoute> routeList = model.getRoutes();
                //特定模式下对应的线路集合，需要重置
                List<BackClientDataArea> simpleRoutesList = new ArrayList();
                for (ModelRoute modelRoute : routeList) {
                    BackClientDataArea simpleRouteArea = new BackClientDataArea();
                    simpleRouteArea.setRouteNo(modelRoute.getRouteNo());
                    simpleRouteArea.setRouteName(modelRoute.getRouteName());
                    simpleRouteArea.setSwitchStatus(modelRoute.getSwitchStatus());
                    simpleRouteArea.setControlIP(modelRoute.getControl().getControlIP());
                    simpleRouteArea.setControlPort(modelRoute.getControl().getControlPort());
                    simpleRoutesList.add(simpleRouteArea);
                }
                modelDataArea.setRoutes(simpleRoutesList);
                modelDataAreaListsList.add(modelDataArea);
            }
        }
        signInResultEntity.setModelsList(modelDataAreaListsList);
    }

    /**
     * 无过滤需求的登录
     * deprecated
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public SignInResultEntity clientLogin(String userName, String password) {
        List<BackClientDataArea> dataList = new ArrayList();
        List<Route> list;
        List<BackModelDataArea> modelDataAreaListsList = new ArrayList();
        SignInResultEntity signInResultEntity = this.signIn(userName, password);
        if (signInResultEntity == null) {
            return null;
        }
        int groupId = signInResultEntity.getGroup().getGroupID();
        Groups group = groupsDao.findOne(groupId);
        list = group.getRoutes();
        for (Route route : list) {
            BackClientDataArea clientDataArea = new BackClientDataArea();
            clientDataArea.setRouteId(route.getRouteID());
            clientDataArea.setRouteNo(route.getRouteNo());
            clientDataArea.setRouteName(route.getRouteName());
            clientDataArea.setControlIP(route.getControl().getControlIP());
            clientDataArea.setControlPort(route.getControl().getControlPort());
            dataList.add(clientDataArea);
        }
        signInResultEntity.setBackClientDataAreaList(dataList);
        //查询用户是否存在过滤条件
        //List<RouteFilter> routeFiltersList=this.getRouteFilter(signInResultEntity.getUser().getUserID()); //查询出过滤条件含模式不含线路
        //查询出过滤条件含模式含线路
        List<RouteFilter> routeFilterDetailList = this.listRouteFilterIncludeModelRoute(signInResultEntity.getUser().getUserID());
        if (routeFilterDetailList != null) {
            signInResultEntity.setRouteFilterDetailList(routeFilterDetailList);
        } else {
            //模式线路相关 （不存在过滤条件的情况下）
            List<Model> modelList = modelDao.listModel();
            if (modelList != null) {
                for (Model model : modelList) {
                    BackModelDataArea modelDataArea = new BackModelDataArea();
                    modelDataArea.setModelId(model.getModelID());
                    modelDataArea.setModelName(model.getModelName());
                    List<ModelRoute> routeList = model.getRoutes();
                    //特定模式下对应的线路集合，需要重置
                    List<BackClientDataArea> simpleRoutesList = new ArrayList();
                    for (ModelRoute modelRoute : routeList) {
                        BackClientDataArea simpleRouteArea = new BackClientDataArea();
                        simpleRouteArea.setRouteNo(modelRoute.getRouteNo());
                        simpleRouteArea.setRouteName(modelRoute.getRouteName());
                        simpleRouteArea.setSwitchStatus(modelRoute.getSwitchStatus());
                        simpleRouteArea.setControlIP(modelRoute.getControl().getControlIP());
                        simpleRouteArea.setControlPort(modelRoute.getControl().getControlPort());
                        simpleRoutesList.add(simpleRouteArea);
                    }
                    modelDataArea.setRoutes(simpleRoutesList);
                    modelDataAreaListsList.add(modelDataArea);
                }
            }
            signInResultEntity.setModelsList(modelDataAreaListsList);
        }
        return signInResultEntity;
    }

    public Users load(String userName, String password) {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            return null;
        }
        //HashUtil.algorithmMD5(password)
        return usersDao.findOne(userName, HashUtil.algorithmMD5(password));
    }

    public SignInResultEntity signIn(String userName, String password) {
        SignInResultEntity signInResultEntity = new SignInResultEntity();
        Users user = this.findByNameOrId(userName, 0);
        Groups group = groupsDao.findByUserName(userName);
        if (user == null) {
            return null;
        }
        String pwd = user.getPasswords();
        String pwdMD5 = HashUtil.algorithmMD5(password);
        if (!pwd.equals(pwdMD5)) {
            return null;
        }
        signInResultEntity.setUser(user);
        signInResultEntity.setGroup(group);
        signInResultEntity.setStatus(1);
        return signInResultEntity;
    }

    @Override
    public Users findByNameOrId(String userName, int userID) {
        if (StringUtils.hasText(userName)) {
            return usersDao.findByName(userName);
        }
        if (userID != 0) {
            return usersDao.findByID(userID);
        }
        return null;
    }

    @Override
    public void createUser(Users user) {
        user.setPasswords(HashUtil.algorithmMD5(user.getPasswords()));
        if (user != null) {
            usersDao.addUser(user);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void remove(int userID) {
        if (userID != 0) {
            usersDao.deleteUser(userID);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateUser(Users user) {
        if (user != null) {
            usersDao.updateUser(user);
            if (sessionContext.get(SessionConstants.CURRENT_USER_ID).equals(String.valueOf(user.getUserID()))) {
                sessionContext.set(SessionConstants.CURRENT_USER, user.getUserName());
                sessionContext.set(SessionConstants.CURRENT_USER_GROUP, String.valueOf(user.getGroup().getGroupID()));
            }
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int updatePassword(String userName, String oldPwd, String newPwd) {
        Users user = this.findByNameOrId(userName, 0);
        if (!StringUtils.hasText(newPwd) || user == null || !user.getPasswords().equals(HashUtil.algorithmMD5(oldPwd)) || user.getPasswords().equals(HashUtil.algorithmMD5(newPwd))) {
            return 0;
        }
        usersDao.updatePwd(userName, HashUtil.algorithmMD5(newPwd));
        return 1;
    }

    @Override
    public List<Users> showAll() {
        return usersDao.listUser();
    }

    @Override
    public Page find(String name, int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        List<Users> list = usersDao.queryByName(name, 1, 0);
        int totalCount = list.size();
        page.setTotalCount(totalCount);
        List<Users> pageList = usersDao.queryByName(name, 1, 3);
        page.setList(pageList);
        return page;
    }

    @Override
    public Page findPage(int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        //查询总记录数
        //int totalCount = usersDao.findTotalCount();
        //page.setTotalCount(totalCount);
        //查询出所有记录 对sql追加limit
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        //  StringBuffer sb = new StringBuffer();
        // sb.append(" limit ").append(offset).append(",").append(page.getPageSize());
        List<Users> userPageList = usersDao.listUserByPage(offset, page.getPageSize());
        page.setList(userPageList);
        int totalCount = userPageList.size();
        page.setTotalCount(totalCount);
        return page;
    }

    public int checkPassword(String pwd, String name) {
        String str = sessionContext.get(SessionConstants.CURRENT_USER_PWD);
        if (StringUtils.hasText(str)) {
            if (StringUtils.hasText(pwd) && str.equals(HashUtil.algorithmMD5(pwd))) {
                return 1;
            }
        } else {
            Users user = this.load(name, pwd);
            if (user.getPasswords().equals(HashUtil.algorithmMD5(pwd))) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 根据用户ID获取对应的过滤条件
     *
     * @param userId
     * @return
     */
    @Override
    public List<RouteFilter> getRouteFilter(int userId) {
        return this.routeFilterDao.listRouteFilter(userId);
    }

    /**
     * 根据用户ID获取对应的过滤条件
     *
     * @param userId
     * @return
     */
    @Override
    public List<RouteFilter> listRouteFilterIncludeModelRoute(int userId) {
        return this.routeFilterDao.listRouteFilterIncludeModelRoute(userId);
    }
}