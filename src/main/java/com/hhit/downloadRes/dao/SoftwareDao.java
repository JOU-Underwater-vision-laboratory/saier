package com.hhit.downloadRes.dao;

import com.hhit.downloadRes.domain.SoftwareDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: DownPersonDao
 * @description:
 * @create 2018-09-01
 */
@Mapper
public interface SoftwareDao {

    SoftwareDO find( String soft_name, String soft_version);

    SoftwareDO findByFid(Long fid);

    SoftwareDO get(Integer soft_id);

    List<SoftwareDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SoftwareDO softwareDO);

    int update(SoftwareDO softwareDO);

    int remove(Integer soft_ids);

    int removeByFid(Long id);
}
