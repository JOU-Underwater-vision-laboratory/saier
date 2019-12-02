package com.hhit.saier.service;

import com.hhit.saier.domain.MedBoxDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ray
 */
@Service
public interface MedBoxService {

	MedBoxDO get(Long id);

	List<MedBoxDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MedBoxDO medicine);

	int update(MedBoxDO medicine);

	int remove(Long id);

	int batchRemove(Long[] ids);
}
