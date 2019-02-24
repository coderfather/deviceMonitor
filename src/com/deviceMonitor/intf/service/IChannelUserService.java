package com.deviceMonitor.intf.service;

import com.deviceMonitor.model.ChannelUser;
import com.deviceMonitor.util.ListRange;

import java.util.List;
import java.util.Map;

public interface IChannelUserService {
	int insert(ChannelUser obj);
	int update(ChannelUser obj);
	int delete(String id);
	ChannelUser query(String id);
	void list(ListRange listRange);
	List<ChannelUser> list(int start, int limit);
	List<ChannelUser> listAll();
	int listAllRecordsCount();
	int isExists(Map params);
	List<ChannelUser> listByParams(Map params);
	int batchInsert(List<ChannelUser> listChannelUserInsert);
	int batchUpdate(List<ChannelUser> listChannelUserUpdate);
	int batchReplace(List<ChannelUser> listChannelUserReplace);
	int batchDelete(Long[] delIds);
	List<ChannelUser> listAllByChannelIds(List listChannelId);	
}