/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.apect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import tiger.core.base.BaseResult;
import tiger.core.enums.ErrorCodeEnum;

/**
 * 查看controller的form是否合格
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 10:30 PM yiliang.gyl Exp $
 */
@Aspect
@Component
public class FormValidateAspect {

    /** 切点 */
    //TODO: 这里应该换成一个自定义的切点，因为大多数的controller不需要这个切面，影响效率
    private final static String el = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";

    @Before(el)
    public void before() {

    }

    @Around(el)
    public Object around(ProceedingJoinPoint p) throws Throwable {
        Object[] args = p.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    return new BaseResult<>(ErrorCodeEnum.PARAMETERS_IS_NULL,
                            bindingResult.getAllErrors());
                }
            }
        }
        Object ob = null;
        ob = p.proceed();
        return ob;
    }

    @After(el)
    public void after() {

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
