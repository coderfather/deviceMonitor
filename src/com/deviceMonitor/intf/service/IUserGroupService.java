package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.UserGroup;
import com.deviceMonitor.util.ListRange;

import java.util.List;
import java.util.Map;

public interface IUserGroupService {
	int insert(UserGroup obj);
	int update(UserGroup obj);
	int replace(UserGroup obj);
	int delete(String id);
	UserGroup query(String id);
	void list(ListRange listRange);
	List<UserGroup> list(int start, int limit);
	List<UserGroup> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<UserGroup> listByParams(Map params);
	int batchInsert(List<UserGroup> listUserGroupInsert);
	int batchUpdate(List<UserGroup> listUserGroupUpdate);
	int batchReplace(List<UserGroup> listUserGroupReplace);
	int batchDelete(String[] delIds);	
}