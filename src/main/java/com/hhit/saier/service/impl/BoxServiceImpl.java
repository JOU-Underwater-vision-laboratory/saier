package com.hhit.saier.service.impl;

import com.hhit.saier.dao.BoxDao;
import com.hhit.saier.domain.BoxDO;
import com.hhit.saier.service.BoxService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author ray
 */
@Service
public class BoxServiceImpl implements BoxService {

	@Resource
	private BoxDao boxDao;
	
	@Override
	public List<BoxDO> get(Long id){
		return boxDao.get(id);
	}

	@Override
	public List<BoxDO> list(Map<String, Object> map){
		return boxDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return boxDao.count(map);
	}
	
	@Override
	public int save(BoxDO boxDO){
		return boxDao.save(boxDO);
	}
	
	@Override
	public int update(BoxDO boxDO){
		return boxDao.update(boxDO);
	}
	@Override
	public int updateM(BoxDO boxDO){
		return boxDao.updateM(boxDO);
	}
	
	@Override
	public int remove(Long id){
		return boxDao.remove(id);
	}

	@Override
	public int removeM(Integer boxId, String mboxid) {
		return boxDao.removeM(boxId,mboxid);
	}

	@Override
	public int batchRemove(Long[] ids){
		return boxDao.batchRemove(ids);
	}

	@Override
	public List<BoxDO> findByLicense(String license) {
		return boxDao.findByLicense(license);
	}

	@Override
	public List<BoxDO> findByGB1(Integer gridid, Integer bid) {
		return boxDao.findByGB1(gridid, bid);
	}


	@Override
	public BoxDO findByGB2(Integer gridid, Integer bid, String mid) {
		return boxDao.findByGB2(gridid, bid,mid);
	}

}
