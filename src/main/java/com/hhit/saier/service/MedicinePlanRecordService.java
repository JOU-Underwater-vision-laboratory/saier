package com.hhit.saier.service;

import com.hhit.saier.domain.MedicinePlanRecordDO;
import com.hhit.saier.domain.TimeProp;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ray
 */
@Service
public interface MedicinePlanRecordService {

	MedicinePlanRecordDO get(Long id);

	List<MedicinePlanRecordDO> list(Map<String, Object> map);

	List<String> listName(Map<String, Object> map);

	int count(Map<String, Object> map);

	List<HashMap> countYear(Map<String, Object> map);
	List<HashMap> countMonth(Map<String, Object> map);
	List<HashMap> countDay(Map<String, Object> map);

	int save(MedicinePlanRecordDO medicine);

	int update(MedicinePlanRecordDO medicine);

	int remove(Long id);

	int remove(String medlicense, Integer boxid);

	int batchRemove(Long[] ids);
}
