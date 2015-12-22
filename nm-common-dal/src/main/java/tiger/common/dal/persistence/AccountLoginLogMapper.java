/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.persistence;

import org.apache.ibatis.annotations.Param;
import tiger.common.dal.dataobject.AccountLoginLogDO;

/**
 * 用户登录记录的数据库 Mapper
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 9, 2015 9:15:32 AM yiliang.gyl Exp $
 */
public interface AccountLoginLogMapper {

    /**
     * Find by token.
     *
     * @param token the token
     */
    AccountLoginLogDO findByToken(String token);

    /**
     * Save.
     *
     * @param accountLoginLogDo the accountLoginLogDo
     */
    void save(AccountLoginLogDO accountLoginLogDo);

    /**
     * Delete.
     *
     * @param accountId
     * @param token
     * @return
     */
    int delete(@Param("accountId") Long accountId, @Param("token") String token);

}
