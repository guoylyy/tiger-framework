/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.data.persistence;

import tiger.common.data.dataobject.AccountDO;

import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:32 PM yiliang.gyl Exp $
 */
public interface AccountMapper {

    AccountDO read(long id);

    List<AccountDO> listAccounts();

    int delete(long id);

    int update(long id);

    int inset(AccountDO accountDO);

}
