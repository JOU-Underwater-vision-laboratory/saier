package com.hhit.saier.dao;

import com.hhit.saier.domain.MedicinePlanRecordDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ray
 */
@Mapper
public interface MedicinePlanRecordDao {

    MedicinePlanRecordDO get(Long id);

    List<MedicinePlanRecordDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<HashMap> countYear(Map<String, Object> map);
    List<HashMap> countMonth(Map<String, Object> map);
    List<HashMap> countDay(Map<String, Object> map);

    int save(MedicinePlanRecordDO notifyRecord);

    int update(MedicinePlanRecordDO notifyRecord);

    int removeM(String medlicense, Integer boxid);

    int batchRemove(Long[] ids);

    int batchSave(List<MedicinePlanRecordDO> records);

    int changeRead(MedicinePlanRecordDO notifyRecord);

    int remove(Long id);

    List<String> listName(Map<String, Object> map);
}
