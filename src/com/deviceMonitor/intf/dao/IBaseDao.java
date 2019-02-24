package com.deviceMonitor.intf.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface IBaseDao<T, PK> {
	T select(PK id);
}
