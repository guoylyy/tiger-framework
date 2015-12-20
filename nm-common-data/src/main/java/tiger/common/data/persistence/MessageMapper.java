package tiger.common.data.persistence;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tiger.common.data.dataobject.MessageDO;
import tiger.common.data.dataobject.example.MessageExample;
import tiger.common.data.query.MessageQuery;

import java.util.List;

public interface MessageMapper {
	int countByExample(MessageExample example);

	int deleteByExample(MessageExample example);

	List<MessageDO> selectByExample(MessageExample example);

	int updateByExampleSelective(@Param("record") MessageDO record, @Param("example") MessageExample example);

	int updateByExample(@Param("record") MessageDO record, @Param("example") MessageExample example);

	List<MessageDO> selectByExampleAndPage(MessageExample example, RowBounds rowBound);

	int deleteByPrimaryKey(Long id);

	int insert(MessageDO record);

	int batchInsert(@Param("messageDOList") List<MessageDO> records);

	int insertSelective(MessageDO record);

	int batchInsertSelective(@Param("messageDOList") List<MessageDO> records);

	MessageDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MessageDO record);

	int updateByPrimaryKey(MessageDO record);

	int countMessages(@Param("query") MessageQuery query);

	List<MessageDO> queryMessages(@Param("query") MessageQuery query, @Param("offset") int offset, @Param("limit") int limit);
}
