package com.hhit.site.service.impl;

import com.hhit.common.utils.Query;
import com.hhit.site.dao.RecruitmentDao;
import com.hhit.site.domain.RecruitmentDO;
import com.hhit.site.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class RecruitmentServiceImpl implements RecruitmentService {

	@Autowired
	private RecruitmentDao recruitmentMapper;

    @Override
	public RecruitmentDO get(Long rid){
		return recruitmentMapper.get(rid);
	}
	
	@Override
	public List<RecruitmentDO> list(Map<String, Object> map){
		return recruitmentMapper.list(map);
	}

	@Override
	public List<RecruitmentDO> findAll(Map<String, Object> map) {
		return recruitmentMapper.findAll(map);
	}

	@Override
	public Integer count(Map<String, Object> map){
		return recruitmentMapper.count(map);
	}
	
	@Override
	public Integer save(RecruitmentDO recruitmentDO){
		return recruitmentMapper.save(recruitmentDO);
	}
	
	@Override
	public Integer update(RecruitmentDO recruitmentDO){
		return recruitmentMapper.update(recruitmentDO);
	}
	
	@Override
	public Integer remove(Long rid){
		return recruitmentMapper.remove(rid);
	}
	
	@Override
	public Integer batchRemove(Long[] rids){
		return recruitmentMapper.batchRemove(rids);
	}
	
}
