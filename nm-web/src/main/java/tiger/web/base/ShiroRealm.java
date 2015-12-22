/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.base;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tiger.biz.account.support.AccountManager;
import tiger.core.domain.AccountDomain;
import tiger.core.domain.PermissionDomain;
import tiger.core.domain.RoleDomain;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:28 PM yiliang.gyl Exp $
 */
@Component("shiroRealm")
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    AccountManager accountManager;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    };

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //TODO: 这一段代码测试永远不会被执行到
        AccountDomain account = (AccountDomain) principals.fromRealm(getName()).iterator().next();
        if (null != account) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (RoleDomain role : account.getRoles()) {
                info.addRole(role.getName().getCode());
            }
            for (PermissionDomain permission : account.getPermissions()) {
                info.addStringPermission(permission.toString());
            }
            return info;
        }
        return null;
    }

    /**
    *
    * @param authcToken
    * @return
    * @throws AuthenticationException
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        StatelessToken token = (StatelessToken) authcToken;
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(),
            getName());
    }
}
