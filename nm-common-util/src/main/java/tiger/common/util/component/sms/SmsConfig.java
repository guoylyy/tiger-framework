/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.component.sms;

/**
 *
 * @author zhangbin
 * @version v0.1 2015/9/26 11:02
 */
public class SmsConfig {
//【小锅科技】您的验证码是%s，请勿透漏给别人（十分钟内有效）
    private   String defaultMsgTextFmt = "【小锅科技】 您的验证码是%s,请勿透漏给别人（十分钟有效）";
    private   String url ="http://yunpian.com/v1/sms/send.json";
    private   String apiKey ="924b91cae300188f5d6883598f7bb9e3";
    
    public SmsConfig(){
        
    }
    
    public SmsConfig(String url, String apiKey, String defaultMsgTextFmt) {
        this.url = url;
        this.apiKey = apiKey;
        this.defaultMsgTextFmt = defaultMsgTextFmt;
    }

    public String getDefaultMsgTextFmt() {
        return defaultMsgTextFmt;
    }

    public void setDefaultMsgTextFmt(String defaultMsgTextFmt) {
        this.defaultMsgTextFmt = defaultMsgTextFmt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
