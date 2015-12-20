package tiger.core.domain;

import tiger.common.data.enums.SystemEnum;
import tiger.core.base.BaseDomain;

/**
 *
 * 权限模型
 * @author Hupeng
 * @version v 0.1 2015年10月9日 下午10:20:16 Hupeng Exp $
 */
public class PermissionDomain extends BaseDomain {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2415902938807869231L;

    /** The name. */
    private String            name;

    /** The operation. */
    private String            operation;

    /** The system. */
    private SystemEnum        system;

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

    /**
     * Getter method for property <tt>system</tt>.
     *
     * @return property value of system
     */
    public SystemEnum getSystem() {
        return system;
    }

    /**
     * Setter method for property <tt>system</tt>.
     *
     * @param system value to be assigned to property system
     */
    public void setSystem(SystemEnum system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return name + ":" + operation;
    }

    @Override
    public int hashCode() {
        return new Long(id).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof PermissionDomain) {
            PermissionDomain permissionDomain = (PermissionDomain) object;
            if (permissionDomain.getId()>0) {
                return this.getId().equals(permissionDomain.getId());
            }
        }
        return false;
    }

}
