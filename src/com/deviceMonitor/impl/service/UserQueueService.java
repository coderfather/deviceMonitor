package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviceMonitor.intf.dao.UserQueueMapper;
import com.deviceMonitor.intf.service.IUserQueueService;
import com.deviceMonitor.model.UserQueue;
import com.deviceMonitor.util.ListRange;

@Service
public class UserQueueService implements IUserQueueService {
	@Autowired
	private UserQueueMapper userQueueMapper;
	
	@Override
	public int insert(UserQueue userQueue) {
		return userQueueMapper.insert(userQueue);
	}

	@Override
	public int update(UserQueue userQueue) {
		return userQueueMapper.update(userQueue);
	}

	@Override
	public int replace(UserQueue userQueue) {
		return userQueueMapper.replace(userQueue);
	}

	@Override
	public int delete(String id) {
		return userQueueMapper.delete(id);
	}

	@Override
	public UserQueue query(String id) {
		return userQueueMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = userQueueMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<UserQueue> list = userQueueMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<UserQueue> listAll() {
        return userQueueMapper.listAll();
    }
    
    @Override
    public List<UserQueue> list(int start, int limit) {
        return userQueueMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return userQueueMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return userQueueMapper.isExists(params);
	}
    
    @Override
    public List<UserQueue> listByParams(Map params) {
        return userQueueMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<UserQueue> listUserQueueInsert) {
        return userQueueMapper.batchInsert(listUserQueueInsert);
    }

    @Override
    public int batchUpdate(List<UserQueue> listUserQueueUpdate) {
        return userQueueMapper.batchUpdate(listUserQueueUpdate);
    }
    
    @Override
    public int batchReplace(List<UserQueue> listUserQueueReplace) {
        return userQueueMapper.batchReplace(listUserQueueReplace);
    }

	@Override
	public int batchDelete(String[] delIds) {
		return userQueueMapper.batchDelete(delIds);
	}
}