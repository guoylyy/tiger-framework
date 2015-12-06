/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.data.dataobject.AccountDO;
import tiger.common.data.persistence.AccountMapper;
import tiger.common.util.annotation.RedisCache;
import tiger.core.domain.CodeDomain;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:47 PM yiliang.gyl Exp $
 */
@Service
public class CodeService {

    @Autowired
    AccountMapper accountMapper;

    @RedisCache
    public boolean persitentCode(CodeDomain codeDomain){
        System.out.println("start to persistent code");

        AccountDO accountDO = accountMapper.read(1);
        System.out.println("查询到用户" +accountDO);

        return true;
    }

}
