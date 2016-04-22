package com.yj.intranet.lampcontroller.web.interceptor;

import com.yj.core.http.HTTPConstants;
import com.yj.core.platform.web.DeploymentSettings;
import com.yj.core.platform.web.site.session.SessionContext;
import com.yj.core.platform.web.url.URLBuilder;
import com.yj.intranet.lampcontroller.web.constants.SessionConstants;
import com.yj.intranet.lampcontroller.web.constants.URLConstants;
import com.yj.intranet.lampcontroller.web.interceptor.annotation.Protected;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author mort
 */
public class ProtectedInterceptor extends HandlerInterceptorAdapter {
    @Inject
    private SessionContext sessionContext;

    @Inject
    private DeploymentSettings deploymentSettings;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Protected protectedAnnotation = findAnnotation((HandlerMethod) handler, Protected.class);
            if (protectedAnnotation != null) {
                List<String> userRoles = sessionContext.get(SessionConstants.CURRENT_USER_ROLES);
                String[] protectedRoles = protectedAnnotation.value();
                for (String role : protectedRoles) {
                    if (userRoles != null && userRoles.contains(role))
                        return true;
                }
                permissionRequiredAction(response);
                return false;
            }
        }
        return true;
    }

    private <T extends Annotation> T findAnnotation(HandlerMethod handlerMethod, Class<T> annotationType) {
        T annotation = handlerMethod.getBeanType().getAnnotation(annotationType);
        if (annotation != null)
            return annotation;
        return handlerMethod.getMethodAnnotation(annotationType);
    }

    private void permissionRequiredAction(HttpServletResponse response) {
        URLBuilder builder = new URLBuilder();
        builder.setContextPath(deploymentSettings.getDeploymentContext());
        builder.setLogicalURL(URLConstants.URL_NO_PERMISSION);
        response.setStatus(HTTPConstants.SC_MOVED_TEMPORARILY);
        response.setHeader(HTTPConstants.HEADER_REDIRECT_LOCATION, builder.buildRelativeURL());
    }
}
