/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain;

import tiger.core.base.BaseDomain;

/**
 * @author zhangbin
 * @version v0.1 2015/9/23 22:06
 */
public class AccountSignUpDomain extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 934821214131782794L;

    /**手机号码*/
    private String mobile;

    /**密码*/
    private String password;

    /**用户名字*/
    private String userName;

    /**邀请码，可以不填*/
    private String invitationCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
