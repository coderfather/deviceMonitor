package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.DeviceUser;

@Repository
public interface DeviceUserMapper {
	int insert(DeviceUser obj);
	int update(DeviceUser obj);
	int replace(DeviceUser obj);
	int delete(String id);
	DeviceUser query(String id);
	List<DeviceUser> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<DeviceUser> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	List<DeviceUser> listByParams(Map params);
	int batchInsert(@Param(value = "listInsertDeviceUser") List<DeviceUser> listDeviceUserInsert);
	int batchUpdate(@Param(value = "listUpdateDeviceUser") List<DeviceUser> listDeviceUserUpdate);
	int batchReplace(@Param(value = "listReplaceDeviceUser") List<DeviceUser> listDeviceUserReplace);
	int batchDelete(@Param(value = "delIds") String[] delIds);
	DeviceUser queryByParams(@Param(value = "params") Map params);
	int batchUpdateVer(@Param(value = "ids") List<Long> ids);	
}