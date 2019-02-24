package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.UserQueue;

@Repository
public interface UserQueueMapper {
	int insert(UserQueue obj);
	int update(UserQueue obj);
	int replace(UserQueue obj);
	int delete(String id);
	UserQueue query(String id);
	List<UserQueue> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<UserQueue> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	List<UserQueue> listByParams(Map params);
	int batchInsert(@Param(value = "listInsertUserQueue") List<UserQueue> listUserQueueInsert);
	int batchUpdate(@Param(value = "listUpdateUserQueue") List<UserQueue> listUserQueueUpdate);
	int batchReplace(@Param(value = "listReplaceUserQueue") List<UserQueue> listUserQueueReplace);
	int batchDelete(@Param(value = "delIds") String[] delIds);	
}