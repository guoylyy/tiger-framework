/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.apect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:43 AM yiliang.gyl Exp $
 */
@Component
@Aspect
public class RedisAspect {

    private final static String el = "@annotation(tiger.common.util.annotation.RedisCache)";

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

    @Around(el)
    public Object around(ProceedingJoinPoint p) throws Throwable {
        System.err.println("测试 aspect 成功");
        return  p.proceed();
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
}
