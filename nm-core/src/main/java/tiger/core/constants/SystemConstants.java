/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.constants;

/**
 * 
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 5:23:04 PM yiliang.gyl Exp $
 */
public class SystemConstants {
    /** 字符串 default */
    public static final String DEFAULT = "default";

    /** 字符串 true */
    public static final String TRUE = "true";

    /** 是否限制注册时候要有邀请码*/
    public static final String INVITATION_CONSTANTS = "invitation_constant";

    /** 数组第一个位置 */
    public static final int FIRST_INDEX = 0;

    /** 平台最后一个位置 */
    public static final int PLAT_LAST_INDEX = 255;

    /** 大小为1 */
    public static final int SIZE_ONE = 1;

    /** 大小为0 */
    public static final int SIZE_ZERO = 0;

    /** 邀请码默认长度 */
    public static final int INVITATION_CODE_LENGTH = 8;

    /** 系统消息title */
    public static final String SYSTEM_MESSAGE_TITLE = "系统消息";

    /** 系统默认管理员账号 */
    public static final long SYSTEM_ACCOUNT = 0L;

    /** 用户order by clause, 创建时间逆序排序*/
    public static final String CREATE_TIME_DESC = "create_time desc";

    /** 默认坏账天数 */
    public static final String DEFAULT_BAD_LOAN_DAY = "90";
    /** Account extParam中坏账字段  */
    public static final String BAD_LOAN_DAY = "badLoanDay";

    /** 默认逾期天数 */
    public static final String DEFAULT_OVER_DUE_DAY = "30";
    /**  Account extParam中逾期字段  */
    public static final String OVER_DUE_DAY = "overDueDay";

}
