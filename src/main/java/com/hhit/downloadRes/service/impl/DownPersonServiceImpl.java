package com.hhit.downloadRes.service.impl;

import com.hhit.downloadRes.dao.DownPersonDao;
import com.hhit.downloadRes.domain.DownPerson;
import com.hhit.downloadRes.service.DownPersonService;
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
public class DownPersonServiceImpl implements DownPersonService {

    @Autowired
    private DownPersonDao downPersonDao;

    @Override
    public DownPerson find(String person_name) { return downPersonDao.find(person_name); }

    @Override
    public DownPerson get(Integer id) { return downPersonDao.get(id); }

    @Override
    public List<DownPerson> list(Map<String, Object> map) { return downPersonDao.list(map); }

    @Override
    public int count(Map<String, Object> map) { return downPersonDao.count(map); }

    @Override
    public int save(DownPerson downPerson) { return downPersonDao.save(downPerson); }

    @Override
    public int update(DownPerson downPerson) { return downPersonDao.update(downPerson); }

    @Override
    public int remove(Integer ids) { return downPersonDao.remove(ids); }
}
