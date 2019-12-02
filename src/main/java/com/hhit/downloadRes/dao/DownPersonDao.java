package com.hhit.downloadRes.dao;

import com.hhit.downloadRes.domain.DownPerson;
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
public interface DownPersonDao {

    DownPerson find(String person_name);

    DownPerson get(int person_id);

    List<DownPerson> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DownPerson downPerson);

    int update(DownPerson downPerson);

    int remove(Integer ids);

}
