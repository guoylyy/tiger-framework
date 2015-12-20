package tiger.core.domain;

import tiger.common.util.StringUtil;
import tiger.core.base.BaseDomain;
import tiger.core.constants.SystemConstants;

import java.util.Date;

/**
 * 登录记录模型 by hupeng
 */
public class AccountLoginLogDomain extends BaseDomain {
    private static final long serialVersionUID = -2158123351523129722L;

    private String ip;

    private String plat;

    private String token;

    private Date createTime;

    private Date expireTime;

    private long accountId;

    public AccountLoginLogDomain() {
    }

    public AccountLoginLogDomain(long accountId, String plat, String ip) {
        this.ip = ip;
        this.plat = StringUtil.substring(plat, SystemConstants.FIRST_INDEX, SystemConstants.PLAT_LAST_INDEX);
        this.accountId = accountId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
