package com.hhit.site.dao;

import com.hhit.site.domain.MemberDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 成员
 * @author liujun
 */
@Mapper
public interface MemberDao {

	MemberDO get(Long mid);
	
	List<MemberDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberDO memberDO);
	
	int update(MemberDO memberDO);
	
	int remove(Long mid);
	
	int batchRemove(Long[] mids);

	List<MemberDO> findAll(Map<String, Object> map);
}
