/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain.convert;

import tiger.common.dal.dataobject.MessageDO;
import tiger.common.dal.enums.MessageBizTypeEnum;
import tiger.common.util.BeanUtil;
import tiger.common.util.ByteUtil;
import tiger.core.domain.MessageDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class MessageConvert.
 *
 * @author alfred.yx
 * @version v 0.1 Sep 23, 2015 10:43:25 AM alfred Exp $
 */
public class MessageConvert {

    /**
     * Convert Domain to DO.
     *
     * @param messageDomain the message Domain
     * @return the message DO
     */
    public static MessageDO convertDomainToDO(MessageDomain messageDomain) {
        if (null == messageDomain) {
            return null;
        }
        MessageDO messageDO = new MessageDO();
        BeanUtil.copyPropertiesWithIgnores(messageDomain, messageDO);
        // 为messageDO设置bizType默认值
        if (null != messageDomain.getBizType()) {
            messageDO.setBizType(messageDomain.getBizType().getCode());
        }
        // 为MessageDO设置is系列的值
        if (null != messageDomain.getIsArchived()) {
            messageDO.setIsArchived(ByteUtil.booleanToByte(messageDomain.getIsArchived()));
        } else {
            messageDO.setIsArchived(ByteUtil.BYTE_ZERO);
        }
        if (null != messageDomain.getIsDeleted()) {
            messageDO.setIsDeleted(ByteUtil.booleanToByte(messageDomain.getIsDeleted()));
        } else {
            messageDO.setIsDeleted(ByteUtil.BYTE_ZERO);
        }
        if (null != messageDomain.getIsRead()) {
            messageDO.setIsRead(ByteUtil.booleanToByte(messageDomain.getIsRead()));
        } else {
            messageDO.setIsRead(ByteUtil.BYTE_ZERO);
        }
        return messageDO;
    }

    /**
     * Convert Domains To DOs.
     *
     * @param messageDomains
     * @return
     */
    public static List<MessageDO> convertDomainsToDOs(List<MessageDomain> messageDomains){
        List<MessageDO> messageDOs = new ArrayList<>();
        for(MessageDomain messageDomain : messageDomains){
            messageDOs.add(convertDomainToDO(messageDomain));
        }
        return messageDOs;
    }

    /**
     * Convert DOs to Domain.
     *
     * @param messageDO the message DO
     * @return the message Domain
     */
    public static MessageDomain convertDOToDomain(MessageDO messageDO) {
        if (null == messageDO) {
            return  null;
        }
        MessageDomain messageDomain = new MessageDomain();
        BeanUtil.copyPropertiesWithIgnores(messageDO, messageDomain);
        // 为messageDomain设置bizType默认值
        messageDomain.setBizType(MessageBizTypeEnum.getEnumByCode(messageDO.getBizType()));
        // 为messageDomain设置is系列的值a
        if (null != messageDO.getIsArchived()) {
            messageDomain.setIsArchived(ByteUtil.toBoolean(messageDO.getIsArchived()));
        }
        if (null != messageDO.getIsDeleted()) {
            messageDomain.setIsDeleted(ByteUtil.toBoolean(messageDO.getIsDeleted()));
        }
        if (null != messageDO.getIsRead()) {
            messageDomain.setIsRead(ByteUtil.toBoolean(messageDO.getIsRead()));
        }
        return messageDomain;
    }


    /**
     * Convert DOs to Domains.
     *
     * @param messageDOs the message DOs
     * @return the list
     */
    public static List<MessageDomain> convertDOsToDomains(List<MessageDO> messageDOs) {
        List<MessageDomain> messageDomains = new ArrayList<>();
        if (!messageDOs.isEmpty()) {
            messageDomains.addAll(messageDOs.stream().map(MessageConvert::convertDOToDomain).collect(Collectors.toList()));
        }
        return messageDomains;
    }
}
