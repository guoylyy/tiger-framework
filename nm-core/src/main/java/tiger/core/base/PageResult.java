/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tiger.common.util.Paginator;
import tiger.core.enums.ErrorCodeEnum;

/**
 * 分页返回的结果类型
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 12, 2015 6:36:19 PM yiliang.gyl Exp $
 */
public class PageResult<T> extends BaseResult<T> {

    /**  */
    private static final long serialVersionUID = 8338499249946679769L;

    private Paginator paginator;

    /**
     *
     */
    public PageResult() {
        super();
    }
    // ~ getter & setter -----------------------------------------------

    /**
     *
     */
    public PageResult(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    /**
     *
     */
    public PageResult(String code, String message) {
        super(code, message);
    }

    /**
     *
     * @param errorCodeEnum
     * @param paginator
     */
    public PageResult(ErrorCodeEnum errorCodeEnum, Paginator paginator) {
        super(errorCodeEnum);
        this.paginator = paginator;
    }

    /**
     *
     * @param errorCodeEnum
     * @param data
     * @param paginator
     */
    public PageResult(ErrorCodeEnum errorCodeEnum, T data, Paginator paginator) {
        super(errorCodeEnum, data);
        this.paginator = paginator;
    }

    /**
     *
     * @return
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     *
     * @param paginator
     */
    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
