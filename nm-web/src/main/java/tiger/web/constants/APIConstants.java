/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 存放 API 相关的公共参数
 * @author yiliang.gyl
 * @version v 0.1 Sep 13, 2015 7:11:16 AM yiliang.gyl Exp $
 */
public class APIConstants {
    /**
     * The Constant HEADER_TOKEN.
     */
    public static final String HEADER_TOKEN = "X-Auth-Token";

    /**
     * The Constant HEADER_USERNAME.
     */
    public static final String HEADER_USERNAME = "X-Username";

    /**
     * The Constant HEADER_PASSWORD.
     */
    public static final String HEADER_PASSWORD = "X-Password";

    /**
     * The Constant HEADER_CAPTCHA_TOKEN.
     */
    public static final String HEADER_CAPTCHA_TOKEN = "Captcha-Token";

    /**
     * The Constant HEADER_CAPTCHA.
     */
    public static final String HEADER_CAPTCHA = "Captcha";

    /** User-Agent */
    public static final String HEADER_USER_AGENT = "User-Agent";

    /**
     * 系统枚举类包路径
     */
    public static final String BASE_ENUM_PACKAGE = "tiger.common.dal.enums";

    /** token默认过期天数 */
    public static final String TOKEN_DEFAULT_EXPIRE_DAY = "1";

    /** The constant PARAM_EXPIRE_DAY */
    public static final String PARAM_EXPIRE_DAY = "expireDay";

    /** The constant PARAM_MOBILE */
    public static final String PARAM_MOBILE = "mobile";

    /** The constant PARAM_TOKEN */
    public static final String PARAM_TOKEN = "token";

    /** The constant PARAM_OPERATION */
    public static final String PARAM_OPERATION = "operation";

    private static final ArrayList<String> public_system_param_types = new ArrayList<>(Arrays.asList("LOAN_CONFIG", "SYSTEM_CONFIG"));

    public static final List<String> PUBLIC_SYSTEM_PARAM_TYPES = Collections.unmodifiableList(public_system_param_types);

}
