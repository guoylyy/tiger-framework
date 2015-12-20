/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.convert;

import tiger.common.data.dataobject.SystemParamsDO;
import tiger.common.data.enums.SystemParamTypeEnum;
import tiger.common.util.BeanUtil;
import tiger.common.util.ByteUtil;
import tiger.common.util.StringUtil;
import tiger.core.domain.SystemParamDomain;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数类转换器.
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 4:17:27 PM yiliang.gyl Exp $
 */
public class SystemParamConvert {

    /**
     * Convert to do.
     *
     * @param systemParamDomain the system param domain
     * @return the system params do
     */
    public static SystemParamsDO convertToDo(SystemParamDomain systemParamDomain) {
        if (systemParamDomain == null) {
            return null;
        }
        SystemParamsDO systemParamsDO = new SystemParamsDO();
        BeanUtil.copyPropertiesWithIgnores(systemParamDomain, systemParamsDO);
        systemParamsDO.setParamType(systemParamDomain.getParamType().getCode());
        systemParamsDO.setIsActive(ByteUtil.getAsBytes(systemParamDomain.isActive())[0]);

        return systemParamsDO;

    }

    /**
     * Convert to domain.
     *
     * @param systemParamsDO the system params do
     * @return the system param domain
     */
    public static SystemParamDomain convertToDomain(SystemParamsDO systemParamsDO) {
        if (systemParamsDO == null) {
            return null;
        }
        SystemParamDomain systemParamDomain = new SystemParamDomain();
        BeanUtil.copyPropertiesWithIgnores(systemParamsDO, systemParamDomain);

        SystemParamTypeEnum.getEnumByCode(systemParamsDO.getParamType());

        systemParamDomain.setActive(ByteUtil.toBoolean(systemParamsDO.getIsActive()));
        return systemParamDomain;
    }

    /**
     * 将systemParams列表转化为Map<key, value>对
     * @param systemParams
     * @return
     */
    public static Map<String, String> convertToMap(List<SystemParamsDO> systemParams) {
        Map<String, String> paramMap = new HashMap<>();
        for (SystemParamsDO param : systemParams) {
            if(StringUtil.isEmpty(param.getParamName()) || StringUtil.isEmpty(param.getParamValue())) {
                throw new AppException(ErrorCodeEnum.PARAMETERS_IS_NULL);
            }
           paramMap.put(param.getParamName(), param.getParamValue());
        }
        return paramMap;
    }
}
