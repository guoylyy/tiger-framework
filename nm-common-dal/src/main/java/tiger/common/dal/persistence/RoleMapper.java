package tiger.common.dal.persistence;

import org.apache.ibatis.annotations.Param;
import tiger.common.dal.dataobject.RoleDO;

import java.util.List;

/**
 * Created by Hupeng on 2015/10/9.
 */
public interface RoleMapper {

    List<RoleDO> findByAccountId(@Param("accountId") long accountId);

}
