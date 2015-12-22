/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.util;

import com.qiniu.util.Auth;
import tiger.common.dal.enums.AttachTypeEnum;
import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.util.StringUtil;
import tiger.common.util.component.qiniu.QiniuBaseHandler;
import tiger.common.util.component.qiniu.QiniuPublicHandler;
import tiger.common.util.component.qiniu.QiniuSecretHandler;
import tiger.common.util.component.qiniu.QiniuUtil;
import tiger.core.service.SystemParamService;

import java.util.Map;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午5:27 alfred.yx Exp $
 */
public class QiniuConfigFacotry {

    public static QiniuUtil createQiniuConfig(AttachTypeEnum attachType, SystemParamService systemParamService) {
        QiniuBaseHandler handler;
        Map<String, String> configMap;

        switch (attachType) {
            case PUBLIC:
                configMap = systemParamService.getParamsByType(SystemParamTypeEnum.QINIU_PUBLIC_PARAM);
                handler = new QiniuPublicHandler(getAuth(configMap));
                break;
            case SECRET:
                configMap = systemParamService.getParamsByType(SystemParamTypeEnum.QINIU_SECRET_PARAM);
                handler = new QiniuSecretHandler(getAuth(configMap));
                break;
            default:
                return null;
        }

        return new QiniuUtil(configMap, handler);
    }

    private static Auth getAuth(Map<String, String> config) {
        if (StringUtil.isEmpty(config.get("ACCESS_KEY")) || StringUtil.isEmpty(config.get("SECRET_KEY"))) {
            throw new IllegalArgumentException("非法的七牛配置参数");
        }
        return Auth.create(config.get("ACCESS_KEY"), config.get("SECRET_KEY"));
    }

}
