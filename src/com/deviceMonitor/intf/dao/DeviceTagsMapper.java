package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.DeviceTags;
import com.deviceMonitor.model.syn.DeviceTagsView;
import com.deviceMonitor.util.ListRangeEx;

@Repository
public interface DeviceTagsMapper {
	int insert(DeviceTags obj);
	int update(DeviceTags obj);
	int replace(DeviceTags obj);
	int delete(String id);
	DeviceTags query(String id);
	List<DeviceTags> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<DeviceTags> listAll();
	int listAllRecordsCount();
	List<DeviceTagsView> list(@Param(value = "listRangeEx") ListRangeEx listRangeEx);
	int listAllRecordsCount(@Param(value = "listRangeEx") ListRangeEx listRangeEx);
	int isExists(@Param(value = "params") Map params);
	List<DeviceTags> listByParams(@Param(value = "params") Map params);
	int batchInsert(@Param(value = "listInsertDeviceTags") List<DeviceTags> listDeviceTagsInsert);
	int batchUpdate(@Param(value = "listUpdateDeviceTags") List<DeviceTags> listDeviceTagsUpdate);
	int batchReplace(@Param(value = "listReplaceDeviceTags") List<DeviceTags> listDeviceTagsReplace);
	int batchDelete(@Param(value = "delIds") String[] delIds);	
}