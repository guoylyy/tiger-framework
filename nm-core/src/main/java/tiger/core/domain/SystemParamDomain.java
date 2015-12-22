/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain;

import tiger.common.dal.enums.SystemParamTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.base.BaseDomain;

/**
 * 系统参数类
 *
 * @author yiliang.gyl
 * @version v 0.1 Oct 3, 2015 4:12:27 PM yiliang.gyl Exp $
 */
public class SystemParamDomain extends BaseDomain {

    /**  */
    private static final long serialVersionUID = -1314658133654161521L;

    private String paramName;

    private String paramValue;

    @CopyIgnore
    private boolean isActive;

    @CopyIgnore
    private SystemParamTypeEnum paramType;

    /**
     * Getter method for property <tt>paramName</tt>.
     *
     * @return property value of paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Setter method for property <tt>paramName</tt>.
     *
     * @param paramName value to be assigned to property paramName
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * Getter method for property <tt>paramValue</tt>.
     *
     * @return property value of paramValue
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * Setter method for property <tt>paramValue</tt>.
     *
     * @param paramValue value to be assigned to property paramValue
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * Getter method for property <tt>isActive</tt>.
     *
     * @return property value of isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Setter method for property <tt>isActive</tt>.
     *
     * @param isActive value to be assigned to property isActive
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Getter method for property <tt>paramType</tt>.
     *
     * @return property value of paramType
     */
    public SystemParamTypeEnum getParamType() {
        return paramType;
    }

    /**
     * Setter method for property <tt>paramType</tt>.
     *
     * @param paramType value to be assigned to property paramType
     */
    public void setParamType(SystemParamTypeEnum paramType) {
        this.paramType = paramType;
    }




}
