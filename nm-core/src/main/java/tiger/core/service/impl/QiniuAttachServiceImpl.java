/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tiger.common.dal.dataobject.AttachDO;
import tiger.common.dal.enums.AttachTypeEnum;
import tiger.common.dal.persistence.AttachMapper;
import tiger.common.util.ByteUtil;
import tiger.common.util.JsonConverterUtil;
import tiger.core.domain.AttachDomain;
import tiger.core.domain.QiniuUploadDomain;
import tiger.core.domain.convert.AttachConverter;
import tiger.core.enums.ErrorCodeEnum;
import tiger.core.exception.AppException;
import tiger.core.service.AttachService;
import tiger.core.service.SystemParamService;
import tiger.core.util.QiniuConfigFacotry;

import java.util.*;

/**
 * @author alfred.yx
 * @version v 0.1 Oct 3, 2015 11:48:36 PM alfred Exp $
 */
@Service
public class QiniuAttachServiceImpl implements AttachService {
    private static Logger logger = Logger.getLogger(QiniuAttachServiceImpl.class);
    private final static int MAX_FILENAME_LENGTH = 122;
    private final static int UUID_LENGTH = 6;

    @Autowired
    AttachMapper attachMapper;

    @Autowired
    SystemParamService systemParamService;

    @Override
    public AttachDomain getAttachWithSignedUrlById(long attachId) {
        AttachDomain qiniuAttach = AttachConverter
                .convert2Domain(attachMapper.selectByPrimaryKey(attachId));
        if (null == qiniuAttach) {
            return null;
        }
        // 设置七牛signed url, 默认时长3600s
        qiniuAttach.setUrl(QiniuConfigFacotry
                .createQiniuConfig(qiniuAttach.getAttachType(), systemParamService).getSignedUrl(qiniuAttach.getUrl()));

        return qiniuAttach;
    }

