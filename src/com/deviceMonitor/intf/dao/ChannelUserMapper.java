package com.deviceMonitor.intf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deviceMonitor.model.ChannelUser;

@Repository
public interface ChannelUserMapper {
	int insert(ChannelUser obj);
	int update(ChannelUser obj);
	int replace(ChannelUser obj);
	int delete(String id);
	ChannelUser query(String id);
	List<ChannelUser> list(@Param(value = "start") int start, @Param(value = "limit") int limit);
	List<ChannelUser> listAll();
	int listAllRecordsCount();
	int isExists(@Param(value = "params") Map params);
	List<ChannelUser> listByParams(Map params);
	int batchInsert(@Param(value = "listInsertChannelUser") List<ChannelUser> listChannelUserInsert);
	int batchUpdate(@Param(value = "listUpdateChannelUser") List<ChannelUser> listChannelUserUpdate);
	int batchReplace(@Param(value = "listReplaceChannelUser") List<ChannelUser> listChannelUserReplace);
	int batchDelete(@Param(value = "delIds") Long[] delIds);
	List<ChannelUser> listAllByChannelIds(@Param(value = "listChannelId") List listChannelId);	
}