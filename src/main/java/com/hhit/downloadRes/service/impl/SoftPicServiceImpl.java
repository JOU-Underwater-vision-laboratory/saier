package com.hhit.downloadRes.service.impl;

import com.hhit.downloadRes.dao.SoftPicDao;
import com.hhit.downloadRes.domain.SoftPicDO;
import com.hhit.downloadRes.service.SoftPicService;
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
public class SoftPicServiceImpl implements SoftPicService {

    @Autowired
    private SoftPicDao softPicDao;


    @Override
    public SoftPicDO find(Long soft_fid, String soft_pic01) {
        return softPicDao.find(soft_fid, soft_pic01);
    }

    @Override
    public SoftPicDO findByFid(Long fid) {
        return softPicDao.findByFid(fid);
    }

    @Override
    public SoftPicDO get(Long soft_fid) {
        return softPicDao.get(soft_fid);
    }

    @Override
    public List<SoftPicDO> list(Map<String, Object> map) {
        return softPicDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return softPicDao.count(map);
    }

    @Override
    public int save(SoftPicDO softPicDO) {
        return softPicDao.save(softPicDO);
    }

    @Override
    public int update(SoftPicDO softPicDO) {
        return softPicDao.update(softPicDO);
    }

    @Override
    public int remove(Long soft_fids) {
        return softPicDao.remove(soft_fids);
    }
}
