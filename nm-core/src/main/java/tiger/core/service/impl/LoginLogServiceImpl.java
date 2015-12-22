package tiger.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.dataobject.AccountLoginLogDO;
import tiger.common.dal.persistence.AccountLoginLogMapper;
import tiger.core.domain.AccountLoginLogDomain;
import tiger.core.domain.convert.AccountLoginLogConvert;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;
import tiger.core.service.LoginLogService;

/**
 * Created by Hupeng on 2015/10/9.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private AccountLoginLogMapper accountLoginLogMapper;

    /**
     * 通过token获取用户数据
     * @param token
     * @return
     */
    @Override
    public long getAccountIdByToken(String token) {
        AccountLoginLogDO accountLoginLogDO = accountLoginLogMapper.findByToken(token);
        if(accountLoginLogDO == null){
            return 0;
        }
        return accountLoginLogDO.getAccountId();
    }

    @Override
    public void createLog(AccountLoginLogDomain loginLogDomain) {
        if (null == loginLogDomain) {
            throw new AppException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }

        accountLoginLogMapper.save(AccountLoginLogConvert.convertToDO(loginLogDomain));
    }
}
