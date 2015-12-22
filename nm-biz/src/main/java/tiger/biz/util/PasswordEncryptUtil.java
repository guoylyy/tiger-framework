/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.util;

import tiger.common.util.EncryptUtil;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 1:18 AM yiliang.gyl Exp $
 */
public class PasswordEncryptUtil {

    public static final String SBIN = "1234"; //未来自动生成

    public static String getLoginPassword(String baseMD5Password, String mobile, String sbin) {
        String var2 = EncryptUtil.MD5(baseMD5Password + mobile);
        String var3 = EncryptUtil.MD5(var2 + sbin.toUpperCase());
        return var3;
    }
}
