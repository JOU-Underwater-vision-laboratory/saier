package com.hhit.saier.service.impl;

import com.hhit.saier.dao.MedBoxMiniDao;
import com.hhit.saier.domain.MedBoxMiniDO;
import com.hhit.saier.service.MedBoxMiniService;
import com.hhit.saier.service.MedBoxMiniService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author ray
 */
@Service
public class MedBoxMiniServiceImpl implements MedBoxMiniService {

	@Resource
	private MedBoxMiniDao medBoxMiniDao;
	
	@Override
	public MedBoxMiniDO get(Long id){
		return medBoxMiniDao.get(id);
	}

	@Override
	public List<MedBoxMiniDO> list(Map<String, Object> map){
		return medBoxMiniDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return medBoxMiniDao.count(map);
	}
	
	@Override
	public int save(MedBoxMiniDO notifyRecord){
		return medBoxMiniDao.save(notifyRecord);
	}
	
	@Override
	public int update(MedBoxMiniDO notifyRecord){
		return medBoxMiniDao.update(notifyRecord);
	}
	
	@Override
	public int remove(Long id){
		return medBoxMiniDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return medBoxMiniDao.batchRemove(ids);
	}

	@Override
	public String findByGrid(String grid) {
		return medBoxMiniDao.findByGrid(grid);
	}

}
