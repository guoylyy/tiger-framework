package tiger.core.domain;

import tiger.common.data.enums.RoleEnum;
import tiger.core.base.BaseDomain;

/**
 * 角色
 *
 * @author Hupeng
 * @version v 0.1 2015年10月9日 下午10:04:01 Hupeng Exp $
 */
public class RoleDomain extends BaseDomain {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6276954480367731360L;

    /** The name. */
    private RoleEnum          name;

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public RoleEnum getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(RoleEnum name) {
        this.name = name;
    }

}
