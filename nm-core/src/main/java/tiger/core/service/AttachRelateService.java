/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;

import tiger.common.dal.query.AttachRelateQuery;
import tiger.core.domain.AttachDomain;
import tiger.core.domain.AttachRelateDomain;

import java.util.List;

/**
 * 负责附件关联工作的组件
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:37 AM yiliang.gyl Exp $
 */
public interface AttachRelateService {

    /**
     *
     * @param relateQuery
     * @return
     */
    List<AttachDomain>  listAttaches(AttachRelateQuery relateQuery);

    /**
     * 为抵押物增加一个抵押物附件
     * @return
     */
    boolean relateAttach(AttachRelateDomain relateDomain);

    /**
     * 根据id删除抵押物附件
     * @return
     */
    boolean deRelateAttachById(AttachRelateDomain relateDomain);

    /**
     * 根据query的条件删除相关附件关联
     *
     * @param query
     * @return
     */
    boolean deRelatedAttachesByQuery(AttachRelateQuery query);

    /**
     * 关联一组附件
     *
     * @param relateDomains
     * @return
     */
    boolean relateAttaches(List<AttachRelateDomain> relateDomains);

    /**
     * 检查relateDomain是否已经存在，主要比较subjectId, bizType, attachId
     *
     * @param relateDomain
     * @return
     */
    boolean isExist(AttachRelateDomain relateDomain);

}
