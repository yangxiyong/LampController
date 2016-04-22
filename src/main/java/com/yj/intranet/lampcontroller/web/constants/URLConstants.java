package com.yj.intranet.lampcontroller.web.constants;

/**
 * @author mort
 */
public class URLConstants {
    //login
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGIN_SUBMIT = "/login-submit";
    public static final String URL_LOGOUT = "/logout";

    //home
    public static final String URL_HOME = "/home";
    public static final String URL_PAGE = "/page";
    public static final String URL_PAGE_ASY = "/asypage";

    //common action
    public static final String URL_COMMON_EDIT = "/edit";
    public static final String URL_COMMON_ADD = "/add";
    public static final String URL_COMMON_DELETE = "/delete";
    public static final String URL_COMMON_UPDATE = "/update";
    public static final String URL_COMMON_FIND = "/find";
    public static final String URL_COMMON_FIND_NAME = "/find_name";
    public static final String URL_COMMON_ADVANCE = "/advance";
    public static final String URL_COMMON_SAVE = "/save";
    public static final String URL_COMMON_CHECK_NAME = "/check_name";
    public static final String URL_COMMON_DOWN_LOAD = "/down_load";
    //groups
    public static final String URL_GROUP_PREFIX = "/group";
    //filter
    public static final String URL_FILTER_PREFIX = "/filter";
    public static final String URL_ADVANCE_FILTER_MODEL = "/advanceModel";
    public static final String URL_ADVANCE_FILTER_CONTROL = "/advanceControl";
    public static final String URL_ADVANCE_FILTER_MODEL_SAVE = "/saveModel";
    public static final String URL_ADVANCE_FILTER_ROUTE_SAVE = "/saveControl";

    //user
    public static final String URL_USER_PREFIX = "/user";
    public static final String URL_USER_TURN_UPDATE_PWD = "/turn_update_pwd";
    public static final String URL_USER_UPDATE_PWD = "/updatePwd";
    public static final String URL_USER_CHECK_PWD = "/check_pwd";

    //control
    public static final String URL_CONTROL_PREFIX = "/control";
    public static final String URL_CONTROL_FIND_NAME = "/find_name";

    //route
    public static final String URL_ROUTE_PREFIX = "/route";
    public static final String URL_ROUTE_AVAILABLE_ROUTENO = "/available_routeNo";

    //model
    public static final String URL_MODEL_PREFIX = "/model";

    //apk
    public static final String URL_APK_PREFIX = "/apk";
    //downLoad
    public static final String URL_DOWN_APK_PREFIX = "/downLoadApk";

    //service
    public static final String URL_SERVICE_PREFIX = "/api";
    public static final String URL_SERVICE_USER_LOGIN = "/user/login";
    public static final String URL_SERVICE_USER_ENTRY = "/user/entry";
    public static final String URL_SERVICE_USER_UPDATEPWD = "/user/updatepwd";
    public static final String URL_SERVICE_MODEL_LOAD = "/model/load";
    public static final String URL_SERVICE_UPDATE_VERSION = "/apk/update";
    public static final String URL_SERVICE_ROUTE_NAME_UPDATE = "/route/update_name";
    public static final String URL_SERVICE_FILTER_SWITCH = "/filter/switch";

    //service test tools
    public static final String URL_SERVICE_TEST_TOOLS = "/tools";

    //error
    public static final String URL_NO_PERMISSION = "/error/no-permission";
}
