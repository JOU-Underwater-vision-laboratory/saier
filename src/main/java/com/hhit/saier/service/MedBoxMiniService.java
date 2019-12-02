package com.hhit.saier.service;

import com.hhit.saier.domain.MedBoxMiniDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ray
 */
@Service
public interface MedBoxMiniService {

	MedBoxMiniDO get(Long id);

	List<MedBoxMiniDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MedBoxMiniDO medicine);

	int update(MedBoxMiniDO medicine);

	int remove(Long id);

	int batchRemove(Long[] ids);

    String findByGrid(String grid);
}
