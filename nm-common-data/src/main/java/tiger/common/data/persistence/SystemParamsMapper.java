package tiger.common.data.persistence;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.data.dataobject.SystemParamsDO;
import tiger.common.data.dataobject.example.SystemParamsExample;

import java.util.List;

public interface SystemParamsMapper {
    int countByExample(SystemParamsExample example);

    int deleteByExample(SystemParamsExample example);

    List<SystemParamsDO> selectByExample(SystemParamsExample example);

    int updateByExampleSelective(@Param("record") SystemParamsDO record, @Param("example") SystemParamsExample example);

    int updateByExample(@Param("record") SystemParamsDO record, @Param("example") SystemParamsExample example);

    List<SystemParamsDO> selectByExampleAndPage(SystemParamsExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(SystemParamsDO record);

    int insertSelective(SystemParamsDO record);

    SystemParamsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemParamsDO record);

    int updateByPrimaryKey(SystemParamsDO record);
}
