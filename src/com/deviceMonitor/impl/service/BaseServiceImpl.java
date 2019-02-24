package com.deviceMonitor.impl.service;

import java.io.Serializable;

import com.deviceMonitor.intf.dao.IBaseDao;
import com.deviceMonitor.intf.service.IBaseService;

public class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {
	private IBaseDao<T, PK> baseDao;

	public IBaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}
	
	public T select(PK id) {
		return baseDao.select(id);
	}
}
