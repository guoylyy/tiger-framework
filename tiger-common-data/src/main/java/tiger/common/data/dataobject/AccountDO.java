/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.data.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tiger.common.util.annotation.CopyIgnore;

import java.util.Date;

/**
 * 用户账户对象
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 10:11 AM yiliang.gyl Exp $
 */
public class AccountDO extends BaseDO{
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3727161107301817613L;

    /** The company id. */
    private Long companyId;

    /** The user name. */
    private String userName;

    /** The password. */
    @JsonIgnore
    private String password;

    /** The gender. */
    @CopyIgnore
    private String gender;

    /** The birthday. */
    private Date birthday;

    /** The icon. */
    @CopyIgnore
    private String icon;

    /** The id card. */
    private String idCard;

    /** The business scope. */
    private String businessScope;

    /** The address. */
    private String address;

    /** The status. */
    private String status;

    /** The ext params. */
    @CopyIgnore
    private String extParams;


    private String mobile;

    /**
     * Gets the company id.
     *
     * @return the company id
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * Sets the company id.
     *
     * @param companyId the new company id
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user name.
     *
     * @param userName the new user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the birthday.
     *
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday.
     *
     * @param birthday the new birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Gets the icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the icon.
     *
     * @param icon the new icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets the id card.
     *
     * @return the id card
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * Sets the id card.
     *
     * @param idCard the new id card
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * Gets the business scope.
     *
     * @return the business scope
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * Sets the business scope.
     *
     * @param businessScope the new business scope
     */
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the ext params.
     *
     * @return the ext params
     */
    public String getExtParams() {
        return extParams;
    }

    /**
     * Sets the ext params.
     *
     * @param extParams the new ext params
     */
    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
