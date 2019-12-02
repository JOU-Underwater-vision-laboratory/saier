package com.hhit.site.dao;

import com.hhit.site.domain.PublicationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文章
 * @author liujun
 */
@Mapper
public interface PublicationDao {

	PublicationDO get(Long cid);
	
	List<PublicationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PublicationDO content);
	
	int update(PublicationDO content);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);

	List<PublicationDO> findAll(Map<String, Object> map);
}
