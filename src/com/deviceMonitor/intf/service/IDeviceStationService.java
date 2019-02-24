package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.util.ListRange;

import java.util.List;
import java.util.Map;

public interface IDeviceStationService {
	int insert(DeviceStation obj);
	int update(DeviceStation obj);
	int replace(DeviceStation obj);
	int delete(String id);
	DeviceStation query(String id);
	void list(ListRange listRange);
	List<DeviceStation> list(int start, int limit);
	List<DeviceStation> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	int isExistsDevice(Map params);	
	List<DeviceStation> listByParams(Map params);
	int batchInsert(List<DeviceStation> listDeviceStationInsert);
	boolean batchUpdate(List<DeviceStation> listDeviceStationUpdate);
	int batchReplace(List<DeviceStation> listDeviceStationReplace);
	int batchDelete(String[] delIds);
}