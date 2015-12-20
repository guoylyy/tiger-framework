package tiger.common.data.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tiger.common.util.annotation.CopyIgnore;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 13, 2015 3:57:47 PM yiliang.gyl Exp $
 */
public class AccountDO extends BaseDO {

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

    /** The icon. */
    @CopyIgnore
    private String icon;

    /** The status. */
    private String status;

    /** The ext params. */
    @CopyIgnore
    private String extParams;

    private String mobile;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
