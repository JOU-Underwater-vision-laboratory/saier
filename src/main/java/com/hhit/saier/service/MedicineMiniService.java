package com.hhit.saier.service;

import com.hhit.saier.domain.MedicineMini;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author ray
 */
@Service
public interface MedicineMiniService {

	MedicineMini get(String licensenumber);

	List<MedicineMini> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(MedicineMini medicine);

	int update(MedicineMini medicine);

	int remove(String licensenumber);

	int batchRemove(String[] licensenumbers);

    String findByName(String param);

    MedicineMini getByBarCode(String barCode);

	String uploadPic(MultipartFile file, String avatar_data, String licensenumber);
}
