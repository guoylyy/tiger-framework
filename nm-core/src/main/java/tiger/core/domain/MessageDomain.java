/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain;

import tiger.common.data.enums.MessageBizTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.base.BaseDomain;

import java.sql.Timestamp;

/**
 * 站內消息DEomain.
 *
 * @author alfred.yx
 * @version v 0.1 Sep 30, 2015 4:23:04 PM alfred Exp $
 */
public class MessageDomain extends BaseDomain {

    /**  */
    private static final long serialVersionUID = -3923833021322495838L;

    /** The receiver id. */
    private Long receiverId;

    /** The sender id. */
    private Long senderId;

    /** The is read. */
    @CopyIgnore
    private Boolean isRead;

    /** The is archived. */
    @CopyIgnore
    private Boolean isArchived;

    /** The is deleted. */
    @CopyIgnore
    private Boolean isDeleted;

    private String title;

    private String description;

    /** The content. */
    private String content;

    /** The biz id. */
    private Long bizId;

    /** The biz type. */
    @CopyIgnore
    private MessageBizTypeEnum bizType;

    /** The create time. */
    private Timestamp createTime;

    public MessageDomain() {}

    public MessageDomain(MessageBizTypeEnum bizType, String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.bizType = bizType;
    }

    public MessageDomain( MessageBizTypeEnum bizType, Long bizId, String title, String content, String description) {
        this(bizType, title, description, content);
        this.bizId = bizId;
    }

    public MessageDomain(MessageBizTypeEnum bizType, String title, String description, String content, Long receiverId) {
        this(bizType, title, description, content);
        this.receiverId = receiverId;
    }

    public MessageDomain(MessageBizTypeEnum bizType, Long bizId, String title, String content, String description, Long receiverId) {
        this(bizType, bizId, title, content, description);
        this.receiverId = receiverId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBizId() {
        return bizId;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public MessageBizTypeEnum getBizType() {
        return bizType;
    }

    public void setBizType(MessageBizTypeEnum bizType) {
        this.bizType = bizType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
