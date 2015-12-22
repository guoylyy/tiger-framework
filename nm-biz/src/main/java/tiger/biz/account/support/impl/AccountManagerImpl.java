/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.account.support.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.account.support.AccountManager;
import tiger.biz.util.PasswordEncryptUtil;
import tiger.common.dal.enums.AttachTypeEnum;
import tiger.common.dal.redis.RedisComponent;
import tiger.common.util.DateUtil;
import tiger.common.util.FileUtil;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.base.BaseResult;
import tiger.core.domain.AccountDomain;
import tiger.core.domain.AccountLoginLogDomain;
import tiger.core.domain.AttachDomain;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;
import tiger.core.service.AccountService;
import tiger.core.service.AttachService;
import tiger.core.service.LoginLogService;
import tiger.core.service.SystemParamService;

import java.util.Date;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 10:45 PM yiliang.gyl Exp $
 */
@Service
public class AccountManagerImpl implements AccountManager{

    Logger logger = Logger.getLogger(AccountManagerImpl.class);

    @Autowired
    AccountService accountService;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    AttachService attachService;

    @Autowired
    SystemParamService systemParamService;

    @Autowired
    RedisComponent redisComponent;

    @Override
    public AccountDomain signin(String account, String password) {
        AccountDomain accountDomain = accountService.readByAccount(account);
        if (accountDomain == null) {
            throw new AppException(ErrorCodeEnum.NOT_FOUND, "不存在的用户！");
        }
        if (!StringUtil.equals(account, accountDomain.getAccount())) {
            throw new AppException(ErrorCodeEnum.BIZ_FAIL, "用户名或密码错误！");
        }
        if (StringUtil.equals(password, PasswordEncryptUtil.getLoginPassword(accountDomain.getPassword(),
                account, PasswordEncryptUtil.SBIN))) {
            // 设置头像信息
            AttachDomain attachDomain = accountDomain.getIcon();
            if (null != attachDomain) {
                if (AttachTypeEnum.PUBLIC.equals(attachDomain.getAttachType()) && FileUtil.isImageFileFromName(attachDomain.getName())) {
                    accountDomain.setIcon(attachService.getAttachWithSignedUrlById(attachDomain.getId()));
                } else {
                    logger.error("用户[ "+ accountDomain +" ]头像设置错误");
                }
            }
            return accountDomain;
        } else {
            throw new AppException(ErrorCodeEnum.BIZ_FAIL, "用户名或密码错误！");
        }
    }

    @Override
    public AccountDomain signinByToken(String token) {
        //根据token获取用户id
        long accountId = loginLogService.getAccountIdByToken(token);

        //如果用户id存在则返回用户领域模型
        AccountDomain accountDomain = accountService.read(accountId);
        if(accountDomain != null){
            redisComponent.deleteObject(token);
            String cacheStr = JsonUtil.toJson(accountDomain);
            if(logger.isInfoEnabled()) {
                logger.info("存入缓存数据 [" + cacheStr + "]");
            }
            redisComponent.saveObject(token, cacheStr);
        }
        return accountDomain;
    }

    @Override
    public String createToken(AccountLoginLogDomain loginLogDomain, int expireDay) {
        if (null == loginLogDomain || expireDay < 0) {
            throw new AppException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }
        // TODO 1. token生成策略升级；2. 缓存增加
        String token = StringUtil.createToken();
        loginLogDomain.setToken(token);
        loginLogDomain.setExpireTime(DateUtil.addDays(new Date(), expireDay));
        loginLogService.createLog(loginLogDomain);
        return token;
    }

    @Override
    public BaseResult<Boolean> attachAccountHeader(Long accountId, Long attachId) {
        // 判断attachId是否存在
        if (!attachService.isExist(attachId)) {
            return new BaseResult<>(ErrorCodeEnum.NOT_FOUND, false);
        }
        // 判断accountId是否为attachId的所有者
        if (!attachService.isOwner(attachId, accountId)) {
            return new BaseResult<>(ErrorCodeEnum.UNAUTHORIZED, false);
        }
        // 判断attachId是否为公开附件
        if (!attachService.isAttachType(attachId, AttachTypeEnum.PUBLIC)) {
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE, false);
        }
        // 检查attachId是否为图像
        AttachDomain attachDomain = attachService.read(attachId);
        if (!FileUtil.isImageFileFromName(attachDomain.getName())) {
            return new BaseResult<>(ErrorCodeEnum.BIZ_UNSUPPORTED_KIND, false);
        }

        if (accountService.updateAccountHeaderIcon(accountId, attachId) > 0) {
            return new BaseResult<>(true);
        } else {
            return new BaseResult<>(ErrorCodeEnum.BIZ_FAIL, false);
        }
    }
}
