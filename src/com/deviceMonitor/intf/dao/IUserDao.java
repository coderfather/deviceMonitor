package com.deviceMonitor.intf.dao;

public interface IUserDao<T, PK> extends IBaseDao<T, PK> {
	
	T selectByLoginName(String loginName);
	
}
