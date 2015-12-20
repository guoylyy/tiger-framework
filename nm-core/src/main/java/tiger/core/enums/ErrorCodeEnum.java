/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.enums;

import java.text.MessageFormat;

/**
 * 错误码枚举类
 * 1. 错误码获取方式：ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getCode();
 * 2. 默认错误信息获取方式：ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getDefaultMessage(); >> 参数值个数超过最大限制
 * 3. 自定义的错误信息获取方式：ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getMessage("mobile_no","50"); >>mobile_no参数值最多只支持50个
 * <b>推荐使用自定义的错误信息方式来生成详细的错误提示信息。</b>
 *
 * @author yiliang.gyl
 * @version $Id: ErrorCodeEnum.java, v 0.1 Jun 21, 2015 10:54:14 AM yiliang.gyl Exp $
 */
public enum ErrorCodeEnum {
    /** 参数类异常 */
    PARAMETERS_IS_NULL("100", "输入参数不能为空", "输入参数({0})不能为空"),
    ILLEGAL_PARAMETER_NAME("101", "非法无效的参数名", "参数名({0})是非法和无效的"),
    BLANK_PARAMETER_VALUE("102", "参数值不能为null或空字符串", "参数({0})值不能为null或空字符串"),
    ILLEGAL_PARAMETER_VALUE("103", "不合法的参数值", "参数({0})值不是合法的{1}"),
    VALUES_OVER_MAX_LIMITED("104", "参数值个数超过最大限制", "参数({0})最多只支持传入{1}个"),
    LENGTH_OVER_MAX_LIMITED("105", "参数值长度超过限制", "参数({0})值最长只允许{1}个字符"),
    LENGTH_NOT_EQUALS("106", "参数值长度不符合预期", "参数({0})值应等于{1}位"),
    
    ILLEGAL_PARAMETER("110","参数错误",""),
    ILLEGAL_PHONENUM("111","手机号码已被注册",""),
    ILLEGAL_IDCARD("112","身份证号已存在",""),

    /** 业务对象类异常 */
    SCENE_NOT_EXISTS("400", "不存在的服务场景编码", "服务场景({0})不存在"),
    SCENE_VERSION_NOT_EXISTS("401", "不存在的服务场景版本号", "服务场景({0})不存在{1}版本"),    
    NOT_FOUND("402","对象不存在","对象({0})不存在"),
    USER_NOT_FOUND("403","用户不存在","用户({0})不存在"),
    
    /** 权限类异常 */
    REQUEST_RESTRICTED("500", "请求受限，请联系我们解除限制", "请求受限"),
    UNAUTHORIZED("501", "您没有权限进行此操作", "没有权限"),
    
    /** 业务类异常 */
    BIZ_EXCEPTION("800", "业务处理异常", "业务处理异常。原因描述：{0}"),
    BIZ_FAIL("801","业务处理失败","业务处理失败。原因描述：{0}"),
    BIZ_EXPIRED("802","业务已过期","业务已过期。原因描述：{0}"),
    BIZ_REQUEST_EXCEEDED("803","业务请求次数过多","业务请求次数过多。原因描述：{0}"),
    BIZ_FAIL_EXCEEDED("804","业务错误次数过多","业务错误次数过多。原因描述：{0}"),
    BIZ_DATA_INSUFFICIENT("805","业务数据不足","业务数据不足。原因描述：{0}"),
    BIZ_DUPLICATIVE("806","业务数据已存在","业务数据已存在。原因描述：{0}"),
    BIZ_ICON_UPLOAD("807","头像上传失败","头像上传失败。原因描述：{0}"),
    BIZ_VERY_CODE("808","验证码错误","验证码错误。原因描述：{0}"),
    BIZ_VERY_CODE_TIMEOUT("809","验证码超时","验证码超时。原因描述：{0}"),
    BIZ_SEND_CODE("810","验证码发送失败","验证码发送失败。原因描述：{0}"),
    
    BIZ_UNSUPPORTED_KIND("810","不支持附件类型", "{0}"),
    BIZ_FILE_COUNT_EXCEED("811","超过最大附件数量", "{0}"),
    BIZ_FILE_SIZE_EXCEED("812","超过附件最大大小", "{0}"),
    BIZ_DATA_NOT_FOUND("813", "数据记录不存在", "{0}"),
    BIZ_PHASE_ERROR("814", "该阶段不能对项目操作", "{0}"),
    BIZ_UNSUPPORTED_ENUM("815", "不支持的常量", "{0}"),
    BIZ_STATUS_ERROR("816", "该状态不能对项目操作", "{0}"),

    /** 系统类异常 */
    SYSTEM_EXCEPTION("900", "系统处理异常", "系统处理异常。原因描述：{0}"),
    SYSTEM_TIMEOUT("901", "系统处理超时" ,"系统处理超时"),
    DB_EXCEPTION("902", "数据库处理异常", "数据库处理异常"),

    /** 未知异常 */
    UNKNOW_ERROR("-1", "未知系统异常","未知系统异常"),

    /** 没有错误 */
    NO_ERROR("200","操作成功","操作成功")
    ;

    private String code;
    private String defaultMessage;
    private String message;

    /**
     * 自定义错误信息
     * <br/>当未支持定义错误信息时，返回默认的message信息
     * <b>请尽量使用该方法来自定义的明确的错误提示信息</b>
     * <p/>
     * <p>
     * 使用方法：
     * ErrorCodeEnum.VALUES_OVER_MAX_LIMITED.getMessage("mobile_no","50");
     * >> mobile_no参数值最多只支持50个
     * </p>
     *
     * @param input 自定义描述
     * @return
     */
    public String getMessage(String... input) {
        Object[] obj = input;
        return getMessage(obj);
    }

    /**
     *
     * @param input
     * @return
     */
    private String getMessage(Object[] input) {
        if (input == null || input.length == 0 || getMessage() == null) {
            return getDefaultMessage();
        } else {
            return MessageFormat.format(getMessage(), input);
        }
    }

    /**
     * @param code
     * @param defaultMessage
     */
    private ErrorCodeEnum(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    /**
     * @param code
     * @param defaultMessage
     * @param message
     */
    private ErrorCodeEnum(String code, String defaultMessage, String message) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.message = message;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    private String getMessage() {
        return message;
    }

    /**
     * Getter method for property <tt>formatMessage</tt>.
     *
     * @return property value of formatMessage
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }

}