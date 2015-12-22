package tiger.common.dal.query;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 通用查询
 *
 * @author HuPeng
 * @version v 0.1 2015年10月20日 下午8:15:12 HuPeng Exp $
 */
public class BaseQuery implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID  = -5618497453602898337L;

    /** The Constant DEFAULT_PAGE_SIZE. */
    private final static int  DEFAULT_PAGE_SIZE = 10;

    /** The Constant FIRST_PAGE. */
    private final static int  FIRST_PAGE        = 1;

    /** The page size. */
    protected int             pageSize          = DEFAULT_PAGE_SIZE;

    /** The page num. */
    protected int             pageNum           = FIRST_PAGE;

    /**
     * Gets the page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize
     *            the new page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets the page num.
     *
     * @return the page num
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Sets the page num.
     *
     * @param pageNum
     *            the new page num
     */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
