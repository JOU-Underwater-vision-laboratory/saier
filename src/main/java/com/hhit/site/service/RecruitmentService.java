package com.hhit.site.service;

import com.hhit.site.domain.RecruitmentDO;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: RecruitmentService
 * @description:
 * @create 2018-10-06
 */
public interface RecruitmentService {

    RecruitmentDO get(Long nid);

    Integer remove(Long id);

    Integer batchRemove(Long[] nids);

    List<RecruitmentDO> list(Map<String, Object> map);

    List<RecruitmentDO> findAll(Map<String, Object> map);

    Integer count(Map<String, Object> map);

    Integer save(RecruitmentDO recruitmentDO);

    Integer update(RecruitmentDO recruitmentDO);
}
