/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.qiniu;

import com.qiniu.util.Auth;

/**
 * @author alfred.yx
 * @version $ID: v 0.1 下午4:17 alfred.yx Exp $
 */
public class QiniuSecretHandler extends QiniuBaseHandler {
    public QiniuSecretHandler(Auth auth) {
        super(auth);
    }

    @Override
    public String getSignedUrl(String domainUrl, String url) {
       return auth.privateDownloadUrl(domainUrl + url);
    }
}
