/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.controller.account;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tiger.biz.account.support.AccountManager;
import tiger.common.util.IDCardUtil;
import tiger.common.util.StringUtil;
import tiger.common.util.annotation.Permission;
import tiger.common.util.annotation.RequireValid;
import tiger.core.base.BaseResult;
import tiger.core.domain.AccountDomain;
import tiger.core.domain.AccountLoginLogDomain;
import tiger.core.domain.AccountResetPwdDomain;
import tiger.core.domain.AttachDomain;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.service.AccountService;
import tiger.core.service.AttachService;
import tiger.core.service.LoginLogService;
import tiger.core.service.SmsService;
import tiger.web.constants.APIConstants;
import tiger.web.controller.BaseController;
import tiger.web.form.account.AccountUpdateForm;
import tiger.web.form.account.SimpleResetPasswordForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 用户和权限相关 API
 *
 * @author yiliang.guo
 * @version v 0.1 2015年9月10日 下午7:23:26 yiliang.guo Exp $
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private static Logger logger = Logger.getLogger(AccountController.class);

    /**
     * The account service.
     */
    @Autowired
    private AccountManager accountManager;

    @Autowired
    SmsService smsService;

    @Autowired
    AccountService accountService;

    @Autowired
    AttachService attachService;

    @Autowired
    LoginLogService loginLogService;


    /**
     * 用户登录接口
     * Login.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public BaseResult<AccountDomain> login(@RequestHeader(APIConstants.HEADER_USERNAME) String account,
                                           @RequestHeader(APIConstants.HEADER_PASSWORD) String password,
                                           @RequestHeader(APIConstants.HEADER_USER_AGENT) String userAgent,
                                           @RequestParam(value = APIConstants.PARAM_EXPIRE_DAY, required = false, defaultValue = APIConstants.TOKEN_DEFAULT_EXPIRE_DAY) int expireDay,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        if (StringUtil.isBlank(account) || StringUtil.isBlank(password)) {
            return new BaseResult<>(ErrorCodeEnum.PARAMETERS_IS_NULL);
        }
        AccountDomain accountDomain = accountManager.signin(account, password);

        String token = accountManager.createToken(new AccountLoginLogDomain(accountDomain.getId(),
                userAgent, request.getRemoteAddr()), expireDay);
        this.setToken(response, token);
        return new BaseResult<>(accountDomain);
    }

    /**
     * 用户登出
     *
     * @return the string
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.DELETE)
    @Permission
    public BaseResult<Object> logout(@RequestHeader(APIConstants.HEADER_TOKEN) String token) {
        boolean rc = accountService.deleteLoginToken(this.currentAccount().getId(), token);
        if (rc) {
            if (logger.isInfoEnabled()) {
                logger.info("用户 [" + this.currentAccount().getMobile() + "] 成功登出..");
            }
        } else {
            logger.error("用户 [" + this.currentAccount().getMobile() + "] 登出失败..");
        }
        return new BaseResult<>(rc);
    }

    /**
     * 用户查询自己的登录token是否有效
     *
     * @param token
     * @param account
     * @return
     */
    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    public BaseResult<Boolean> isValidToken(@RequestHeader(APIConstants.HEADER_TOKEN) String token,
                                            @RequestHeader(APIConstants.HEADER_USERNAME) String account) {
        AccountDomain accountDomain = accountService.readByAccount(account);
        long accountId = loginLogService.getAccountIdByToken(token);
        if (null == accountDomain || !accountDomain.getId().equals(accountId)) {
            return new BaseResult<>(false);
        }
        return new BaseResult<>(true);
    }

    /**
     * 用户获取个人信息
     *
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @Permission
    public BaseResult<AccountDomain> getUserProfile() {
        AccountDomain accountDomain = this.currentAccount();

        AttachDomain attachDomain = null;
        if (accountDomain.getIcon() != null) {
            attachDomain = attachService.getAttachWithSignedUrlById(accountDomain.getIcon().getId());
        }
        accountDomain.setIcon(attachDomain);
        return new BaseResult<>(accountDomain);
    }

    /**
     * 用户修改自己个人信息
     *
     * @param form
     * @param bindResult
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    @Permission
    @ResponseBody
    @RequireValid
    public BaseResult<Object> updateUserProfile(@RequestBody @Valid AccountUpdateForm form,
                                                BindingResult bindResult) {
        AccountDomain domain = form.convert2Domain();
        domain.setId(this.currentAccount().getId());
        domain = accountService.updateAccount(domain);
        if(domain != null){
            return new BaseResult<>(domain);
        }else{
            return new BaseResult<>(ErrorCodeEnum.BIZ_FAIL, "更新信息失败!");
        }
    }

    /**
     * 更新用户头像
     *
     * @param attchId
     * @return
     */
    @RequestMapping(value = "/icon/{attachId}", method = RequestMethod.PUT)
    @Permission
    public BaseResult<Boolean> attachHeaderIcon(@PathVariable("attachId") Long attchId) {
        return accountManager.attachAccountHeader(currentAccount().getId(), attchId);
    }

    /**
     * 用原密码修改密码
     *
     * @return
     */
    @RequestMapping(value = {"/password"}, method = RequestMethod.PUT)
    @Permission
    @RequireValid
    public BaseResult<Boolean> changePassword(@RequestBody @Valid SimpleResetPasswordForm form,
                                              BindingResult bindingResult) {
        AccountResetPwdDomain resetPwdDomain = form.convert2Domain();
        resetPwdDomain.setAccountId(this.currentAccount().getId());
        return new BaseResult<>(accountService.resetPasswordByOldPassword(resetPwdDomain));
    }

    /**
     * Sets the token.
     *
     * @param response the response
     * @param token    the token
     */
    private void setToken(HttpServletResponse response, String token) {
        response.setHeader(APIConstants.HEADER_TOKEN, token);
    }

}
