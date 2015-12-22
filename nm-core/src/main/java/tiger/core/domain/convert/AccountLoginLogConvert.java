package tiger.core.domain.convert;

import tiger.common.dal.dataobject.AccountLoginLogDO;
import tiger.common.util.BeanUtil;
import tiger.core.domain.AccountLoginLogDomain;

import java.sql.Timestamp;

/**
 * Created by alfred on 15-11-4.
 */
public class AccountLoginLogConvert {
    private AccountLoginLogConvert() {}

    public static AccountLoginLogDO convertToDO(AccountLoginLogDomain source) {
        if (null == source) {
            return null;
        }
        AccountLoginLogDO target = new AccountLoginLogDO();
        BeanUtil.copyPropertiesWithIgnores(source, target);
        // Date无法转换成TimeStamp
        target.setExpireTime(new Timestamp(source.getExpireTime().getTime()));
        return target;
    }
}
