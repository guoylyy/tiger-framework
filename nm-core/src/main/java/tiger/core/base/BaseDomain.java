package tiger.core.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 基本领域模型
 *
 * @author HuPeng
 * @version v 0.1 2015年10月19日 下午4:04:51 HuPeng Exp $
 */
public class BaseDomain implements Serializable {

    /**  */
    private static final long serialVersionUID = -826213001422949328L;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
