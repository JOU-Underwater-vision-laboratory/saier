package com.hhit.system.service;

import java.util.List;

import com.hhit.system.domain.RoleMenuDO;
import com.hhit.system.domain.UserRoleDO;
import org.springframework.stereotype.Service;

import com.hhit.system.domain.RoleDO;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int saveRoleMenu(RoleMenuDO roleMenu);

	int update(RoleDO role);

	public UserRoleDO getUid(Long id);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);
}
