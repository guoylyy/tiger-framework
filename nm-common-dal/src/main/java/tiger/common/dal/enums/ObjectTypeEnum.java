/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

/**
 * @author mi.li
 * @version v 0.1 15/10/20 下午8:44 mi.li Exp $
 */
public enum ObjectTypeEnum implements BaseEnum{
    PACKAGE("PACKAGE","套餐"),
    ADDON("ADDON","增值包");

    private String code;
    private String value;

    ObjectTypeEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

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

    public static ObjectTypeEnum getEnumByCode(String code){
        for(ObjectTypeEnum ot : ObjectTypeEnum.values()){
            if(ot.getCode().equals(code)){
                return ot;
            }
        }
        return null;
    }
}
