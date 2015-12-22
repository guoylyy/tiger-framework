/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain;

import tiger.common.util.annotation.CopyIgnore;
import tiger.core.base.BaseDomain;

/**
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 10, 2015 8:20:06 PM yiliang.gyl Exp $
 */
@SuppressWarnings("serial")
public class AccountResetPwdDomain extends BaseDomain {

    private Long accountId;

    /**手机号码*/
    @CopyIgnore
    private String account;

    /**密码*/
    private String password;

    /**旧密码*/
    private String oldPassword;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
