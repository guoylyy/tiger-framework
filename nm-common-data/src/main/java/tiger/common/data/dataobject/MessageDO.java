package tiger.common.data.dataobject;

import tiger.common.util.annotation.CopyIgnore;

public class MessageDO extends BaseDO {

    private static final long serialVersionUID = -4569700496299208895L;

    private Long receiverId;

    private Long senderId;

    @CopyIgnore
    private Byte isRead;

    @CopyIgnore
    private Byte isArchived;

    @CopyIgnore
    private Byte isDeleted;

    private String title;

    private String description;

    private String content;

    private Long bizId;

    @CopyIgnore
    private String bizType;

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

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public Byte getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Byte isArchived) {
        this.isArchived = isArchived;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
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

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

}
