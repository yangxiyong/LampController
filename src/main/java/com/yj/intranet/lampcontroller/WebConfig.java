package com.yj.intranet.lampcontroller;

import com.yj.core.log.LogSettings;
import com.yj.core.platform.DefaultSiteConfig;
import com.yj.core.platform.PlatformScopeResolver;
import com.yj.core.platform.runtime.RuntimeEnvironment;
import com.yj.core.platform.runtime.RuntimeSettings;
import com.yj.core.platform.web.DeploymentSettings;
import com.yj.core.platform.web.site.SiteSettings;
import com.yj.core.platform.web.site.layout.ModelBuilderInterceptor;
import com.yj.core.platform.web.site.session.SessionProviderType;
import com.yj.intranet.lampcontroller.platform.filter.CommonLogMessageFilter;
import com.yj.intranet.lampcontroller.web.interceptor.LoginRequiredInterceptor;
import com.yj.intranet.lampcontroller.web.interceptor.ProtectedInterceptor;
import com.yj.intranet.lampcontroller.web.masterlayout.MasterLayout;
import com.yj.intranet.lampcontroller.web.masterlayout.MasterTemplateModelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.inject.Inject;
import javax.servlet.ServletContext;

/**
 * @author mort
 */
@Configuration
@ComponentScan(basePackageClasses = WebConfig.class, scopeResolver = PlatformScopeResolver.class)
public class WebConfig extends DefaultSiteConfig {

    @Inject
    Environment env;

    @Inject
    ServletContext servletContext;

    @Override
    public DeploymentSettings deploymentSettings() {
        DeploymentSettings settings = super.deploymentSettings();
        settings.setDeploymentContext(env.getProperty("site.deploymentContext"), servletContext);
        settings.setHTTPPort(env.getRequiredProperty("site.httpPort", int.class));
        settings.setHTTPSPort(env.getRequiredProperty("site.httpsPort", int.class));
        return settings;
    }

    @Override
    public SiteSettings siteSettings() {
        SiteSettings settings = new SiteSettings();
        settings.setErrorPage(env.getProperty("site.errorPage"));
        settings.setResourceNotFoundPage("forward:" + env.getProperty("site.resourceNotFoundErrorPage"));
        settings.setSessionTimeOutPage("redirect:" + env.getProperty("site.sessionTimeoutPage"));
        settings.setSessionProviderType(env.getProperty("site.sessionProvider", SessionProviderType.class, SessionProviderType.LOCAL));
        settings.setRemoteSessionServers(env.getProperty("site.remoteSessionServers"));
        settings.setJSDir("/assets/js");
        settings.setCSSDir("/assets/css");
        return settings;
    }

    @Override
    public RuntimeSettings runtimeSettings() {
        RuntimeSettings runtimeSettings = super.runtimeSettings();
        runtimeSettings.setEnvironment(env.getProperty("site.environment", RuntimeEnvironment.class, RuntimeEnvironment.PROD));
        runtimeSettings.setVersion(getVersion());
        return runtimeSettings;
    }

    private String getVersion() {
        String version = env.getProperty("runtime.version");
        if (version.contains("${"))
            return "current";
        return version;
    }

    @Override
    public LogSettings logSettings() {
        LogSettings logSettings = super.logSettings();
        logSettings.setLogMessageFilter(new CommonLogMessageFilter());
        return logSettings;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public ModelBuilderInterceptor modelBuilderInterceptor() {
        ModelBuilderInterceptor interceptor = new ModelBuilderInterceptor();
        interceptor.registerModelBuilder(MasterLayout.class, MasterTemplateModelBuilder.class);
        return interceptor;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login");
    }

    @Bean
    public LoginRequiredInterceptor loginRequiredInterceptor() {
        return new LoginRequiredInterceptor();
    }

    @Bean
    public ProtectedInterceptor protectedInterceptor() {
        return new ProtectedInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(exceptionInterceptor());
        registry.addInterceptor(requestContextInterceptor());
        registry.addInterceptor(trackInterceptor());
        registry.addInterceptor(cookieInterceptor());
        registry.addInterceptor(sessionInterceptor());
        registry.addInterceptor(loginRequiredInterceptor());
        registry.addInterceptor(protectedInterceptor());
        registry.addInterceptor(modelBuilderInterceptor());
    }
}
