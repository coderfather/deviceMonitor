package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.DeviceTags;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.ListRangeEx;

import java.util.List;
import java.util.Map;

public interface IDeviceTagsService {
	int insert(DeviceTags obj);
	int update(DeviceTags obj);
	int replace(DeviceTags obj);
	int delete(String id);
	DeviceTags query(String id);
	void list(ListRange listRange);
	List<DeviceTags> list(int start, int limit);
	List<DeviceTags> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<DeviceTags> listByParams(Map params);
	int batchInsert(List<DeviceTags> listDeviceTagsInsert);
	int batchUpdate(List<DeviceTags> listDeviceTagsUpdate);
	int batchReplace(List<DeviceTags> listDeviceTagsReplace);
	int batchDelete(String[] delIds);
	void list(ListRangeEx listRangeEx);	
	int listAllRecordsCount(ListRangeEx listRangeEx);
}