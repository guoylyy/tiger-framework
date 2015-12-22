/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.convert;

import tiger.common.dal.dataobject.AccountDO;
import tiger.common.dal.enums.GenderEnum;
import tiger.common.util.BeanUtil;
import tiger.common.util.JsonConverterUtil;
import tiger.common.util.JsonUtil;
import tiger.common.util.StringUtil;
import tiger.core.domain.AccountDomain;
import tiger.core.domain.AccountSignUpDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 5:02 PM yiliang.gyl Exp $
 */
public class AccountConvert {

    /**
     * @param accountDO
     * @return
     */
    public static AccountDomain convert2Domain(AccountDO accountDO) {
        if (accountDO == null) {
            return null;
        }
        AccountDomain domain = new AccountDomain();
        BeanUtil.copyPropertiesWithIgnores(accountDO, domain);
        domain.setGender(GenderEnum.getEnumByCode(accountDO.getGender()));
        if (StringUtil.isNotBlank(accountDO.getExtParams())) {
            domain.setExtParams(JsonUtil.fromJson(accountDO.getExtParams(), HashMap.class));
        }
        return domain;
    }

    /**
     * @param accountDomain
     * @return
     */
    public static AccountDO convert2DO(AccountDomain accountDomain) {
        if (accountDomain == null) {
            return null;
        }
        AccountDO accountDO = new AccountDO();
        BeanUtil.copyPropertiesWithIgnores(accountDomain, accountDO);
        if (accountDomain.getGender() != null) {
            accountDO.setGender(accountDomain.getGender().toString());
        }
        if (null != accountDomain.getExtParams()) {
            accountDO.setExtParams(JsonConverterUtil.serialize(accountDomain.getExtParams()));
        }
        return accountDO;
    }

    /**
     *
     *
     * @param accountDOs
     * @return
     */
    public static List<AccountDomain> convert2Domain(List<AccountDO> accountDOs) {
        List<AccountDomain> accountDomains = new ArrayList<>();
        for (AccountDO accountDO : accountDOs) {
            accountDomains.add(convert2Domain(accountDO));
        }
        return accountDomains;
    }

    public static AccountDomain convertAccountSignUpDomainToAccountDomain(AccountSignUpDomain source) {
        if (null == source) {
            return null;
        }
        AccountDomain target = new AccountDomain();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        return  target;
    }
}
