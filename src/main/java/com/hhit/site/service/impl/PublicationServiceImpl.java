package com.hhit.site.service.impl;

import com.hhit.site.dao.PublicationDao;
import com.hhit.site.domain.PublicationDO;
import com.hhit.site.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PublicationServiceImpl implements PublicationService {
	@Autowired
	private PublicationDao publicationMapper;
	
	@Override
	public PublicationDO get(Long cid){
		return publicationMapper.get(cid);
	}
	
	@Override
	public List<PublicationDO> list(Map<String, Object> map){
		return publicationMapper.list(map);
	}

	@Override
	public List<PublicationDO> findAll(Map<String, Object> map) {
		return publicationMapper.findAll(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return publicationMapper.count(map);
	}
	
	@Override
	public int save(PublicationDO publication){
		return publicationMapper.save(publication);
	}
	
	@Override
	public int update(PublicationDO publication){
		return publicationMapper.update(publication);
	}
	
	@Override
	public int remove(Long cid){
		return publicationMapper.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return publicationMapper.batchRemove(cids);
	}
	
}
