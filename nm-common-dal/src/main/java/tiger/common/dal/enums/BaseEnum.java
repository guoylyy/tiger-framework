/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import tiger.common.dal.enums.serilaizer.EnumSerializer;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:31 PM yiliang.gyl Exp $
 */
@JsonSerialize(using = EnumSerializer.class)
public interface BaseEnum {

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    String getCode();

    /**
     * Setter method for property <tt>code</tt>.
     *
     * @param code value to be assigned to property code
     */
    void setCode(String code);

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    String getValue();

    /**
     * Setter method for property <tt>value</tt>.
     *
     * @param value value to be assigned to property value
     */
    void setValue(String value);

}
