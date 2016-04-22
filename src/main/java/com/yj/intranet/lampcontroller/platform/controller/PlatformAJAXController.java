package com.yj.intranet.lampcontroller.platform.controller;

import com.yj.core.platform.web.rest.RESTController;
import com.yj.core.platform.web.site.cookie.RequireCookie;
import com.yj.core.platform.web.site.session.RequireSession;

/**
 * @author mort
 */
@RequireCookie
@RequireSession
public class PlatformAJAXController extends RESTController {
}
