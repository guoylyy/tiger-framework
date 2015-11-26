/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.base;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tiger.biz.BizConfig;
import tiger.common.data.DataConfig;
import tiger.core.CoreConfig;

/**
 * WEB 层配置类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:28 PM yiliang.gyl Exp $
 */
@SpringBootApplication
@EnableWebMvc
@Import({DataConfig.class, CoreConfig.class, BizConfig.class})
@ComponentScan("tiger.web.controller")
@EnableScheduling
public class TigerWebConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }

}
