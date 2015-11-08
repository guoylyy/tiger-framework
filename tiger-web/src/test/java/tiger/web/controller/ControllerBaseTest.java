/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tiger.web.base.TigerWebConfig;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 8:47 PM yiliang.gyl Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TigerWebConfig.class})
@PropertySource(value = {"api-test.properties"})
public class ControllerBaseTest {

    @Autowired
    Environment environment;

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;


    @Before
    public void setupMockMvc() throws Exception{
        System.out.println("测试开始");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @After
    public void clearTests(){
        System.out.println("测试关闭");
    }

}
