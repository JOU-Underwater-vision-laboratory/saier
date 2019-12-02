package com.hhit.saier.service;

import com.hhit.saier.domain.MedicineDO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author ray
 */
@Service
public interface MedicineService {

	MedicineDO get(String licensenumber);

	List<MedicineDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MedicineDO medicine);

	int update(MedicineDO medicine);

	int remove(String licensenumber);

	int batchRemove(String[] licensenumbers);

    String findByName(String param);

    MedicineDO getByBarCode(String barCode);

	String uploadPic(MultipartFile file, String avatar_data, String licensenumber);

    List<String> findDrug(String cnName);
}
