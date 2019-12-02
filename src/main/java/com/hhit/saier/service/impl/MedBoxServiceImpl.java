package com.hhit.saier.service.impl;

import com.hhit.saier.dao.MedBoxDao;
import com.hhit.saier.domain.MedBoxDO;
import com.hhit.saier.service.MedBoxService;
import com.hhit.saier.service.MedBoxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author ray
 */
@Service
public class MedBoxServiceImpl implements MedBoxService {

	@Resource
	private MedBoxDao medBoxDao;
	
	@Override
	public MedBoxDO get(Long id){
		return medBoxDao.get(id);
	}

	@Override
	public List<MedBoxDO> list(Map<String, Object> map){
		return medBoxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return medBoxDao.count(map);
	}
	
	@Override
	public int save(MedBoxDO notifyRecord){
		return medBoxDao.save(notifyRecord);
	}
	
	@Override
	public int update(MedBoxDO notifyRecord){
		return medBoxDao.update(notifyRecord);
	}
	
	@Override
	public int remove(Long id){
		return medBoxDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return medBoxDao.batchRemove(ids);
	}

}
