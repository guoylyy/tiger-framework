/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.form.account;

import tiger.common.dal.enums.GenderEnum;
import tiger.common.util.BeanUtil;
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

    /**用户名*/
    private String userName;

    /**身份证号码*/
    private String idCard;

    /**业务范围*/
    private String businessScope;

    /**地址*/
    private String address;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public AccountDomain convert2Domain() {
        AccountDomain domain = new AccountDomain();
        BeanUtil.copyPropertiesWithIgnores(this, domain);
        domain.setGender(GenderEnum.getEnumByCode(this.gender));
        return domain;
    }

}
