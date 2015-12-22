/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tiger.common.dal.dataobject.MessageDO;
import tiger.common.dal.persistence.MessageMapper;
import tiger.common.dal.query.MessageQuery;
import tiger.common.util.Paginator;
import tiger.core.base.PageResult;
import tiger.core.domain.MessageDomain;
import tiger.core.domain.convert.MessageConvert;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;
import tiger.core.service.MessageService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class MessageServiceImpl.
 *
 * @author alfred.yx
 * @version v 0.1 Sep 23, 2015 10:32:27 AM alfred Exp $
 */
@Service
public class MessageServiceImpl implements MessageService {

    /** The message mapper. */
    @Autowired
    MessageMapper messageMapper;

    /**
     * Gets the message by id.
     *
     * @param id the id
     * @return the message by id
     * @see tiger.core.service.MessageService#getMessageById(Long)
     */
    @Override
    public MessageDomain getMessageById(Long id) {
        MessageDO messageDO = messageMapper.selectByPrimaryKey(id);
        if (null != messageDO) {
            return MessageConvert.convertDOToDomain(messageDO);
        }
        return null;
    }

    /**
     *  检查用户是否为该消息的接收者
     *
     * @param messageId the message id
     * @param accountId the account id
     * @return the boolean
     * @see tiger.core.service.MessageService#isReceiver(Long, Long)
     */
    @Override
    public boolean isReceiver(Long messageId, Long accountId) {
        MessageDO messageDO = messageMapper.selectByPrimaryKey(messageId);
        if (null == messageDO) {
            throw new AppException(ErrorCodeEnum.NOT_FOUND);
        }
        return accountId.equals(messageDO.getReceiverId());
    }

    /**
     * Update message.
     *
     * @param messageDomain the message domain
     * @return the int
     * @see tiger.core.service.MessageService#updateMessage(tiger.core.domain.MessageDomain)
     */
    @Override
    public boolean updateMessage(MessageDomain messageDomain) {
        MessageDO messageDO = MessageConvert.convertDomainToDO(messageDomain);
        int updateResult = messageMapper.updateByPrimaryKeySelective(messageDO);
        return checkReturnCode(updateResult);
    }

    /**
     * Delete message by id.
     *
     * @param id the id
     * @return the int
     * @see tiger.core.service.MessageService#deleteMessageByID(Long)
     */
    @Override
    public boolean deleteMessageByID(Long id) {
        int deleteResult = messageMapper.deleteByPrimaryKey(id);
        return checkReturnCode(deleteResult);
    }

    /**
     *  分页列出站内消息.
     *
     * @param query the query domain
     * @return the page result
     * @see tiger.core.service.MessageService#listMessages(MessageQuery)
     */
    @Override
    public PageResult<Object> listMessages(MessageQuery query) {
        PageResult<Object> messages = new PageResult<>();
        int totalItems = messageMapper.countMessages(query);
        // 分页器构建
        Paginator paginator = new Paginator();
        paginator.setItems(totalItems); // 设置符合筛选条件的总数
        paginator.setItemsPerPage(query.getPageSize()); // 设置页面大小
        paginator.setPage(query.getPageNum()); // 目前选择的页码

        messages.setData(MessageConvert.convertDOsToDomains(
                messageMapper.queryMessages(query, paginator.getOffset(), paginator.getLength())));
        messages.setPaginator(paginator);
        return messages;
    }

    /**
     * Send message.
     *
     * @param receiverID the receiver id
     * @param message the message
     * @return true, if successful
     * @see tiger.core.service.MessageService#sendMessage(Long, tiger.core.domain.MessageDomain)
     */
    @Override
    public boolean sendMessage(Long receiverID, MessageDomain message) {
        if (null == receiverID) {
            return false;
        }
        MessageDO messageDO = MessageConvert.convertDomainToDO(message);
        messageDO.setReceiverId(receiverID);
        int rc = messageMapper.insertSelective(messageDO);
        return checkReturnCode(rc);
    }

    /**
     * Send message.
     *
     * @param receiverIDs the receiver i ds
     * @param message the message
     * @return true, if successful
     * @see tiger.core.service.MessageService#sendMessages(List, tiger.core.domain.MessageDomain)
     */
    @Override
    public boolean sendMessages(List<Long> receiverIDs, MessageDomain message) {
        if (CollectionUtils.isEmpty(receiverIDs)) {
            return false;
        }
        Set<Long> distinctReceiverIDs = new HashSet<>(receiverIDs);
        List<MessageDO> messageDOs = new ArrayList<>();
        for (Long id : distinctReceiverIDs) {
            MessageDO messageDO = MessageConvert.convertDomainToDO(message);
            messageDO.setReceiverId(id);
            messageDOs.add(messageDO);
        }
        int rc = messageMapper.batchInsertSelective(messageDOs);
        return checkReturnCode(rc);
    }

    /**
     * 发送多条消息
     *
     * @param messageDomains
     * @return
     */
    public boolean sendMessages(List<MessageDomain> messageDomains){
        List<MessageDO> messageDOs = MessageConvert.convertDomainsToDOs(messageDomains);
        int rc = messageMapper.batchInsert(messageDOs);
        return checkReturnCode(rc);
    }

    @Override
    public int countMessages(MessageQuery query) {
        return messageMapper.countMessages(query);
    }

    // ~ private methods

    /**
     * Check return code.
     *
     * @param rc the rc
     * @return true, if successful
     */
    private boolean checkReturnCode(int rc) {
        return rc > 0;
    }
}
