/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.data.dataobject;

/**
 * 权限数据实体类
 *
 * @author HuPeng
 * @version v 0.1 2015年10月9日 下午8:59:06 HuPeng Exp $
 */
public class PermissionDO extends BaseDO {

    /**  */
    private static final long serialVersionUID = -7921422951350267216L;

    /** The name. */
    private String            name;

    /** The operation. */
    private String            operation;

    /** The system. */
    private String            system;

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>operation</tt>.
     *
     * @return property value of operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Setter method for property <tt>operation</tt>.
     *
     * @param operation value to be assigned to property operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
