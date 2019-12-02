package com.hhit.system.service;

import com.hhit.oa.domain.NotifyRecordDO;
import com.hhit.system.domain.ReplyRecordDO;

import java.util.List;
import java.util.Map;

/**
 * 周报通知发送记录
 * 
 * @author leoterrior
 */
public interface ReplyRecordService {
	
	ReplyRecordDO get(Long id);
	
	List<ReplyRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReplyRecordDO notifyRecord);
	
	int update(ReplyRecordDO notifyRecord);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 更改阅读状态
	 * @return
	 */
	int changeRead(ReplyRecordDO notifyRecord);
}
