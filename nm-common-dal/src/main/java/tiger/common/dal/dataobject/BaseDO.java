/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.dataobject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * 基础VO
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 10, 2015 9:40:17 PM yiliang.gyl Exp $
 */
public class BaseDO implements Serializable {
    /**  */
    private static final long serialVersionUID = -5286065210117390841L;

    Long id;

    Timestamp createTime;
    Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public static boolean contains(Collection<? extends BaseDO> collection, BaseDO baseVO) {
        boolean contains = false;
        for (BaseDO i : collection) {
            if (i.id.equals(baseVO.id)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
