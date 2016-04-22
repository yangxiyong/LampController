package com.yj.intranet.lampcontroller.platform.controller;

import com.yj.core.platform.web.site.SiteController;
import com.yj.core.platform.web.site.cookie.RequireCookie;
import com.yj.core.platform.web.site.session.RequireSession;
import com.yj.intranet.lampcontroller.web.masterlayout.MasterLayout;

/**
 * @author mort
 */
@MasterLayout
@RequireCookie
@RequireSession
public class PlatformMasterController extends SiteController {
}
