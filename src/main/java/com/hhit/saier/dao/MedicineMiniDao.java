package com.hhit.saier.dao;

import com.hhit.saier.domain.MedicineMini;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *  根据barcode查找药品
 */
@Mapper
public interface MedicineMiniDao {

    MedicineMini get(String id);

    List<MedicineMini> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MedicineMini notifyRecord);

    int update(MedicineMini notifyRecord);

    int remove(String licensenumber);

    int batchRemove(String[] licensenumbers);

    int batchSave(List<MedicineMini> records);

    int changeRead(MedicineMini notifyRecord);

    String findByName(String param);

    MedicineMini findByBarCode(String barCode);
}
