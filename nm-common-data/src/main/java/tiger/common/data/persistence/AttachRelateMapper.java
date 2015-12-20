package tiger.common.data.persistence;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.data.dataobject.AttachRelateDO;
import tiger.common.data.dataobject.example.AttachRelateExample;

import java.util.List;

public interface AttachRelateMapper {
    int countByExample(AttachRelateExample example);

    int deleteByExample(AttachRelateExample example);

    List<AttachRelateDO> selectByExample(AttachRelateExample example);

    int updateByExampleSelective(@Param("record") AttachRelateDO record, @Param("example") AttachRelateExample example);

    int updateByExample(@Param("record") AttachRelateDO record, @Param("example") AttachRelateExample example);

    List<AttachRelateDO> selectByExampleAndPage(AttachRelateExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(AttachRelateDO record);

    int insertSelective(AttachRelateDO record);

    int batchInsert(@Param("list") List<AttachRelateDO> list);

    AttachRelateDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AttachRelateDO record);

    int updateByPrimaryKey(AttachRelateDO record);
}
