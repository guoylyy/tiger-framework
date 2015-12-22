package tiger.web.apect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import tiger.core.base.BaseResult;
import tiger.core.base.PageResult;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;

import java.sql.SQLException;

@Aspect
@Component
@Order(100)
public class TigerExceptionAspect {

    private static Logger logger = Logger.getLogger(TigerExceptionAspect.class);

    /** The Constant el. */
    private final static String el = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";

    @Before(el)
    public void before() {

    }

    /**
     * 统一异常处理函数
     *  ~ 可以处理基本返回异常 (BaseResult) 和 PageResult 返回异常
     *
     * @param p
     * @return
     */
    @Around(el)
    public Object around(ProceedingJoinPoint p) {
        Object ob = null;
        boolean isPageResult = false;
        if(p.getSignature() instanceof MethodSignature){
            MethodSignature signature = (MethodSignature)p.getSignature();
            if(signature.getReturnType()== PageResult.class){
                isPageResult = true;
            }
        }
        try {
            ob = p.proceed();
        } catch (AppException e) {
            if(isPageResult){
               return new PageResult<>(e.getCode(), e.getMessage());
            }
            return new BaseResult<>(e.getCode(), e.getMessage());
        } catch (DataAccessException | SQLException e) {
            if(isPageResult){
                return  new PageResult<>(ErrorCodeEnum.DB_EXCEPTION);
            }
            return new BaseResult<>(ErrorCodeEnum.DB_EXCEPTION);
        } catch (Throwable e) {
            logger.error(e);
            if(isPageResult){
                return new PageResult<>(ErrorCodeEnum.UNKNOW_ERROR);
            }
            return new BaseResult<>(ErrorCodeEnum.UNKNOW_ERROR);
        }
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
