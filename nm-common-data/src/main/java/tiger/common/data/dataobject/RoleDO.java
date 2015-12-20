package tiger.common.data.dataobject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 *
 *
 * Created by HuPeng on 2015/9/1.
 */
public class RoleDO extends BaseDO {


    /**  */
    private static final long serialVersionUID = 3030454187234958384L;
    /** The name. */
    private String          name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
