package tiger.web.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import tiger.core.domain.AccountDomain;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;

/**
 * Created by HuPeng on 2015/9/8.
 */
public class BaseController {
    private static Logger logger = Logger.getLogger(BaseController.class);

    public Subject currentUser() {
        return SecurityUtils.getSubject();
    }

    public AccountDomain currentAccount() {
        AccountDomain currentUser = (AccountDomain) currentUser().getPrincipal();
        /**如果是未登录用户，直接抛异常返回*/
        if (currentUser == null ) {
            throw new AppException(ErrorCodeEnum.UNAUTHORIZED, "未登录");
        }
        return currentUser;
    }

    public Boolean isAdmin() {
        return false;
    }
}
