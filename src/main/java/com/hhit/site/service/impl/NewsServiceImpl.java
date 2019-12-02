package com.hhit.site.service.impl;

import com.hhit.site.dao.NewsDao;
import com.hhit.site.domain.NewsDO;
import com.hhit.site.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsMapper;
	
	@Override
	public NewsDO get(Long cid){
		return newsMapper.get(cid);
	}
	
	@Override
	public List<NewsDO> list(Map<String, Object> map){
		return newsMapper.list(map);
	}

	@Override
	public List<NewsDO> findAll(Map<String, Object> map) {
		return newsMapper.findAll(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return newsMapper.count(map);
	}
	
	@Override
	public int save(NewsDO newDO){
		return newsMapper.save(newDO);
	}
	
	@Override
	public int update(NewsDO newDO){
		return newsMapper.update(newDO);
	}
	
	@Override
	public int remove(Long cid){
		return newsMapper.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return newsMapper.batchRemove(cids);
	}
	
}
