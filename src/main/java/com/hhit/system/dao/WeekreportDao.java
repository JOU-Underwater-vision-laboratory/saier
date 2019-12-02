package com.hhit.system.dao;

import com.hhit.system.domain.WeekreportDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 周报
 * @author liujun
 */
@Mapper
public interface WeekreportDao {

	WeekreportDO get(Long wid);
	
	List<WeekreportDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WeekreportDO weekreport);
	
	int update(WeekreportDO weekreport);
	
	int remove(Long wid);
	
	int batchRemove(Long[] wids);

	List<WeekreportDO> findAll(Map<String, Object> map);
}
