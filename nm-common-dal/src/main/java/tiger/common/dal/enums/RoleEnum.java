/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * Created by HuPeng on 2015/9/1.
 */
public enum RoleEnum implements BaseEnum{
    CUSTOMER("CUSTOMER","普通用户"), ADMIN("ADMIN","管理员");

    RoleEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * 通过枚举<code>code</code>获取枚举
     *
     * @param code
     * @return
     */
    public static RoleEnum getEnumByCode(String code) {
        for (RoleEnum bt : RoleEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
            }
        }
        return null;
    }
}
