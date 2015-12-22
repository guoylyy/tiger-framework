/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.attach.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.biz.attach.support.AttachRelateManager;
import tiger.common.dal.enums.AttachTypeEnum;
import tiger.common.dal.query.AttachRelateQuery;
import tiger.core.base.BaseResult;
import tiger.core.domain.AttachDomain;
import tiger.core.domain.AttachRelateDomain;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.service.AttachRelateService;
import tiger.core.service.AttachService;

import java.util.List;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 2:26 PM yiliang.gyl Exp $
 */
@Service
public class AttachRelateManagerImpl implements AttachRelateManager {

    @Autowired
    private AttachRelateService attachRelateService;

    @Autowired
    private AttachService qiniuAttachService;

    /**
     * @see AttachRelateManager#relateAttach(AttachRelateDomain, long)
     */
    @Override
    public BaseResult<Boolean> relateAttach(AttachRelateDomain attachRelateDomain, long accountId) {
        AttachDomain attach = qiniuAttachService.read(attachRelateDomain.getAttachId());
        // 检查附件是否存在
        if (attach == null) {
            return new BaseResult<>(ErrorCodeEnum.NOT_FOUND, false);
        }
        // 检查附件是否为当前用户所有
        if (!attach.getAccountId().equals(accountId)) {
            return new BaseResult<>(ErrorCodeEnum.UNAUTHORIZED, false);
        }
        // 检查附件是否为私有附件
        if (!attach.getAttachType().equals(AttachTypeEnum.SECRET)) {
            return new BaseResult<>(ErrorCodeEnum.ILLEGAL_PARAMETER, false);
        }
        // 检查当前关联是否已经存在
        if (attachRelateService.isExist(attachRelateDomain)) {
            return new BaseResult<>(ErrorCodeEnum.BIZ_DUPLICATIVE);
        }

        if (attachRelateService.relateAttach(attachRelateDomain)) {
            return new BaseResult<>(true);
        }

        return new BaseResult<>(ErrorCodeEnum.BIZ_FAIL, false);
    }

    /**
     * @see AttachRelateManager#deRelateAttach(AttachRelateDomain, long)
     */
    @Override
    public BaseResult<Boolean> deRelateAttach(AttachRelateDomain attachRelateDomain, long accountId) {
        boolean result = attachRelateService.deRelateAttachById(attachRelateDomain);
        if(result){
            return new BaseResult<>(result);
        }else{
            return new BaseResult<>(ErrorCodeEnum.BIZ_FAIL, result);
        }
    }

    /**
     * @see AttachRelateManager#listAttachs(AttachRelateDomain)
     */
    @Override
    public BaseResult<List<AttachDomain>> listAttachs(AttachRelateDomain attachRelateDomain) {
        AttachRelateQuery query = new AttachRelateQuery();
        query.setSubjectId(attachRelateDomain.getSubjectId());
        query.setBizTypeEnum(attachRelateDomain.getBizType());
        return new BaseResult<>(attachRelateService.listAttaches(query));
    }
}
