package com.yj.intranet.lampcontroller.web.interceptor;

import com.yj.core.http.HTTPConstants;
import com.yj.core.platform.web.DeploymentSettings;
import com.yj.core.platform.web.request.RequestContext;
import com.yj.core.platform.web.site.session.SessionContext;
import com.yj.core.platform.web.url.URLBuilder;
import com.yj.intranet.lampcontroller.web.constants.SessionConstants;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.LoginRequired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * @author mort
 */
public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {

    @Inject
    private SessionContext sessionContext;

    @Inject
    private RequestContext requestContext;

    @Inject
    private DeploymentSettings deploymentSettings;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            LoginRequired loginRequired = findAnnotation((HandlerMethod) handler, LoginRequired.class);
            if (loginRequired != null && !loggedIn()) {
                sessionContext.set(SessionConstants.LOGIN_REDIRECT_DESTINATION_URL, requestContext.getClientRequestedRelativeURLWithQueryString());
                loginRequiredAction(response);
                return false;
            }
        }
        return true;
    }

    private void loginRequiredAction(HttpServletResponse response) {
        URLBuilder builder = new URLBuilder();
        builder.setContextPath(deploymentSettings.getDeploymentContext());
        builder.setLogicalURL(URLConstants.URL_LOGIN);
        response.setStatus(HTTPConstants.SC_MOVED_TEMPORARILY);
        response.setHeader(HTTPConstants.HEADER_REDIRECT_LOCATION, builder.buildRelativeURL());
    }

    private <T extends Annotation> T findAnnotation(HandlerMethod handlerMethod, Class<T> annotationType) {
        T annotation = handlerMethod.getBeanType().getAnnotation(annotationType);
        if (annotation != null)
            return annotation;
        return handlerMethod.getMethodAnnotation(annotationType);
    }

    private boolean loggedIn() {
        String currentUserName = sessionContext.get(SessionConstants.CURRENT_USER);
        return currentUserName != null && StringUtils.hasText(currentUserName);
    }
}
