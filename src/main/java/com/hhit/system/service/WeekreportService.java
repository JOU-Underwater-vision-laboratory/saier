package com.hhit.system.service;

import com.hhit.system.domain.WeekreportDO;

import java.util.List;
import java.util.Map;

/**
 * @author leoterrior
 * @Title: WeekreportService
 * @description:
 * @create 2018-10-06
 */
public interface WeekreportService {

    WeekreportDO get(Long nid);

    List<WeekreportDO> list(Map<String, Object> map);

    List<WeekreportDO> findAll(Map<String, Object> map);

    Integer count(Map<String, Object> map);

    Integer save(WeekreportDO weekreportDO);

    Integer update(WeekreportDO weekreportDO);

    Integer remove(Long wid);

    Integer batchRemove(Long[] wids);

    Integer reply(WeekreportDO notify);
}
