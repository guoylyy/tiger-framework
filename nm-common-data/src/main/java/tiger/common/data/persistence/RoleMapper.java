package tiger.common.data.persistence;

import org.apache.ibatis.annotations.Param;
import tiger.common.data.dataobject.RoleDO;

import java.util.List;

/**
 * Created by Hupeng on 2015/10/9.
 */
public interface RoleMapper {

    List<RoleDO> findByAccountId(@Param("accountId") long accountId);

}
