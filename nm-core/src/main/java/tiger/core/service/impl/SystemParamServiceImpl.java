/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.dataobject.SystemParamsDO;
import tiger.common.dal.dataobject.example.SystemParamsExample;
import tiger.common.dal.dataobject.example.SystemParamsExample.Criteria;
import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.dal.persistence.SystemParamsMapper;
import tiger.common.util.ByteUtil;
import tiger.core.domain.convert.SystemParamConvert;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;
import tiger.core.service.SystemParamService;

import java.util.List;
import java.util.Map;

/**
 * 系统参数实现类
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 4:48:55 PM yiliang.gyl Exp $
 */
@Service
public class SystemParamServiceImpl implements SystemParamService {

    protected static Logger logger = Logger.getLogger(SystemParamServiceImpl.class);

    @Autowired
    private SystemParamsMapper systemParamsMapper;

    /**
     * @see tiger.core.service.SystemParamService#getValueByTypeAndKey(SystemParamTypeEnum, String)
     */
    @Override
    public String getValueByTypeAndKey(SystemParamTypeEnum paramType, String key) {
        SystemParamsExample example = new SystemParamsExample();
        Criteria criteria = example.createCriteria();
        criteria.andParamNameEqualTo(key);
        criteria.andParamTypeEqualTo(paramType.getCode());
        List<SystemParamsDO> paramsDOs = systemParamsMapper.selectByExample(example);
        if (paramsDOs.size() == 1) {
            if (logger.isInfoEnabled()) {
                logger.info("获取到了正确的系统参数:" + paramsDOs.get(0));
            }
            SystemParamsDO paramsDO = paramsDOs.get(0);
            return paramsDO.getParamValue();
        } else if (paramsDOs.size() > 1) {
            logger.error("获取到" + paramsDOs.size() + "个系统参数实例，无法选择, pranType=" + paramType
                    + ", parmName=" + key + ".");
            throw new AppException(ErrorCodeEnum.DB_EXCEPTION.getCode(),
                    "获取到" + paramsDOs.size() + "个系统参数实例，无法选择");
        } else {
            logger.error("没有获取到任何参数");
            return null;
        }

    }

    /**
     * @see SystemParamService#getParamsByType(SystemParamTypeEnum)
     */
    @Override
    public Map<String, String> getParamsByType(SystemParamTypeEnum paramType) {
        SystemParamsExample example = new SystemParamsExample();
        Criteria criteria = example.createCriteria();
        criteria.andParamTypeEqualTo(paramType.getCode());
        criteria.andIsActiveEqualTo(ByteUtil.BYTE_ONE);
        return SystemParamConvert.convertToMap(systemParamsMapper.selectByExample(example));
    }

}
