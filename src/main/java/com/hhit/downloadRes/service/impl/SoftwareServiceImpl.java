package com.hhit.downloadRes.service.impl;

import com.hhit.downloadRes.dao.SoftwareDao;
import com.hhit.downloadRes.domain.SoftwareDO;
import com.hhit.downloadRes.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: DownPersonServiceImpl
 * @description: 处理下载文件的用户信息的实现类
 */
@Service
public class SoftwareServiceImpl implements SoftwareService {

    @Autowired
    private SoftwareDao softwareDao;

    @Override
    public SoftwareDO find(String soft_name,String soft_version) {
        return softwareDao.find(soft_name, soft_version);
    }

    @Override
    public SoftwareDO findByFid(Long fid) {
        return softwareDao.findByFid(fid);
    }

    @Override
    public SoftwareDO get(Integer soft_id) {
        return softwareDao.get(soft_id);
    }

    @Override
    public List<SoftwareDO> list(Map<String, Object> map) {
        return softwareDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return softwareDao.count(map);
    }

    @Override
    public int save(SoftwareDO softwareDO) {
        return softwareDao.save(softwareDO);
    }

    @Override
    public int update(SoftwareDO softwareDO) {
        return softwareDao.update(softwareDO);
    }

    @Override
    public int remove(Integer soft_ids) {
        return softwareDao.remove(soft_ids);
    }

    @Override
    public int removeByFid(Long id) {
        return softwareDao.removeByFid(id);
    }
}
