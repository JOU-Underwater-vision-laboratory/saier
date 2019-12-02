package com.hhit.site.dao;

import com.hhit.site.domain.NewsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 动态
 * @author liujun
 */
@Mapper
public interface NewsDao {

	NewsDO get(Long cid);
	
	List<NewsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NewsDO content);
	
	int update(NewsDO content);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

	List<NewsDO> findAll(Map<String, Object> map);
}
