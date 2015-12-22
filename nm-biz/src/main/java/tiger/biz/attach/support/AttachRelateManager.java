/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.attach.support;

import tiger.core.base.BaseResult;
import tiger.core.domain.AttachDomain;
import tiger.core.domain.AttachRelateDomain;

import java.util.List;

/**
 * 负责对所有附件的关联操作
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:19 AM yiliang.gyl Exp $
 */
public interface AttachRelateManager {

    /**
     * 用户关联附件操作
     *
     * @param attachRelateDomain
     * @param accountId
     * @return
     */
    BaseResult<Boolean> relateAttach(AttachRelateDomain attachRelateDomain, long accountId);

    /**
     * 用户删除附件关联操作
     *
     * @param attachRelateDomain
     * @param accountId
     * @return
     */
    BaseResult<Boolean> deRelateAttach(AttachRelateDomain attachRelateDomain, long accountId);

    /**
     * 获取关联的附件操作
     *
     * @param attachRelateDomain
     * @return
     */
    BaseResult<List<AttachDomain>> listAttachs(AttachRelateDomain attachRelateDomain);
}
