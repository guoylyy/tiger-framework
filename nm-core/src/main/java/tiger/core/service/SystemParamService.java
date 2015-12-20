/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;

import tiger.common.data.enums.SystemParamTypeEnum;

import java.util.Map;

/**
 * 系统参数服务类
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 4:10:08 PM yiliang.gyl Exp $
 */
public interface SystemParamService {

    /**
     * 通过参数类型和 key 获取参数值得
     * @param paramType
     * @param key
     * @return
     */
    String getValueByTypeAndKey(SystemParamTypeEnum paramType, String key);

    /**
     * 通过 type 获取一组 parameters
     *
     * @param paramType
     * @return
     */
    Map<String, String> getParamsByType(SystemParamTypeEnum paramType);
}
