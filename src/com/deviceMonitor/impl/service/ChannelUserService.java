package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviceMonitor.intf.dao.ChannelUserMapper;
import com.deviceMonitor.intf.service.IChannelUserService;
import com.deviceMonitor.model.ChannelUser;
import com.deviceMonitor.util.ListRange;

@Service
public class ChannelUserService implements IChannelUserService {
	@Autowired
	private ChannelUserMapper channelUserMapper;
	
	@Override
	public int insert(ChannelUser channelUser) {
		return channelUserMapper.insert(channelUser);
	}

	@Override
	public int update(ChannelUser channelUser) {
		return channelUserMapper.update(channelUser);
	}

	@Override
	public int delete(String id) {
		return channelUserMapper.delete(id);
	}

	@Override
	public ChannelUser query(String id) {
		return channelUserMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = channelUserMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<ChannelUser> list = channelUserMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<ChannelUser> listAll() {
        return channelUserMapper.listAll();
    }
    
    @Override
    public List<ChannelUser> list(int start, int limit) {
        return channelUserMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return channelUserMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return channelUserMapper.isExists(params);
	}
    
    @Override
    public List<ChannelUser> listByParams(Map params) {
        return channelUserMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<ChannelUser> listChannelUserInsert) {
        return channelUserMapper.batchInsert(listChannelUserInsert);
    }

    @Override
    public int batchUpdate(List<ChannelUser> listChannelUserUpdate) {
        return channelUserMapper.batchUpdate(listChannelUserUpdate);
    }
    
    @Override
    public int batchReplace(List<ChannelUser> listChannelUserReplace) {
        return channelUserMapper.batchReplace(listChannelUserReplace);
    }

	@Override
	public int batchDelete(Long[] delIds) {
		return channelUserMapper.batchDelete(delIds);
	}

	@Override
	public List<ChannelUser> listAllByChannelIds(List listChannelId) {
		return channelUserMapper.listAllByChannelIds(listChannelId);
	}
}