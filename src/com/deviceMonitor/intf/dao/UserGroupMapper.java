package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.UserGroup;

@Repository
public interface UserGroupMapper {
	int insert(UserGroup obj);
	int update(UserGroup obj);
	int replace(UserGroup obj);
	int delete(String id);
	UserGroup query(String id);
	List<UserGroup> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<UserGroup> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	List<UserGroup> listByParams(Map params);
	int batchInsert(@Param(value = "listInsertUserGroup") List<UserGroup> listUserGroupInsert);
	int batchUpdate(@Param(value = "listUpdateUserGroup") List<UserGroup> listUserGroupUpdate);
	int batchReplace(@Param(value = "listReplaceUserGroup") List<UserGroup> listUserGroupReplace);
	int batchDelete(@Param(value = "delIds") String[] delIds);	
}