/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * The Class MessageQuery.
 *
 * @author alfred.yx
 * @version v 0.1 Sep 23, 2015 4:38:55 PM alfred Exp $
 */
public class MessageQuery extends BaseQuery {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7769632297807056605L;

    /** The receiver id. */
    private Long receiverId;

    /** The sender id. */
    private Long senderId;

    /**  是否已读， 默认为空. */
    private Boolean isRead;

    /**  是否归档， 默认未归档. */
    private Boolean isArchived = false;

    /** The biz type. */
    private String bizType;

    /**  开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate = null;

    /**  结束时间. */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate = null;

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

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
