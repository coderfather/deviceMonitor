package com.deviceMonitor.intf.service;

import java.util.List;
import java.util.Map;

import com.deviceMonitor.model.DeviceParam;
import com.deviceMonitor.model.syn.DeviceTagsView;
import com.deviceMonitor.util.ListRange;

public interface IDeviceParamService {
	int insert(DeviceParam obj);
	int update(DeviceParam obj);
	int replace(DeviceParam obj);
	int delete(String id);
	DeviceParam query(String id);
	void list(ListRange listRange);
	List<DeviceParam> list(int start, int limit);
	List<DeviceParam> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<DeviceParam> listByParams(Map params);
	int batchInsert(List<DeviceParam> listDeviceParamInsert);
	int batchUpdate(List<DeviceParam> listDeviceParamUpdate);
	int batchReplace(List<DeviceParam> listDeviceParamReplace);
	int batchDelete(Long[] delIds);
    int save(String registerNumber, int resolutionVer, List<DeviceTagsView> listChangedDeviceTagsView, List<Long> listDeviceUserId);	
}