/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.sms;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import tiger.common.util.HttpUtil;
import tiger.common.util.JsonUtil;
import tiger.common.util.PhoneUtil;
import tiger.common.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangbin
 * @version v0.1 2015/9/26 11:02
 */
public class SmsComponent {

    private static Logger logger = Logger.getLogger(SmsComponent.class);


    private static final String DEFAULT_MSG_TEXT_FMT = "【小锅科技】 您的验证码是%s,请勿透漏给别人（十分钟有效）";
    private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";
    private static String MULTI_SEND_SMS = "http://yunpian.com/v1/sms/multi_send.json";
    private static final String SEND_SMS_URL = "http://yunpian.com/v1/sms/send.json";
    private static final String API_KEY = "924b91cae300188f5d6883598f7bb9e3";

    /**
     * 发送短信验证码
     *
     * @param mobile the mobile
     * @param code   the code
     */
    public static Boolean sendVerifyCode(String mobile, String code) {
        if (!PhoneUtil.isValidMobile(mobile) || StringUtil.isBlank(code)) {
            return false;
        }
        Map<String, String> paras = new HashMap<String, String>();
        String text = String.format(DEFAULT_MSG_TEXT_FMT, code);
        paras.put("apikey", API_KEY);
        paras.put("text", text);
        paras.put("mobile", mobile);
        try {
            String returnMsg = HttpUtil.postForm(SEND_SMS_URL, paras);
            if (logger.isInfoEnabled()) {
                logger.info(returnMsg);
            }
            SmsResponse response = JsonUtil.fromJson(returnMsg, SmsResponse.class);
            if (response.getCode() == 0) {
                return true;
            }
        } catch (IOException e) {
            logger.error("发送短信验证码给[" + mobile + "];异常 [" + e + "]");
            return false;
        }
        return false;
    }

    /**
     * 指定模板发送短信
     *
     * @param tplId
     * @param tplValue
     * @param mobile
     * @return
     */
    public static String sendTplSms(long tplId, String tplValue, String mobile) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", API_KEY);
        params.put("tpl_id", String.valueOf(tplId));
        params.put("tpl_value", tplValue);
        params.put("mobile", mobile);
        try {
            return HttpUtil.postForm(URI_TPL_SEND_SMS, params);
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

    public static boolean SendSms(String mobile, String text) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("apikey", API_KEY);
        paras.put("text", text);
        paras.put("mobile", mobile);
        try {
            String returnMsg = HttpUtil.postForm(SEND_SMS_URL, paras);
            logger.info(returnMsg);
            SmsResponse response = JsonUtil.fromJson(returnMsg, SmsResponse.class);
            return response.getCode() == 0;
        } catch (IOException e) {
            logger.error(e);
            return false;
        }
    }

    public static List<SmsResponse> multiSendSms(String mobile, String text) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("apikey", API_KEY);
        paras.put("text", text);
        paras.put("mobile", mobile);
        try {
            String returnMsg = HttpUtil.postForm(MULTI_SEND_SMS, paras);
            logger.info(returnMsg);
            JSONArray jsonArray = new JSONArray(returnMsg);

            return JsonUtil.fromJson(jsonArray, ArrayList.class, SmsResponse.class);
        } catch (IOException e) {
            logger.error(e);
            return null;
        }
    }

}
