/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.apect;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tiger.biz.account.support.AccountManager;
import tiger.common.dal.redis.RedisComponent;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.common.util.annotation.Permission;
import tiger.common.util.permission.PermissionRelation;
import tiger.core.base.BaseResult;
import tiger.core.domain.AccountDomain;
import tiger.core.enums.ErrorCodeEnum;
import tiger.web.base.StatelessToken;
import tiger.web.constants.APIConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限判断aop切面
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 10:33 PM yiliang.gyl Exp $
 */
@Aspect
@Component
@Order(1)
public class PermissionAspect {

    private static Logger logger = Logger.getLogger(PermissionAspect.class);

    /** The Constant el. */
    private final static String el = "@annotation(tiger.common.util.annotation.Permission)";

    /** The request. */
    @Autowired
    HttpServletRequest request;

    /** The account service. */
    @Autowired
    AccountManager accountManager;

    @Autowired
    RedisComponent redisComponent;

    /**
     * Before.
     */
    @Before(el)
    public void before() {
    }

    /**
     * After.
     */
    @After(el)
    public void after() {

    }

    /**
     *
     *
     * @param p
     * @return
     * @throws Throwable
     */
    @Around(el)
    public Object around(ProceedingJoinPoint p) throws Throwable {
        //TODO: 这个非登录需要Permission的情况要通过实际的数据库permission测一次，现在看起来都不支持
        Object ob = null;
        Permission permission = ((MethodSignature) p.getSignature()).getMethod()
                .getAnnotation(Permission.class);
        String accessToken = getToken();
        if (StringUtil.isEmpty(accessToken)) {
            return new BaseResult<>(ErrorCodeEnum.UNAUTHORIZED);
        }
        AccountDomain accountDomain = findAccountDomainByToken(accessToken);

        if (null == accountDomain) {
            return new BaseResult<>(ErrorCodeEnum.UNAUTHORIZED);
        }
        StatelessToken token = new StatelessToken(accountDomain, accessToken);
        Subject user = SecurityUtils.getSubject();
        user.login(token);
        if (permission.value().length == 0 || permitted(user, permission)) {
            ob = p.proceed();
        } else {
            return new BaseResult<>(ErrorCodeEnum.UNAUTHORIZED);
        }
        return ob;
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    private String getToken() {
        return request.getHeader(APIConstants.HEADER_TOKEN);
    }


    /**
     * Throwing.
     *
     * @param e
     *            the e
     */
    @AfterThrowing(value = el, throwing = "e")
    public void throwing(Exception e) {
        e.printStackTrace();
    }


    //~ private method

    private AccountDomain findAccountDomainByToken(String token){
        String content = redisComponent.getObject(token);
        AccountDomain accountDomain;
        if(StringUtil.isNotBlank(content)){
            accountDomain = JsonUtil.fromJson(content, AccountDomain.class);
            if(logger.isInfoEnabled()){
                //logger.info("从缓存中获取用户 [" + accountDomain + "]");
            }
        }else{
            accountDomain = accountManager.signinByToken(token);
            if(logger.isInfoEnabled()){
                //logger.info("从数据库中获取用户 [" + accountDomain + "]");
            }
        }
        return  accountDomain;
    }

    private boolean permitted(Subject user, Permission permission) {
        String[] permissions = permission.value();
        if (permission.relation().equals(PermissionRelation.AND)) {
            return user.isPermittedAll(permissions);
        } else if (permission.relation().equals(PermissionRelation.OR)) {
            boolean[] bs = user.isPermitted(permissions);
            for (boolean b : bs) {
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }


}
