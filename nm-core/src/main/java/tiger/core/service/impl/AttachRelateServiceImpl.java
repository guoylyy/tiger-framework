/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.common.dal.dataobject.AttachRelateDO;
import tiger.common.dal.dataobject.example.AttachRelateExample;
import tiger.common.dal.persistence.AttachMapper;
import tiger.common.dal.persistence.AttachRelateMapper;
import tiger.common.dal.query.AttachRelateQuery;
import tiger.core.domain.AttachDomain;
import tiger.core.domain.AttachRelateDomain;
import tiger.core.domain.convert.AttachConverter;
import tiger.core.domain.convert.AttachRelateConvert;
import tiger.core.service.AttachRelateService;

import java.util.ArrayList;
import java.util.List;

/**
 * 关联贷款附件的实现类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 2:03 PM yiliang.gyl Exp $
 */
@Service
public class AttachRelateServiceImpl implements AttachRelateService {

    @Autowired
    AttachMapper attachMapper;

    @Autowired
    AttachRelateMapper attachRelateMapper;

    /**
     * @see AttachRelateService#listAttaches(AttachRelateQuery)
     */
    @Override
    public List<AttachDomain> listAttaches(AttachRelateQuery relateQuery) {
        AttachRelateExample example = new AttachRelateExample();
        example.createCriteria().andSubjectIdEqualTo(relateQuery.getSubjectId())
                .andBizTypeEqualTo(relateQuery.getBizTypeEnum().getCode());
        List<Long> ids = new ArrayList<>();
        List<AttachRelateDO> attachDOs = attachRelateMapper.selectByExample(example);
        if (attachDOs.size() > 0) {
            for (AttachRelateDO relateDO : attachDOs) {
                ids.add(relateDO.getAttachId());
            }
            return AttachConverter.convert2Domain(attachMapper.listAttachesByIds(ids));

        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @see AttachRelateService#relateAttach(AttachRelateDomain)
     */
    @Override
    public boolean relateAttach(AttachRelateDomain relateDomain) {
        AttachRelateDO attachRelateDO = AttachRelateConvert.convertToAttachRelateDO(relateDomain);
        return attachRelateMapper.insert(attachRelateDO) > 0;
    }

    /**
     * @see AttachRelateService#deRelateAttachById(AttachRelateDomain)
     */
    @Override
    public boolean deRelateAttachById(AttachRelateDomain relateDomain) {
        AttachRelateExample example = new AttachRelateExample();
        example.createCriteria().andSubjectIdEqualTo(relateDomain.getSubjectId())
                .andBizTypeEqualTo(relateDomain.getBizType().getCode())
                .andAttachIdEqualTo(relateDomain.getAttachId());
        return attachRelateMapper.deleteByExample(example) > 0;
    }

    @Override
    public boolean deRelatedAttachesByQuery(AttachRelateQuery query) {
        AttachRelateExample example = new AttachRelateExample();
        AttachRelateExample.Criteria criteria = example.createCriteria();
        if (query.getBizTypeEnum() != null) {
            criteria.andBizTypeEqualTo(query.getBizTypeEnum().getCode());
        }
        if (query.getSubjectId() != null) {
            criteria.andSubjectIdEqualTo(query.getSubjectId());
        }

        return attachRelateMapper.deleteByExample(example) > 0;
    }

    /**
     * @see AttachRelateService#relateAttaches(List)
     */
    @Override
    public boolean relateAttaches(List<AttachRelateDomain> relateDomains) {
        List<AttachRelateDO> relateDOs = AttachRelateConvert.convertToLoanPawnAttachDOs(relateDomains);
        return attachRelateMapper.batchInsert(relateDOs) > 0;
    }

    @Override
    public boolean isExist(AttachRelateDomain relateDomain) {
        AttachRelateExample example = new AttachRelateExample();
        example.createCriteria().andSubjectIdEqualTo(relateDomain.getSubjectId())
                .andBizTypeEqualTo(relateDomain.getBizType().getCode())
                .andAttachIdEqualTo(relateDomain.getAttachId());
        return attachRelateMapper.countByExample(example) > 0;
    }
}
