package com.hhit.saier.dao;

import com.hhit.saier.domain.MedicineDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 增删改查药品
 * @author ray
 */
@Mapper
public interface MedicineDao {

    MedicineDO get(String id);

    List<MedicineDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MedicineDO notifyRecord);

    int update(MedicineDO notifyRecord);

    int remove(String licensenumber);

    int batchRemove(String[] licensenumbers);

    int batchSave(List<MedicineDO> records);

    int changeRead(MedicineDO notifyRecord);

    String findByName(String param);

    MedicineDO getByBarCode(String barCode);

    List<String> findDrug(String cnName);
}
