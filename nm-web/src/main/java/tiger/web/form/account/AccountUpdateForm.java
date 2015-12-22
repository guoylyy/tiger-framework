/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.form.account;

import tiger.common.dal.enums.GenderEnum;
import tiger.common.util.BeanUtil;
import tiger.common.util.StringUtil;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.domain.AccountDomain;
import tiger.web.form.BaseForm;
import tiger.web.form.FormInterface;

/**
 * @author zhangbin
 * @version v0.1 2015/9/19 15:17
 */
public class AccountUpdateForm extends BaseForm implements FormInterface {

    /**头像URI*/
    private String icon;

    /**用户性别*/
    @CopyIgnore
    private String gender;

    /**用户姓名*/
    private String name;

    /**电话号码*/
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public AccountDomain convert2Domain() {
        AccountDomain domain = new AccountDomain();
        BeanUtil.copyPropertiesWithIgnores(this, domain);
        if(StringUtil.isNotBlank(this.gender)) {
            domain.setGender(GenderEnum.getEnumByCode(this.gender));
        }
        return domain;
    }

}
