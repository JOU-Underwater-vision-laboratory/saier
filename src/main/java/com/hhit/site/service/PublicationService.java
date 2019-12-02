package com.hhit.site.service;

import com.hhit.site.domain.PublicationDO;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: PublicationService
 * @description:
 * @create 2018-10-06
 */
public interface PublicationService {
    PublicationDO get(Long pid);

    List<PublicationDO> list(Map<String, Object> map);

    List<PublicationDO> findAll(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(PublicationDO publicationDO);

    int update(PublicationDO publicationDO);

    int remove(Long cid);

    int batchRemove(Long[] pids);
}
