package tiger.common.dal.persistence;

import org.apache.ibatis.annotations.Param;
import tiger.common.dal.dataobject.PermissionDO;

import java.util.List;

/**
 * Created by Hupeng on 2015/10/9.
 */
public interface PermissionMapper {

    /**
     * 根据角色id获取权限
     * @param roleId
     * @return
     */
    List<PermissionDO> findByRoleId(long roleId);

    /**
     * 根据角色ids获取权限列表
     */
    List<PermissionDO> selectByRoleIds(@Param("ids") List<Long> roleIds);

}
