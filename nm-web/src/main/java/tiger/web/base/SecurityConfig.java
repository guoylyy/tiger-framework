/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 安全解析配置
 *  ~ 配置应用访问的安全信息需求
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 13, 2015 7:17:28 AM yiliang.gyl Exp $
 */
@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureHeaders(http.headers());
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
            .anonymous().and().csrf().disable();

    }

    /**
     * Authentication filter.
     *
     * @return the filter
     */
    protected Filter authenticationFilter() {

        AbstractAuthenticationProcessingFilter filter = new SecurityContextAuthenticationFilter(
            "/");
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        filter.setAuthenticationSuccessHandler(successHandler);
        return filter;
    }

    /**
     * Configure headers.
     *
     * @param headers the headers
     * @throws Exception the exception
     */
    private static void configureHeaders(HeadersConfigurer<?> headers) throws Exception {
        HstsHeaderWriter writer = new HstsHeaderWriter(false);
        writer.setRequestMatcher(AnyRequestMatcher.INSTANCE);
        headers.contentTypeOptions().xssProtection().cacheControl().addHeaderWriter(writer)
            .frameOptions();
    }

    /**
     * Thin filter for Spring Security chain that simply transfers an existing
     * {@link Authentication} from the {@link SecurityContext} if there is one. This is
     * useful when authentication actually happened in a controller, rather than in the
     * filter chain itself.
     */
    static class SecurityContextAuthenticationFilter extends
                                                     AbstractAuthenticationProcessingFilter {

        /**
         * Instantiates a new security context authentication filter.
         *
         * @param defaultFilterProcessesUrl the default filter processes url
         */
        public SecurityContextAuthenticationFilter(String defaultFilterProcessesUrl) {
            super(defaultFilterProcessesUrl);
            setAuthenticationManager(authentication -> {
                throw new IllegalStateException("Unexpected call for AuthenticationManager");
            });
        }

        /**
         * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#attemptAuthentication(HttpServletRequest, HttpServletResponse)
         */
        @Override
        public Authentication attemptAuthentication(HttpServletRequest request,
                                                    HttpServletResponse response) throws AuthenticationException,
                                                                                  IOException,
                                                                                  ServletException {
            return SecurityContextHolder.getContext().getAuthentication();
        }
    }

}
