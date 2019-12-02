package com.hhit.saier.service.impl;

import com.hhit.common.utils.FileUtil;
import com.hhit.saier.dao.MedicineDao;
import com.hhit.saier.domain.MedicineDO;
import com.hhit.saier.service.MedicineService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author ray
 */
@Service
public class MedicineServiceImpl implements MedicineService {

	@Resource
	private MedicineDao medicineDao;
	
	@Override
	public MedicineDO get(String id){
		return medicineDao.get(id);
	}

	@Override
	public List<MedicineDO> list(Map<String, Object> map){
		return medicineDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return medicineDao.count(map);
	}
	
	@Override
	public int save(MedicineDO notifyRecord){
		return medicineDao.save(notifyRecord);
	}
	
	@Override
	public int update(MedicineDO notifyRecord){
		return medicineDao.update(notifyRecord);
	}
	
	@Override
	public int remove(String  licensenumber){
		return medicineDao.remove(licensenumber);
	}

	@Override
	public int batchRemove(String[] licensenumbers){
		return medicineDao.batchRemove(licensenumbers);
	}

	@Override
	public String findByName(String param) {
		return medicineDao.findByName(param);
	}

	@Override
	public MedicineDO getByBarCode(String barCode) {
		return medicineDao.getByBarCode(barCode);
	}

	@Override
	public String uploadPic(MultipartFile file, String avatar_data, String licensenumber) {
		String fileName = "";
		try {
			fileName = FileUtil.cutImage2(file,avatar_data,licensenumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	@Override
	public List<String> findDrug(String cnName) {
		return medicineDao.findDrug(cnName);
	}
}
