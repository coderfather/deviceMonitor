package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.UserQueue;
import com.deviceMonitor.util.ListRange;

import java.util.List;
import java.util.Map;

public interface IUserQueueService {
	int insert(UserQueue obj);
	int update(UserQueue obj);
	int replace(UserQueue obj);
	int delete(String id);
	UserQueue query(String id);
	void list(ListRange listRange);
	List<UserQueue> list(int start, int limit);
	List<UserQueue> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<UserQueue> listByParams(Map params);
	int batchInsert(List<UserQueue> listUserQueueInsert);
	int batchUpdate(List<UserQueue> listUserQueueUpdate);
	int batchReplace(List<UserQueue> listUserQueueReplace);
	int batchDelete(String[] delIds);	
}