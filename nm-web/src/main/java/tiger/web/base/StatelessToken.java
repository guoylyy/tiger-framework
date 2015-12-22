/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.base;

import org.apache.shiro.authc.AuthenticationToken;
import tiger.core.domain.AccountDomain;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:28 PM yiliang.gyl Exp $
 */
public class StatelessToken implements AuthenticationToken {

    public StatelessToken() {

    }

    public StatelessToken(AccountDomain account, String accessToken) {
        this.accessToken = accessToken;
        this.account = account;
    }

    /**  */
    private static final long serialVersionUID = -8210358249576302789L;

    private AccountDomain account;

    private String accessToken;

    @Override
    public Object getPrincipal() {
        return account;
    }

    @Override
    public Object getCredentials() {
        return accessToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(account.getAccount());
        sb.append(" - ");
        sb.append(accessToken);
        return sb.toString();
    }

}
