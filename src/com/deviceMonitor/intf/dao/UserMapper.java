package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.User;

@Repository
public interface UserMapper {
	int insert(User obj);
	int update(User obj);
	int replace(User obj);
	int delete(String id);
	User query(String id);
	List<User> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<User> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	List<User> listByParams(Map params);
	int batchInsert(@Param(value = "listInsertUser") List<User> listUserInsert);
	int batchUpdate(@Param(value = "listUpdateUser") List<User> listUserUpdate);
	int batchReplace(@Param(value = "listReplaceUser") List<User> listUserReplace);
	int batchDelete(@Param(value = "delIds") String[] delIds);
	User login(@Param(value = "loginName") String loginName, @Param(value = "pwd") String pwd);
	List<User> listByDeviceUserIds(@Param(value = "listDeviceUserId") List<Long> listDeviceUserId);	
}