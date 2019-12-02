package com.hhit.site.service;


import com.hhit.site.domain.ProjectDO;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: ProjectService
 * @description:
 * @create 2018-10-06
 */
public interface ProjectService {
    ProjectDO get(Long pid);

    List<ProjectDO> list(Map<String, Object> map);

    List<ProjectDO> findAll(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ProjectDO projectDO);

    int update(ProjectDO projectDO);

    int remove(Long pid);

    int batchRemove(Long[] pids);
}
