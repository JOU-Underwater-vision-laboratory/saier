package com.hhit.saier.service;

import com.hhit.saier.domain.BoxDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ray
 */
@Service
public interface BoxService {

	List<BoxDO> get(Long id);

	List<BoxDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(BoxDO medicine);

	int update(BoxDO medicine);

    int updateM(BoxDO boxDO);

    int remove(Long id);

	int removeM(Integer boxId, String mboxid);

	int batchRemove(Long[] ids);

	List<BoxDO> findByLicense(String license);

	List<BoxDO> findByGB1(Integer gridid, Integer bid);

	BoxDO findByGB2(Integer gridid, Integer bid, String mid);
}
