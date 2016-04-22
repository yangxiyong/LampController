package com.yj.intranet.lampcontroller;

import com.yj.core.platform.DefaultAppContextInitializer;
import com.yj.core.platform.web.filter.PlatformFilter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

/**
 * @author mort
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestEnvironmentConfig.class}, initializers = DefaultAppContextInitializer.class)
@TransactionConfiguration
@ActiveProfiles("test")
@WebAppConfiguration
public abstract class SpringTest {

    @Inject
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void createMockMVC() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new PlatformFilter())
                .build();
    }

}
