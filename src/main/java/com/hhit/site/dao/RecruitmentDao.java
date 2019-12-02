package com.hhit.site.dao;

import com.hhit.site.domain.RecruitmentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 招聘
 * @author liujun
 */
@Mapper
public interface RecruitmentDao {

	RecruitmentDO get(Long rid);
	
	List<RecruitmentDO> list(Map<String, Object> map);
	
	Integer count(Map<String, Object> map);

	Integer save(RecruitmentDO recruitment);

	Integer update(RecruitmentDO recruitment);

	Integer remove(Long rid);

	Integer batchRemove(Long[] rids);

	List<RecruitmentDO> findAll(Map<String, Object> map);
}
