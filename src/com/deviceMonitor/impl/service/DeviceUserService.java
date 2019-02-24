package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deviceMonitor.intf.dao.DeviceStationMapper;
import com.deviceMonitor.intf.dao.DeviceUserMapper;
import com.deviceMonitor.intf.service.IDeviceUserService;
import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.model.DeviceUser;
import com.deviceMonitor.util.ListRange;

@Service
public class DeviceUserService implements IDeviceUserService {
	@Autowired
	private DeviceUserMapper deviceUserMapper;
	
	@Autowired
	private DeviceStationMapper deviceStationMapper;
	
	@Transactional
	@Override
	public int insert(DeviceUser deviceUser, DeviceStation deviceStation) {
		int result = deviceStationMapper.updateDevice(deviceStation);
		if (result == 1) {
			result = deviceUserMapper.insert(deviceUser);
		}
		
		return result;
	}

	@Transactional
	@Override
	public int update(DeviceUser deviceUser, DeviceStation deviceStation) {
		int result = deviceStationMapper.updateDevice(deviceStation);
		if (result == 1) {
			result = deviceUserMapper.update(deviceUser);
		}
		
		return result;
	}

	@Override
	public int replace(DeviceUser deviceUser) {
		return deviceUserMapper.replace(deviceUser);
	}

	@Override
	public int delete(String id) {
		return deviceUserMapper.delete(id);
	}

	@Override
	public DeviceUser query(String id) {
		return deviceUserMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = deviceUserMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<DeviceUser> list = deviceUserMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<DeviceUser> listAll() {
        return deviceUserMapper.listAll();
    }
    
    @Override
    public List<DeviceUser> list(int start, int limit) {
        return deviceUserMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return deviceUserMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return deviceUserMapper.isExists(params);
	}
    
    @Override
    public List<DeviceUser> listByParams(Map params) {
        return deviceUserMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<DeviceUser> listDeviceUserInsert) {
        return deviceUserMapper.batchInsert(listDeviceUserInsert);
    }

    @Override
    public int batchUpdate(List<DeviceUser> listDeviceUserUpdate) {
        return deviceUserMapper.batchUpdate(listDeviceUserUpdate);
    }
    
    @Override
    public int batchReplace(List<DeviceUser> listDeviceUserReplace) {
        return deviceUserMapper.batchReplace(listDeviceUserReplace);
    }

	@Override
	public int batchDelete(String[] delIds) {
		return deviceUserMapper.batchDelete(delIds);
	}

	@Override
	public DeviceUser queryByParams(Map params) {
		return deviceUserMapper.queryByParams(params);
	}
}