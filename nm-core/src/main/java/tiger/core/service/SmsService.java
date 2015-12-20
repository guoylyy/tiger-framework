/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;


/**
 * @author zhangbin
 * @version v0.1 2015/10/6 17:27
 */
public interface SmsService {

    /**
     * 发送验证码code到手机mobile
     * @param mobile
     * @param code
     * @return
     */
    boolean sendVerifyCode(String mobile, String code);

    /**
     *
     * @param mobile
     * @param content
     * @return
     */
    boolean sendSmsWithContent(String mobile, String content);

    /**
     *
     * @param mobile
     * @param smsTplId
     * @return
     */
    boolean sendSms(String mobile, Long smsTplId);

}
