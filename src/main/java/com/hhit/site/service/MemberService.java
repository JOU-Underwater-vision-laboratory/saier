package com.hhit.site.service;


import com.hhit.site.domain.MemberDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author liujun
 * @Title: MemberService
 * @description:
 * @create 2018-10-06
 */
public interface MemberService {
    MemberDO get(Long mid);

    List<MemberDO> list(Map<String, Object> map);

    List<MemberDO> findAll(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(MemberDO memberDO);

    int update(MemberDO memberDO);

    int remove(Long mid);

    int batchRemove(Long[] mids);

    String updateMemberImg(MultipartFile file, String avatar_data, Long mid) throws Exception;
}
