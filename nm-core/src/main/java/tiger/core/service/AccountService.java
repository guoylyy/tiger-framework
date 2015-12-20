/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;

import tiger.common.data.query.AccountQuery;
import tiger.core.base.PageResult;
import tiger.core.domain.AccountDomain;
import tiger.core.domain.AccountResetPwdDomain;

import java.util.HashMap;
import java.util.List;

/**
 * 账户服务类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 3:07 PM yiliang.gyl Exp $
 */
public interface AccountService {

    /**
     * 通过id获取用户
     *
     * @param accountId
     * @return
     */
    AccountDomain read(Long accountId);

    /**
     * 通过手机获取用户
     *
     * @param mobile
     * @return
     */
    AccountDomain readByMobile(String mobile);

    /**
     * 删除用户登录token
     *
     * @param accountId
     * @param loginToken
     * @return
     */
    Boolean deleteLoginToken(Long accountId, String loginToken);

    /**
     * @param accountId
     * @param newPassword
     * @return
     */
    Boolean resetPasswordById(Long accountId, String newPassword);

    /**
     * 更新用户信息
     *
     * @param accountDomain
     * @return
     */
    boolean updateAccount(AccountDomain accountDomain);

    /**
     * 更新用户头像关联
     *
     * @param accountId
     * @param attachId
     * @return
     */
    int updateAccountHeaderIcon(Long accountId, Long attachId);

    /**
     * 新增用户
     *
     * @param domain
     * @return
     */
    AccountDomain addAccount(AccountDomain domain);

    /**
     * 分页查询用户列表
     *
     * @param query
     * @return
     */
    PageResult<List<AccountDomain>> query(AccountQuery query);


    /**
     * 验证手机号码是否存在
     * @param phoneNum
     * @return
     */
    boolean isMobileExist(String phoneNum);

    /**
     *
     * @param resetPassDomain
     * @return
     */
    boolean resetPasswordByMobile(AccountResetPwdDomain resetPassDomain);

    /**
     *
     * @param resetPassDomain
     * @return
     */
    boolean resetPasswordByOldPassword(AccountResetPwdDomain resetPassDomain);

    /**
     *
     * @param id
     */
    void delete(long id);

    /**
     * 根据accoutId获取帐户额外设置
     *  现包括：逾期天数和坏账天数
     * @param accountId
     * @return
     */
    HashMap<String, String> getExtParam(Long accountId);
}
