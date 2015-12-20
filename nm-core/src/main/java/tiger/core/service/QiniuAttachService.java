/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;

import tiger.core.domain.AttachDomain;
import tiger.core.domain.QiniuUploadDomain;

/**
 *
 * @author alfred.yx
 * @version v 0.1 Oct 4, 2015 9:52:55 PM alfred Exp $
 */
public interface QiniuAttachService extends AttachService {

    /**
     * 根据附件id获取附件,授权url
     *
     * @param attachId
     * @return the qiniu attach by id
     */
    AttachDomain getAttachWithSignedUrlById(long attachId);

    /**
     * 获取七牛云上传授权token
     *
     * @return the qiniu upload token
     */
    QiniuUploadDomain getQiniuUploadToken(AttachDomain attachDomain, long accountId);

    /**
     * 七牛回调.
     *
     * @param attachDomain the attach domain
     * @return the attach domain
     */
    AttachDomain qiniuCallback(AttachDomain attachDomain, String originAuthorization, String callbackBody, String callbackContentType);

}
