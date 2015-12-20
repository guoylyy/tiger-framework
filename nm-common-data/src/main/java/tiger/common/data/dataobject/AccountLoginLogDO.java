package tiger.common.data.dataobject;

import java.sql.Timestamp;

/**
 * Created by HuPeng on 2015/9/9.
 */
public class AccountLoginLogDO extends BaseDO {

    private static final long serialVersionUID = -6245670160837445638L;

    public AccountLoginLogDO() {
    }

    private String ip;

    private String plat;

    private String token;

    private Timestamp expireTime;

    private long accountId;

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

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
