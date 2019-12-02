package com.hhit.site.service.impl;

import com.hhit.common.utils.FileUtil;
import com.hhit.site.dao.MemberDao;
import com.hhit.site.domain.MemberDO;
import com.hhit.site.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberMapper;
	
	@Override
	public MemberDO get(Long mid){
		return memberMapper.get(mid);
	}
	
	@Override
	public List<MemberDO> list(Map<String, Object> map){
		return memberMapper.list(map);
	}

    @Override
    public List<MemberDO> findAll(Map<String, Object> map) {
        return memberMapper.findAll(map);
    }
	
	@Override
	public int count(Map<String, Object> map){
		return memberMapper.count(map);
	}
	
	@Override
	public int save(MemberDO memberDO){
		return memberMapper.save(memberDO);
	}
	
	@Override
	public int update(MemberDO memberDO){
		return memberMapper.update(memberDO);
	}
	
	@Override
	public int remove(Long mid){
		return memberMapper.remove(mid);
	}
	
	@Override
	public int batchRemove(Long[] mids){
		return memberMapper.batchRemove(mids);
	}

	@Override
	public String updateMemberImg(MultipartFile file, String avatar_data, Long mid) throws Exception {
		String fileName = "";
		fileName = FileUtil.cutImage(file,avatar_data);
		return fileName;
	}

}
