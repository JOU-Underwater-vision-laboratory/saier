package com.hhit.saier.service;

import com.hhit.saier.domain.MedicinePlanDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ray
 */
@Service
public interface MedicinePlanService {

	MedicinePlanDO get(Long id);

	List<MedicinePlanDO> list(Map<String, Object> map);

	List<String> listName(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MedicinePlanDO medicine);

	int update(MedicinePlanDO medicine);

	int remove(Long id);

	int remove(String medlicense, Integer boxid);

	int batchRemove(Long[] ids);
}
