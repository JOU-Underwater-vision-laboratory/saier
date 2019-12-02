package com.hhit.saier.service.impl;

import com.hhit.common.utils.FileUtil;
import com.hhit.saier.dao.MedicineMiniDao;
import com.hhit.saier.domain.MedicineMini;
import com.hhit.saier.service.MedicineMiniService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author ray
 */
@Service
public class MedicineMiniServiceImpl implements MedicineMiniService {

	@Resource
	private MedicineMiniDao medicineMiniDao;
	
	@Override
	public MedicineMini get(String id){
		return medicineMiniDao.get(id);
	}

	@Override
	public List<MedicineMini> list(Map<String, Object> map){
		return medicineMiniDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return medicineMiniDao.count(map);
	}
	
	@Override
	public int save(MedicineMini notifyRecord){
		return medicineMiniDao.save(notifyRecord);
	}
	
	@Override
	public int update(MedicineMini notifyRecord){
		return medicineMiniDao.update(notifyRecord);
	}
	
	@Override
	public int remove(String  licensenumber){
		return medicineMiniDao.remove(licensenumber);
	}

	@Override
	public int batchRemove(String[] licensenumbers){
		return medicineMiniDao.batchRemove(licensenumbers);
	}

	@Override
	public String findByName(String param) {
		return medicineMiniDao.findByName(param);
	}

	@Override
	public MedicineMini getByBarCode(String barCode) {
		return medicineMiniDao.findByBarCode(barCode);
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
}
