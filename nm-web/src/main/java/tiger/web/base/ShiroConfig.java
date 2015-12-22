/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.base;

import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:43 AM yiliang.gyl Exp $
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        Map<String, String> filterChainDefinitionMapping = new HashMap<>();
        filterChainDefinitionMapping.put("/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        shiroFilter.setSecurityManager(securityManager());
        Map<String, Filter> filters = new HashMap<>();
        filters.put("anon", new AnonymousFilter());
        shiroFilter.setFilters(filters);
        System.out.println(shiroFilter.getFilters().size());
        return shiroFilter;
    }

    @Bean(name = "securityManager")
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //禁用session
        DefaultWebSubjectFactory subjectFactory = new StatelessSubjectFactory();
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultWebSessionStorageEvaluator sse = new DefaultWebSessionStorageEvaluator();
        sse.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sse);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean(name = "myRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizingRealm myRealm() {
        ShiroRealm realm = new ShiroRealm();
        //realm.setCacheManager(cacheManager());
        realm.init();
        return realm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

//    @Bean
//    public CacheManager cacheManager() {
//        return new EhCacheManager();
//    }
}
