package com.hhit.system.service.impl;

import com.hhit.oa.dao.NotifyRecordDao;
import com.hhit.oa.domain.NotifyRecordDO;
import com.hhit.oa.service.NotifyRecordService;
import com.hhit.system.dao.ReplyRecordDao;
import com.hhit.system.domain.ReplyRecordDO;
import com.hhit.system.service.ReplyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ReplyRecordServiceImpl implements ReplyRecordService {
	@Autowired
	private ReplyRecordDao replyRecordDao;
	
	@Override
	public ReplyRecordDO get(Long id){
		return replyRecordDao.get(id);
	}
	
	@Override
	public List<ReplyRecordDO> list(Map<String, Object> map){
		return replyRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return replyRecordDao.count(map);
	}
	
	@Override
	public int save(ReplyRecordDO notifyRecord){
		return replyRecordDao.save(notifyRecord);
	}
	
	@Override
	public int update(ReplyRecordDO notifyRecord){
		return replyRecordDao.update(notifyRecord);
	}
	
	@Override
	public int remove(Long id){
		return replyRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return replyRecordDao.batchRemove(ids);
	}

	@Override
	public int changeRead(ReplyRecordDO notifyRecord) {
		return replyRecordDao.changeRead(notifyRecord);
	}

}
