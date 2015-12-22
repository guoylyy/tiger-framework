/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * 消息业务类型枚举
 *
 * @author alfred.yx
 * @version v 0.1 Sep 24, 2015 4:49:19 PM alfred Exp $
 */
public enum MessageBizTypeEnum implements BaseEnum{
    NONE("NONE", "无"),
    FILE("FILE", "文件"),
    POST("POST", "分享"),
    EVENT("EVENT", "日程安排"),
    TASK("TASK", "任务"),
    SYSTEM("SYSTEM", "系统消息"),
    LOAN_NOTIFICATION("LOAN_NOTIFICATION", "贷款提醒"),
    BORROW_NOTIFICATION("BORROW_NOTIFICATION", "融资提醒")
    ;

    /** The code. */
    private String code;

    /** The value. */
    private String value;

    /**
     * Instantiates a new message biz type enum.
     *
     * @param code the code
     * @param value the value
     */
    MessageBizTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过枚举<code>code</code>获取枚举
     *
     * @param code
     * @return
     */
    public static MessageBizTypeEnum getEnumByCode(String code) {
        for (MessageBizTypeEnum bizType : MessageBizTypeEnum.values()) {
            if (bizType.getCode().equals(code)) {
                return bizType;
            }
        }
        return null;
    }
}
