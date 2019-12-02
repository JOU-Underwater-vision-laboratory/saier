package com.hhit.saier.dao;

import com.hhit.saier.domain.MedicinePlanDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 增删改查服药计划
 * @author ray
 */
@Mapper
public interface MedicinePlanDao {

    MedicinePlanDO get(Long id);

    List<MedicinePlanDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MedicinePlanDO notifyRecord);

    int update(MedicinePlanDO notifyRecord);

    int removeM(String  medlicense,Integer boxid);

    int batchRemove(Long[] ids);

    int batchSave(List<MedicinePlanDO> records);

    int changeRead(MedicinePlanDO notifyRecord);

    int remove(Long id);

    List<String> listName(Map<String, Object> map);
}
