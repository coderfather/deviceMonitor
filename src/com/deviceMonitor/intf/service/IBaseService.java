package com.deviceMonitor.intf.service;

public interface IBaseService<T, PK> {
	T select(PK id);
}
