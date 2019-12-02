package com.hhit.downloadRes.service;

import com.hhit.downloadRes.domain.SoftwareDO;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: DownPersonService
 * @description: 处理下载文件的用户信息
 */
public interface SoftwareService {

    SoftwareDO find(String soft_name,String soft_version);

    SoftwareDO findByFid(Long fid);

    SoftwareDO get(Integer soft_id);

//    boolean setOpen(Long fid, Long sid, String type);

    List<SoftwareDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SoftwareDO softwareDO);

    int update(SoftwareDO softwareDO);

    int remove(Integer soft_ids);

    int removeByFid(Long id);
}
