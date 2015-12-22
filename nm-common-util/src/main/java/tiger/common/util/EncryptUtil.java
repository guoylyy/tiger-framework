/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 12:51 AM yiliang.gyl Exp $
 */
public class EncryptUtil {

    /**
     * MD5 加密算法
     *
     * @param plainText
     * @return
     */
    public static String MD5(String plainText) {
        plainText = plainText.trim();
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 明文密码加密规则
     *
     * @param basePassword  明文密码
     * @param mobile 用户手机号
     * @param sbin  验证码
     * @return
     */
    public static String encryptAuthPassword(String basePassword, String mobile, String sbin){
        String var1 = MD5(basePassword);
        String var2 = MD5(var1 + mobile);
        String var3 = MD5(var2 + sbin);
        return var3;
    }

}
