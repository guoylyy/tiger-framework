/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import tiger.common.util.component.sms.SmsComponent;
import tiger.core.service.SmsService;

/**
 * @author zhangbin
 * @version v0.1 2015/10/6 17:38
 */
@Service
public class SmsServiceImpl implements SmsService {
    private static final int FIRST_INDEX = 0;

    private Logger logger = Logger.getLogger(SmsServiceImpl.class);

    @Override
    public boolean sendVerifyCode(String mobile, String code) {
        boolean result = SmsComponent.sendVerifyCode(mobile, code);
        return result;
    }

    @Override
    public boolean sendSmsWithContent(String mobile, String content) {
        boolean result = SmsComponent.SendSms(mobile, content);
        return result;
    }


}
