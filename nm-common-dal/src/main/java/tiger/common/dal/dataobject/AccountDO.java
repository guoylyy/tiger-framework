package tiger.common.dal.dataobject;

import tiger.common.util.annotation.CopyIgnore;

import java.io.Serializable;
import java.util.Date;

public class AccountDO implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String account;

    private String password;

    private String name;

    private String mobile;

    @CopyIgnore
    private String gender;

    @CopyIgnore
    private String icon;

    @CopyIgnore
    private String status;

    @CopyIgnore
    private String extParams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExtParams() {
        return extParams;
    }

    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }
}
