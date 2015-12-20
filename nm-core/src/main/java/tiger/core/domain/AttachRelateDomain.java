/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain;

import tiger.common.data.enums.AttachBizTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.base.BaseDomain;

/**
 *
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:40 AM yiliang.gyl Exp $
 */
public class AttachRelateDomain extends BaseDomain {

    private Long id;

    private Long subjectId;

    private Long attachId;

    @CopyIgnore
    private AttachBizTypeEnum bizType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public AttachBizTypeEnum getBizType() {
        return bizType;
    }

    public void setBizType(AttachBizTypeEnum bizType) {
        this.bizType = bizType;
    }
}
