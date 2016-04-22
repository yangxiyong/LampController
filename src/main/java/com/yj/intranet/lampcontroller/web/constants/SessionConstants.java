package com.yj.intranet.lampcontroller.web.constants;

import com.yj.core.collection.Key;

import java.util.ArrayList;

/**
 * @author mort
 */
public class SessionConstants {
    public static final Key<String> CURRENT_USER = Key.stringKey("currentUser");
    public static final Key<String> CURRENT_USER_ID = Key.stringKey("currentUserId");
    public static final Key<String> CURRENT_USER_PWD = Key.stringKey("currentUserPwd");
    public static final Key<ArrayList> CURRENT_USER_ROLES = Key.key("currentUserRoles", ArrayList.class);
    public static final Key<String> CURRENT_USER_GROUP = Key.stringKey("currentUserGroup");
    public static final Key<String> LOGIN_REDIRECT_DESTINATION_URL = Key.stringKey("loginRedirectDestinationURL");
    public static final Key<String> CURRENT_DEPLOYMENT_CONTEXT = Key.stringKey("currentDeploymentContext");
}
