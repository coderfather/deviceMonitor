package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.DeviceParam;

@Repository
public interface DeviceParamMapper {
	int insert(DeviceParam obj);
	int update(DeviceParam obj);
	int replace(DeviceParam obj);
	int delete(String id);
	DeviceParam query(String id);
	List<DeviceParam> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<DeviceParam> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	List<DeviceParam> listByParams(@Param(value = "params") Map params);
	int batchInsert(@Param(value = "listInsertDeviceParam") List<DeviceParam> listDeviceParamInsert);
	int batchUpdate(@Param(value = "listUpdateDeviceParam") List<DeviceParam> listDeviceParamUpdate);
	int batchReplace(@Param(value = "listReplaceDeviceParam") List<DeviceParam> listDeviceParamReplace);
	int batchDelete(@Param(value = "delIds") Long[] delIds);	
}