    /**
     * @see tiger.core.service.QiniuAttachService#getQiniuUploadToken(AttachDomain, long)
     */
    @Override
    public QiniuUploadDomain getQiniuUploadToken(AttachDomain attachDomain,
                                                 long accountId) {
        // 1. 先检查唯一性
        String randomFilename = getRandomName(attachDomain.getName(), accountId);
        if (isFilenameExist(randomFilename)) {
            throw new AppException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE, "该附件名已存在");
        }
        // 2. 生成七牛callbackBody
        String callbackBody = getCallbackBody(randomFilename, attachDomain, accountId);
        // 2. 设置返回domain
        QiniuUploadDomain uploadToken = new QiniuUploadDomain();
        uploadToken.setKey(randomFilename);
        uploadToken.setToken(QiniuConfigFacotry
                .createQiniuConfig(attachDomain.getAttachType(), systemParamService).getCallbackUpToken(callbackBody));
        return uploadToken;
    }

    /**
     * @see tiger.core.service.AttachService#deleteAttachById(long)
     */
    @Override
    public boolean deleteAttachById(long attachId) {
        AttachDomain attachDomain = AttachConverter.convert2Domain(attachMapper.selectByPrimaryKey(attachId));
        if (null == attachDomain) {
            // 如果attachId不存在这返回true
            return true;
        }
        // 1. 先删除附件及其关系
        attachMapper.deleteAttachById(attachId);
        // 2. 删除七牛云文件
        if (logger.isInfoEnabled()) {
            logger.info("删除七牛云文件， id: " + attachId + "， key: " + attachDomain.getUrl());
        }
        return QiniuConfigFacotry.createQiniuConfig(attachDomain.getAttachType(), systemParamService)
                .deleteFile(attachDomain.getUrl());
    }

    /**
     *
     * @see tiger.core.service.AttachService#isAllExist(List)
     */
    @Override
    public boolean isAllExist(List<Long> attachIds) {
        if (CollectionUtils.isEmpty(attachIds)) {
            return true;
        }
        int counts = attachMapper.countExistedIds(attachIds, null, null);
        if (attachIds.size() != counts) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isExist(long id) {
        AttachDO attachDO = attachMapper.selectByPrimaryKey(id);
        if (null == attachDO) {
            return false;
        }
        return true;
    }

    @Override
    public AttachDomain read(long id) {
        AttachDO attachDO = attachMapper.selectByPrimaryKey(id);
        return AttachConverter.convert2Domain(attachDO);
    }

    @Override
    public AttachDomain qiniuCallback(AttachDomain attachDomain, String originAuthorization, String callbackBody, String callbackContentType) {
        attachDomain = setCallbackBody(attachDomain);
        // 验证是否为合法的callback
        if (!QiniuConfigFacotry.createQiniuConfig(attachDomain.getAttachType(), systemParamService)
                .isValidCallback(originAuthorization, callbackBody, callbackContentType)) {
            throw new AppException(ErrorCodeEnum.ILLEGAL_PARAMETER);
        }
        // 如果已经存在则认为token已经被使用,返回错误
        if (isFilenameExist(attachDomain.getUrl())) {
            throw new AppException(ErrorCodeEnum.BIZ_EXPIRED);
        }
        AttachDO attachDo = AttachConverter.convert2DO(attachDomain);
        int insertResult = attachMapper.insertSelective(attachDo);
        if (insertResult > 0) {
            return AttachConverter.convert2Domain(attachDo);
        }
        throw new AppException(ErrorCodeEnum.DB_EXCEPTION);
    }

    /**
     * 检查附件是否为用户所有.
     *
     * @param attachId
     * @param accountId
     */
    public boolean isOwner(long attachId, long accountId) {
        AttachDO attachDO = attachMapper.selectByPrimaryKey(attachId);
        // 如果Attch不存在，则抛出NOT_FOUND
        if (null == attachDO) {
            return false;
        }
        // 如果attach的accountId与参数accountId不符,则返回false
        if (accountId != attachDO.getAccountId()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isOwnerOfAll(List<Long> attachIds, long accountId) {
        if (CollectionUtils.isEmpty(attachIds)) {
            return true;
        }
        int counts = attachMapper.countExistedIds(attachIds, accountId, null);
        if (attachIds.size() != counts) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isAttachType(long attachId, AttachTypeEnum attachType) {
        AttachDomain attachDomain = getAttachWithSignedUrlById(attachId);
        if (attachType.equals(attachDomain.getAttachType())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAllAttachType(List<Long> attachIds, AttachTypeEnum attachType) {
        if (CollectionUtils.isEmpty(attachIds)) {
            return true;
        }
        return attachIds.size() == attachMapper.countExistedIds(attachIds, null, attachType.getCode());
    }
    // ~ Private Method

    /**
     * 在fileName的基础上增加6位随机id
     *
     * @param fileName  the file name
     * @param accountId the account id
     * @return the string
     */
    private String getRandomName(String fileName, long accountId) {
        // 校验文件名长度
        if (fileName.length() > MAX_FILENAME_LENGTH) {
            throw new AppException(ErrorCodeEnum.LENGTH_OVER_MAX_LIMITED);
        }
        // 校验文件名是否为 文件名.文件类型 格式
        int lastDotPosition = fileName.lastIndexOf(".");
        if (0 >= lastDotPosition) {
            throw new AppException(ErrorCodeEnum.ILLEGAL_PARAMETER_VALUE);
        }
        // 拼接文件名
        String filePrefix = fileName.substring(0, lastDotPosition);
        String fileSuffix = fileName.substring(lastDotPosition + 1, fileName.length());
        String uuid = UUID
                .nameUUIDFromBytes(ByteUtil.getAsBytes(System.currentTimeMillis() + "" + accountId))
                .toString();
        StringBuilder newFileName = new StringBuilder();
        newFileName.append(filePrefix).append(uuid.substring(uuid.length() - UUID_LENGTH)).append(".")
                .append(fileSuffix);
        return newFileName.toString();
    }


    /**
     * 检查'fileName'是否已经存在，若存在则抛出异常
     *
     * @param fileName the file name
     */
    private boolean isFilenameExist(String fileName) {
        return 0 < attachMapper.countByUrl(fileName);
    }

    /**
     * 生成七牛云的回调callBody
     * 并对参数进行base 64转义
     *
     * @param randomFilename
     * @param attachDomain
     * @param accountId
     * @return
     */
    private String getCallbackBody(String randomFilename, AttachDomain attachDomain,
                                   long accountId) {
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        HashMap<String, String> encodedMap = new HashMap<>();

        for (Map.Entry<String, String> entry : attachDomain.getMetaData().entrySet()) {
            encodedMap.put(
                    base64Encoder.encodeToString(entry.getKey().getBytes()),
                    base64Encoder.encodeToString(entry.getValue().getBytes())
            );
        }

        String format = "url=%s&name=%s&size=$(fsize)&attachType=%s&metaData=%s&accountId=%d";
        String callBackBody = String.format(
                format,
                base64Encoder.encodeToString(randomFilename.getBytes()),
                base64Encoder.encodeToString(attachDomain.getName().getBytes()),
                attachDomain.getAttachType(),
                JsonConverterUtil.serialize(encodedMap),
                accountId
        );

        return callBackBody;
    }

    /**
     * 将getCallbackBody中转义的参数，进行反转义
     * @param attachDomain
     * @return
     */
    private AttachDomain setCallbackBody(AttachDomain attachDomain) {
        Base64.Decoder base64Decoder = Base64.getUrlDecoder();
        HashMap<String, String> decodedMap = new HashMap<>();

        for (Map.Entry<String, String> entry : attachDomain.getMetaData().entrySet()) {
            decodedMap.put(
                    new String(base64Decoder.decode(entry.getKey())),
                    new String(base64Decoder.decode(entry.getValue()))
            );
        }

        attachDomain.setUrl(new String(base64Decoder.decode(attachDomain.getUrl())));
        attachDomain.setName(new String(base64Decoder.decode(attachDomain.getName())));
        attachDomain.setMetaData(decodedMap);

        return attachDomain;
    }

}
