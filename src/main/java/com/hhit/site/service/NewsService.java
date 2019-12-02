package com.hhit.site.service;


import com.hhit.site.domain.NewsDO;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: NewsService
 * @description:
 * @create 2018-10-06
 */
public interface NewsService {
    NewsDO get(Long nid);

    List<NewsDO> list(Map<String, Object> map);

    List<NewsDO> findAll(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(NewsDO newsDO);

    int update(NewsDO newsDO);

    int remove(Long nid);

    int batchRemove(Long[] nids);
}
