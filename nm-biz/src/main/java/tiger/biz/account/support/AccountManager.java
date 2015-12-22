/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.account.support;

import tiger.core.base.BaseResult;
import tiger.core.domain.AccountDomain;
import tiger.core.domain.AccountLoginLogDomain;

/**
 * @author yiliang.guo
 * @version v0.1 2015/9/27 10:08
 */
public interface AccountManager {

    /**
     * 用户登录接口
     *
     * @param account
     * @param password
     * @return
     */
    AccountDomain signin(String account, String password);


    /**
     * 通过token获取账户信息
     *
     * @param token
     * @return
     */
    AccountDomain signinByToken(String token);

    /**
     * 未登录用户创建一个 n 天后过期的token
     *
     * @param loginLogDomain
     * @param expireDay
     * @return
     */
    String createToken(AccountLoginLogDomain loginLogDomain, int expireDay);

     /**
     * 关联用户头像
     * @param accountId
     * @param attachId
     * @return
     */
    BaseResult<Boolean> attachAccountHeader(Long accountId, Long attachId);

}
