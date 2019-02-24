package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.User;
import com.deviceMonitor.util.ListRange;

import java.util.List;
import java.util.Map;

public interface IUserService {
	int insert(User obj);
	int update(User obj);
	int replace(User obj);
	int delete(String id);
	User query(String id);
	void list(ListRange listRange);
	List<User> list(int start, int limit);
	List<User> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<User> listByParams(Map params);
	int batchInsert(List<User> listUserInsert);
	int batchUpdate(List<User> listUserUpdate);
	int batchReplace(List<User> listUserReplace);
	int batchDelete(String[] delIds);
	User login(String loginName, String pwd);
	List<User> listByDeviceUserIds(List<Long> listDeviceUserId);	
}