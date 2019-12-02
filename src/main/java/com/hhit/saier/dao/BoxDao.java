package com.hhit.saier.dao;

import com.hhit.saier.domain.BoxDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 增删改查大药盒
 * @author ray
 */
@Mapper
public interface BoxDao {

    List<BoxDO> get(Long id);

    List<BoxDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BoxDO boxDO);

    int update(BoxDO boxDO);
    int updateM(BoxDO boxDO);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int batchSave(List<BoxDO> records);

    int changeRead(BoxDO record);

    List<BoxDO> findByLicense(String license);

    List<BoxDO> findByGB1(Integer gid, Integer bid);

    BoxDO findByGB2(Integer gid, Integer bid, String mid);

    int removeM(Integer boxId, String mboxid);
}
