package com.yj.intranet.lampcontroller;

import com.yj.core.database.ConnectionPoolDataSource;
import com.yj.core.http.HTTPClient;
import com.yj.core.platform.concurrent.TaskExecutor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * @author mort
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "com.yj.intranet.lampcontroller.dao")
public class AppConfig {

    @Inject
    private Environment env;
    @Inject
    public InterceptorRegistry interceptorRegistry;

    @Bean
    public DataSource dataSource() {
        ConnectionPoolDataSource dataSource = new ConnectionPoolDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(100);
        dataSource.setDatabaseName("lampcontroller");
        dataSource.setValidationQuery("select 1");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
       //method one :使用配置文件方式
        sqlSessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
/*        //method two: 代码
        Resource resource = new ClassPathResource("/config.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        sqlSessionFactory.setConfigurationProperties(props);
//        sqlSessionFactory.setPlugins(interceptors); //已经通过注解方式了 此处应该不需要再配置
        //method two end
       */
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setTypeAliasesPackage("com.yj.intranet.lampcontroller.domain");
        return sqlSessionFactory.getObject();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return TaskExecutor.fixedSizeExecutor(20);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public HTTPClient httpClient() {
        return new HTTPClient();
    }
}
