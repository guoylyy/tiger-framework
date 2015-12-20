package tiger.common.data.persistence;

import org.apache.ibatis.annotations.Param;
import tiger.common.dal.dataobject.AttachDO;
import tiger.common.dal.dataobject.example.AttachExample;

import java.util.List;

public interface AttachMapper {
	int countByExample(AttachExample example);

	int deleteByExample(AttachExample example);

	List<AttachDO> selectByExample(AttachExample example);

	int updateByExampleSelective(@Param("record") AttachDO record, @Param("example") AttachExample example);

	int updateByExample(@Param("record") AttachDO record, @Param("example") AttachExample example);

	int insert(AttachDO record);

	int insertSelective(AttachDO record);

	AttachDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AttachDO record);

	int updateByPrimaryKey(AttachDO record);

	List<AttachDO> listAttachesByIds(@Param("items") List<Long> ids);

	/**
	 * 检查attachUrl是否已存在
	 * 如返回Null，则代表false
	 * 返回非空List，则代表true
	 *
	 * @param attachUrl the attach url
	 * @return the long
	 */
	int countByUrl(@Param("attachUrl") String attachUrl);

	/**
	 * 根据附件id删除附件，假删除
	 * @param id
	 * @return
	 */
	int deleteAttachById(Long id);

	int countExistedIds(@Param("ids") List<Long> attachIds, @Param("accountId") Long accountId, @Param("attachType") String attachType);
}
