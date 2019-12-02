package com.hhit.downloadRes.service;

import com.hhit.downloadRes.domain.SoftPicDO;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: DownPersonService
 * @description: 处理下载文件的用户信息
 */
public interface SoftPicService {

    SoftPicDO find(Long soft_fid, String soft_pic01);

    SoftPicDO findByFid(Long fid);

    SoftPicDO get(Long soft_fid);

    List<SoftPicDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SoftPicDO softPicDO);

    int update(SoftPicDO softPicDO);

    int remove(Long soft_fids);
}
