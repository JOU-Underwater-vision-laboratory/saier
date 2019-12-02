package com.hhit.saier.service.impl;

import com.hhit.saier.dao.MedicinePlanRecordDao;
import com.hhit.saier.domain.MedicinePlanRecordDO;
import com.hhit.saier.domain.TimeProp;
import com.hhit.saier.service.MedicinePlanRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ray
 */
@Service
public class MedicinePanRecordServiceImpl implements MedicinePlanRecordService {

	@Resource
	private MedicinePlanRecordDao medicinePlanRecordDao;
	
	@Override
	public MedicinePlanRecordDO get(Long id){
		return medicinePlanRecordDao.get(id);
	}

	@Override
	public List<MedicinePlanRecordDO> list(Map<String, Object> map){
		return medicinePlanRecordDao.list(map);
	}

	@Override
	public List<String> listName(Map<String, Object> map){
		return medicinePlanRecordDao.listName(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return medicinePlanRecordDao.count(map);
	}
	@Override
	public List<HashMap> countYear(Map<String, Object> map){
		return medicinePlanRecordDao.countYear(map);
	}
	@Override
	public List<HashMap> countMonth(Map<String, Object> map){
		return medicinePlanRecordDao.countMonth(map);
	}
	@Override
	public List<HashMap> countDay(Map<String, Object> map){
		return medicinePlanRecordDao.countDay(map);
	}
	@Override
	public int save(MedicinePlanRecordDO notifyRecord){
		return medicinePlanRecordDao.save(notifyRecord);
	}
	
	@Override
	public int update(MedicinePlanRecordDO notifyRecord){
		return medicinePlanRecordDao.update(notifyRecord);
	}

	@Override
	public int remove(Long id) {
		return medicinePlanRecordDao.remove(id);
	}

	@Override
	public int remove(String medlicense, Integer boxid){
		return medicinePlanRecordDao.removeM(  medlicense, boxid);
	}

	@Override
	public int batchRemove(Long[] ids){
		return medicinePlanRecordDao.batchRemove(ids);
	}

}
