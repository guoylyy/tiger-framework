package tiger.common.dal.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.dal.dataobject.SmsDO;
import tiger.common.dal.dataobject.example.SmsExample;

public interface SmsMapper {
    int countByExample(SmsExample example);

    int deleteByExample(SmsExample example);

    List<SmsDO> selectByExample(SmsExample example);

    int updateByExampleSelective(@Param("record") SmsDO record, @Param("example") SmsExample example);

    int updateByExample(@Param("record") SmsDO record, @Param("example") SmsExample example);

    List<SmsDO> selectByExampleAndPage(SmsExample example, RowBounds rowBound);

    int deleteByPrimaryKey(Long id);

    int insert(SmsDO record);

    int insertSelective(SmsDO record);

    SmsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsDO record);

    int updateByPrimaryKey(SmsDO record);
}
