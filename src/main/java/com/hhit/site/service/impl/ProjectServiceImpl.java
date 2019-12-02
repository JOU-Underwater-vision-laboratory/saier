package com.hhit.site.service.impl;

import com.hhit.site.dao.ProjectDao;
import com.hhit.site.domain.ProjectDO;
import com.hhit.site.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectMapper;
	
	@Override
	public ProjectDO get(Long cid){
		return projectMapper.get(cid);
	}
	
	@Override
	public List<ProjectDO> list(Map<String, Object> map){
		return projectMapper.list(map);
	}


	@Override
	public List<ProjectDO> findAll(Map<String, Object> map) {
		return projectMapper.findAll(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return projectMapper.count(map);
	}
	
	@Override
	public int save(ProjectDO project){
		return projectMapper.save(project);
	}
	
	@Override
	public int update(ProjectDO project){
		return projectMapper.update(project);
	}
	
	@Override
	public int remove(Long cid){
		return projectMapper.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return projectMapper.batchRemove(cids);
	}
	
}
