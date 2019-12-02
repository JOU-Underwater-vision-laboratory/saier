package com.hhit.saier.service.impl;

import com.hhit.saier.dao.MedicinePlanDao;
import com.hhit.saier.domain.MedicinePlanDO;
import com.hhit.saier.service.MedicinePlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author ray
 */
@Service
public class MedicinePanServiceImpl implements MedicinePlanService {

	@Resource
	private MedicinePlanDao medicinePlanDao;
	
	@Override
	public MedicinePlanDO get(Long id){
		return medicinePlanDao.get(id);
	}

	@Override
	public List<MedicinePlanDO> list(Map<String, Object> map){
		return medicinePlanDao.list(map);
	}

	@Override
	public List<String> listName(Map<String, Object> map){
		return medicinePlanDao.listName(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return medicinePlanDao.count(map);
	}
	
	@Override
	public int save(MedicinePlanDO notifyRecord){
		return medicinePlanDao.save(notifyRecord);
	}
	
	@Override
	public int update(MedicinePlanDO notifyRecord){
		return medicinePlanDao.update(notifyRecord);
	}

	@Override
	public int remove(Long id) {
		return medicinePlanDao.remove(id);
	}

	@Override
	public int remove(String medlicense, Integer boxid){
		return medicinePlanDao.removeM(  medlicense, boxid);
	}

	@Override
	public int batchRemove(Long[] ids){
		return medicinePlanDao.batchRemove(ids);
	}

}
