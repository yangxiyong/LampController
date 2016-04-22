package com.yj.intranet.lampcontroller.web.masterlayout;

import com.yj.core.platform.web.site.layout.ModelBuilder;
import com.yj.core.platform.web.site.session.SessionContext;
import com.yj.intranet.lampcontroller.web.constants.SessionConstants;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author mort
 */
public class MasterTemplateModelBuilder implements ModelBuilder {

    @Inject
    SessionContext sessionContext;

    @Override
    public void build(Map<String, Object> model) {
        //model.put("introduction", "This is master layout template model!");
        model.put("myName", sessionContext.get(SessionConstants.CURRENT_USER));
        model.put("userRoles", sessionContext.get(SessionConstants.CURRENT_USER_ROLES));
        model.put("userId", sessionContext.get(SessionConstants.CURRENT_USER_ID));
        model.put("myGroupId", sessionContext.get(SessionConstants.CURRENT_USER_GROUP));
        model.put("contextPath", sessionContext.get(SessionConstants.CURRENT_DEPLOYMENT_CONTEXT));
    }
}
