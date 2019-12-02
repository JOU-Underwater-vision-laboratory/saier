package com.hhit.downloadRes.service;

import com.hhit.downloadRes.domain.DownPerson;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: DownPersonService
 * @description: 处理下载文件的用户信息
 */
public interface DownPersonService  {

    DownPerson find(String person_name);

    DownPerson get(Integer person_id);

    List<DownPerson> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DownPerson downPerson);

    int update(DownPerson downPerson);

    int remove(Integer ids);
}
