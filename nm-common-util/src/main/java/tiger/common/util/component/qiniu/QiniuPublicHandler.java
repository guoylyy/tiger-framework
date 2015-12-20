/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.qiniu;

import com.qiniu.util.Auth;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午4:09 alfred.yx Exp $
 */
public class QiniuPublicHandler extends QiniuBaseHandler {
    public QiniuPublicHandler(Auth auth) {
        super(auth);
    }

    @Override
    public String getSignedUrl(String domainUrl, String url) {
        return domainUrl + url;
    }
}
