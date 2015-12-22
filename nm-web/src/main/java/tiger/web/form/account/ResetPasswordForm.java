/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.form.account;

import tiger.core.domain.AccountResetPwdDomain;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;
import tiger.web.form.BaseForm;
import tiger.web.form.FormInterface;

import javax.validation.constraints.NotNull;

/**
 * @author zhangbin
 * @version v0.1 2015/9/20 9:49
 */
public class ResetPasswordForm extends BaseForm implements FormInterface {

    @NotNull
    /**手机号码*/
    String account;

    @NotNull
    /**短信验证码*/
    String code;

    @NotNull(message = "密码不能为空")
    /**忘记密码后的，重置的密码*/
    String password;

    @NotNull(message = "确认密码不能为空")
    /**确认密码*/
    String confirmPassword;//我认为此处和注册没有必要把确认密码也传到后台，这些东西应该是在前台做处理的。没有确认密码也没事，不怕攻击者绕过前台直接访问。

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public AccountResetPwdDomain convert2Domain() {

        if (!confirmPassword.equals(password)){
            throw new AppException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
        AccountResetPwdDomain account = new AccountResetPwdDomain();
        //BeanUtil.copyPropertiesWithIgnores(this,account);此处字段少，所以不再用反射，浪费性能
        account.setAccount(this.account);
        account.setPassword(this.password);

        return account;
    }
}
