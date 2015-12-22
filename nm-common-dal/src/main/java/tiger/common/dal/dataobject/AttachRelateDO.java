/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.dataobject;

import tiger.common.util.annotation.CopyIgnore;

import java.io.Serializable;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 11:32 AM yiliang.gyl Exp $
 */
public class AttachRelateDO implements Serializable {
    private Long id;

    private Long subjectId;

    private Long attachId;

    @CopyIgnore
    private String bizType;

    private String extParams;

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

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getExtParams() {
        return extParams;
    }

    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }
}
