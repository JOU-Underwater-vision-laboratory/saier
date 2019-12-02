package com.hhit.system.dao;

import com.hhit.oa.domain.NotifyRecordDO;
import com.hhit.system.domain.ReplyRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通知通告发送记录
 * 
 * @author liujun
 */
@Mapper
public interface ReplyRecordDao {

	ReplyRecordDO get(Long id);

	List<ReplyRecordDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ReplyRecordDO notifyRecord);

	int update(ReplyRecordDO notifyRecord);

	int remove(Long id);

	int batchRemove(Long[] ids);

	int batchSave(List<ReplyRecordDO> records);

	Long[] listNotifyIds(Map<String, Object> map);

	int removeByNotifbyId(Long notifyId);

	int batchRemoveByNotifbyId(Long[] notifyIds);

	int changeRead(ReplyRecordDO notifyRecord);
}
