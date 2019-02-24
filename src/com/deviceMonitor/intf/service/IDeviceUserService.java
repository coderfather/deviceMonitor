package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.model.DeviceUser;
import com.deviceMonitor.util.ListRange;

import java.util.List;
import java.util.Map;

public interface IDeviceUserService {
	int insert(DeviceUser obj, DeviceStation deviceStation);
	int update(DeviceUser obj, DeviceStation deviceStation);
	int replace(DeviceUser obj);
	int delete(String id);
	DeviceUser query(String id);
	void list(ListRange listRange);
	List<DeviceUser> list(int start, int limit);
	List<DeviceUser> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<DeviceUser> listByParams(Map params);
	int batchInsert(List<DeviceUser> listDeviceUserInsert);
	int batchUpdate(List<DeviceUser> listDeviceUserUpdate);
	int batchReplace(List<DeviceUser> listDeviceUserReplace);
	int batchDelete(String[] delIds);
	DeviceUser queryByParams(Map params);	
}