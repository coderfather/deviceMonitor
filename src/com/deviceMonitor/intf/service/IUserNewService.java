package com.deviceMonitor.intf.service;

public interface IUserNewService<T, PK> extends IBaseService<T, PK> {
	
	T selectByLoginName(String loginName);
	
}
