package com.hhit.downloadRes.dao;

import com.hhit.downloadRes.domain.SoftPicDO;
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
public interface SoftPicDao {

    SoftPicDO find(Long soft_fid, String soft_pic01);

    SoftPicDO findByFid(Long fid);

    SoftPicDO get(Long soft_fid);

    List<SoftPicDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SoftPicDO softPicDO);

    int update(SoftPicDO softPicDO);

    int remove(Long soft_fids);

}
