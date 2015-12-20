/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangbin
 * @version v0.1 2015/9/26 10:03
 */
public class PhoneUtil {

    private static final String APIKEY = "924b91cae300188f5d6883598f7bb9e3";

    private static Map<String, String> paras = new HashMap<String, String>();


    static {
        paras.put("apikey" ,APIKEY);
    }
    /**
     * 判断是否是合法的手机号码
     * @param mobile
     * @return
     */
    public static boolean isValidMobile(String mobile){
        /**
         * 手机号码
         * 移动：134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
         * 联通：130,131,132,152,155,156,185,186
         * 电信：133,1349,153,180,189
         */
        String MOBILE = "^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$";
        /**
         * 中国移动：China Mobile
         * 134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
         */
        @SuppressWarnings("unused")
        String CM = "^1(34[0-8]|(3[5-9]|5[017-9]|8[278])\\d)\\d{7}$";
        /**
         * 中国联通：China Unicom
         * 130,131,132,152,155,156,185,186
         */
        @SuppressWarnings("unused")
        String CU = "^1(3[0-2]|5[256]|8[56])\\d{8}$";
        /**
         * 中国电信：China Telecom
         * 133,1349,153,180,189
         */
        @SuppressWarnings("unused")
        String CT = "^1((33|53|8[09])[0-9]|349)\\d{7}$";
        /**
         * 大陆地区固话及小灵通
         * 区号：010,020,021,022,023,024,025,027,028,029
         * 号码：七位或八位
         */
        @SuppressWarnings("unused")
        String PHS = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$";

        Pattern pattern = Pattern.compile(MOBILE);
        Matcher matcher = pattern.matcher(mobile);

        return matcher.matches();
    }

}
