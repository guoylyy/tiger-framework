/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.qiniu;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 七牛云配置文件
 *
 * @author alfred.yx
 * @version $ID: v 0.1 下午9:31 alfred.yx Exp $
 */
public class QiniuUtil {
    // 七牛域名
    private String QINIU_DOMAIN_URL;
    // 七牛空间
    private String BUCKET;
    // 七牛回调url
    private String CALLBACK_URL;
    // 七牛Handler
    private QiniuBaseHandler handler;

    public QiniuUtil(Map<String, String> paramMap, QiniuBaseHandler handler) {
        this.BUCKET = paramMap.get("BUCKET");
        this.CALLBACK_URL = paramMap.get("CALLBACK_URL");
        this.QINIU_DOMAIN_URL = paramMap.get("QINIU_DOMAIN_URL");
        this.handler = handler;
    }

    // 设置指定上传策略
    public String getCallbackUpToken(String callbackBody) {
        return handler.getCallbackUpToken(BUCKET, CALLBACK_URL, callbackBody);
    }

    // 获取七牛私有资源临时下载授权凭证
    public String getSignedUrl(String url) {
        return handler.getSignedUrl(QINIU_DOMAIN_URL, url);
    }

    /**
     * 删除七牛云文件
     * @param fileName
     */
    public boolean deleteFile(String fileName) {
        return handler.deleteFile(BUCKET, fileName);
    }

    /**
     * 验证回调的合法性
     */
    public boolean isValidCallback(String originAuthorization, String callbackBody, String callbackContentType) {
        boolean isValid = false;
        try {
            isValid = handler.isValidCallback(originAuthorization, CALLBACK_URL, URLDecoder.decode(callbackBody, "UTF-8"), callbackContentType);
        } catch (UnsupportedEncodingException e) {
            isValid = false;
        } finally {
            return isValid;
        }
    }
}
