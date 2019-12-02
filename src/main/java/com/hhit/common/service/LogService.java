package com.hhit.common.service;

import org.springframework.stereotype.Service;

import com.hhit.common.domain.LogDO;
import com.hhit.common.domain.PageDO;
import com.hhit.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
