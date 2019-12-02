package com.hhit.saier.dao;

import com.hhit.saier.domain.MedBoxMiniDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 增删改查小药格
 * @author ray
 */
@Mapper
public interface MedBoxMiniDao {

    MedBoxMiniDO get(Long id);

    List<MedBoxMiniDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MedBoxMiniDO notifyRecord);

    int update(MedBoxMiniDO notifyRecord);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int batchSave(List<MedBoxMiniDO> records);

    int changeRead(MedBoxMiniDO notifyRecord);

    String findByGrid(String grid);
}
