package com.hhit.saier.dao;

import com.hhit.saier.domain.MedBoxDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 增删改查大药盒
 * @author ray
 */
@Mapper
public interface MedBoxDao {

    MedBoxDO get(Long id);

    List<MedBoxDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MedBoxDO notifyRecord);

    int update(MedBoxDO notifyRecord);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int batchSave(List<MedBoxDO> records);

    int changeRead(MedBoxDO notifyRecord);
}
