package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviceMonitor.intf.dao.UserMapper;
import com.deviceMonitor.intf.service.IUserService;
import com.deviceMonitor.model.User;
import com.deviceMonitor.util.ListRange;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	public int replace(User user) {
		return userMapper.replace(user);
	}

	@Override
	public int delete(String id) {
		return userMapper.delete(id);
	}

	@Override
	public User query(String id) {
		return userMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = userMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<User> list = userMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<User> listAll() {
        return userMapper.listAll();
    }
    
    @Override
    public List<User> list(int start, int limit) {
        return userMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return userMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return userMapper.isExists(params);
	}
    
    @Override
    public List<User> listByParams(Map params) {
        return userMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<User> listUserInsert) {
        return userMapper.batchInsert(listUserInsert);
    }

    @Override
    public int batchUpdate(List<User> listUserUpdate) {
        return userMapper.batchUpdate(listUserUpdate);
    }
    
    @Override
    public int batchReplace(List<User> listUserReplace) {
        return userMapper.batchReplace(listUserReplace);
    }

	@Override
	public int batchDelete(String[] delIds) {
		return userMapper.batchDelete(delIds);
	}

	@Override
	public User login(String loginName, String pwd) {
		return userMapper.login(loginName, pwd);
	}

	@Override
	public List<User> listByDeviceUserIds(List<Long> listDeviceUserId) {
		return userMapper.listByDeviceUserIds(listDeviceUserId);
	}
}