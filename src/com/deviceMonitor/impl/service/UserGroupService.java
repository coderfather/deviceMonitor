package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviceMonitor.intf.dao.UserGroupMapper;
import com.deviceMonitor.intf.service.IUserGroupService;
import com.deviceMonitor.model.UserGroup;
import com.deviceMonitor.util.ListRange;

@Service
public class UserGroupService implements IUserGroupService {
	@Autowired
	private UserGroupMapper userGroupMapper;
	
	@Override
	public int insert(UserGroup userGroup) {
		return userGroupMapper.insert(userGroup);
	}

	@Override
	public int update(UserGroup userGroup) {
		return userGroupMapper.update(userGroup);
	}

	@Override
	public int replace(UserGroup userGroup) {
		return userGroupMapper.replace(userGroup);
	}

	@Override
	public int delete(String id) {
		return userGroupMapper.delete(id);
	}

	@Override
	public UserGroup query(String id) {
		return userGroupMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = userGroupMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<UserGroup> list = userGroupMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<UserGroup> listAll() {
        return userGroupMapper.listAll();
    }
    
    @Override
    public List<UserGroup> list(int start, int limit) {
        return userGroupMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return userGroupMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return userGroupMapper.isExists(params);
	}
    
    @Override
    public List<UserGroup> listByParams(Map params) {
        return userGroupMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<UserGroup> listUserGroupInsert) {
        return userGroupMapper.batchInsert(listUserGroupInsert);
    }

    @Override
    public int batchUpdate(List<UserGroup> listUserGroupUpdate) {
        return userGroupMapper.batchUpdate(listUserGroupUpdate);
    }
    
    @Override
    public int batchReplace(List<UserGroup> listUserGroupReplace) {
        return userGroupMapper.batchReplace(listUserGroupReplace);
    }

	@Override
	public int batchDelete(String[] delIds) {
		return userGroupMapper.batchDelete(delIds);
	}
}