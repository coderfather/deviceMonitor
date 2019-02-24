package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.DeviceStation;

@Repository
public interface DeviceStationMapper {
	int insert(DeviceStation obj);
	int update(DeviceStation obj);
	int replace(DeviceStation obj);
	int delete(String id);
	DeviceStation query(String id);
	List<DeviceStation> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<DeviceStation> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	int isExistsDevice(@Param(value = "params") Map params);	
	List<DeviceStation> listByParams(@Param(value = "params") Map params);
	int batchInsert(@Param(value = "listInsertDeviceStation") List<DeviceStation> listDeviceStationInsert);
	int batchUpdate(@Param(value = "listUpdateDeviceStation") List<DeviceStation> listDeviceStationUpdate);
	int batchReplace(@Param(value = "listReplaceDeviceStation") List<DeviceStation> listDeviceStationReplace);
	int batchDelete(@Param(value = "delIds") String[] delIds);
	int updateDevice(DeviceStation deviceStation);
    int updateVer(@Param(value = "registerNumber") String registerNumber, @Param(value = "resolutionVer") int resolutionVer);
}