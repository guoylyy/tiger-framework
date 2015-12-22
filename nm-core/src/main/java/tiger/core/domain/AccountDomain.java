/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tiger.common.dal.enums.GenderEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.base.BaseDomain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * The Class AccountDomain.
 *
 * @author zhangbin
 * @version v0.1 2015/10/1 22:41
 */
public class AccountDomain extends BaseDomain {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -6803210431910905404L;

    /**
     * The user name.
     */
    private String account;

    /**
     * The password.
     */
    @JsonIgnore
    private String password;

    /**
     * The gender.
     */
    @CopyIgnore
    private GenderEnum gender;

    /**
     * The name
     */
    private String name;

    /**
     * The mobile.
     */
    private String mobile;

    /**
     * 头像
     */
    @CopyIgnore
    private AttachDomain icon;

    /**
     * The roles.
     */
    @JsonIgnore
    @CopyIgnore
    private List<RoleDomain> roles;

    /**
     * The permissions.
     */
    @JsonIgnore
    @CopyIgnore
    private List<PermissionDomain> permissions;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;


    /**
     * 用户额外参数
     */
    @CopyIgnore
    private HashMap<String, String> extParams;

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

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public AttachDomain getIcon() {
        return icon;
    }

    public void setIcon(AttachDomain icon) {
        this.icon = icon;
    }

    public List<RoleDomain> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDomain> roles) {
        this.roles = roles;
    }

    public List<PermissionDomain> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDomain> permissions) {
        this.permissions = permissions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public HashMap<String, String> getExtParams() {
        return extParams;
    }

    public void setExtParams(HashMap<String, String> extParams) {
        this.extParams = extParams;
    }
}
