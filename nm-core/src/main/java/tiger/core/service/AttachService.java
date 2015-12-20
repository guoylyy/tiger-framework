/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;

import tiger.common.data.enums.AttachTypeEnum;
import tiger.core.domain.AttachDomain;

import java.util.List;

/**
 * 附件Service
 *
 * @author alfred.yx
 * @version v 0.1 Oct 3, 2015 11:26:28 PM alfred Exp $
 */
public interface AttachService {


    /**
     * 根据附件id删除该附件
     *
     * @param attachId
     * @return Boolean
     */
    boolean deleteAttachById(long attachId);

    /**
     * 判断抵押物ids是否都存在
     * @param attachIds
     * @return
     */
    boolean isAllExist(List<Long> attachIds);

    /**
     * 判断抵押物id是否存在
     * @param id
     * @return
     */
    boolean isExist(long id);

    /**
     *  通过id获取附件模型
     * @param id
     * @return
     */
    AttachDomain read(long id);

    /**
     * 检查附件attachId是否为accountId所有
     * @param attachId
     * @param accountId
     */
    boolean isOwner(long attachId, long accountId);

    /**
     * 检查附件attachIds是否都为accountId所有
     * @param attachIds
     * @param accountId
     */
    boolean isOwnerOfAll(List<Long> attachIds, long accountId);

    /**
     * 判断附件attachId是否为对应的attachtype
     * @param attachId
     * @param attachType
     * @return
     */
    boolean isAttachType(long attachId, AttachTypeEnum attachType);

    /**
     * 判断附件attachIds是否为对应的attachtype
     * @param attachIds
     * @param attachType
     * @return
     */
    boolean isAllAttachType(List<Long> attachIds, AttachTypeEnum attachType);
}
