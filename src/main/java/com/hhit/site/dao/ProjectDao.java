package com.hhit.site.dao;

import com.hhit.site.domain.ProjectDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文章内容
 * @author 项目
 */
@Mapper
public interface ProjectDao {

	ProjectDO get(Long cid);
	
	List<ProjectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProjectDO content);
	
	int update(ProjectDO content);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

    List<ProjectDO> findAll(Map<String, Object> map);
}
