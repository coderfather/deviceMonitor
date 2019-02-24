package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.deviceMonitor.intf.dao.DeviceStationMapper;
import com.deviceMonitor.intf.service.IDeviceStationService;
import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.util.ListRange;

@Service
public class DeviceStationService implements IDeviceStationService {
	@Autowired
	private DeviceStationMapper deviceStationMapper;
	
	@Override
	public int insert(DeviceStation deviceStation) {
		return deviceStationMapper.insert(deviceStation);
	}

	@Override
	public int update(DeviceStation deviceStation) {
		return deviceStationMapper.update(deviceStation);
	}

	@Override
	public int replace(DeviceStation deviceStation) {
		return deviceStationMapper.replace(deviceStation);
	}

	@Override
	public int delete(String id) {
		return deviceStationMapper.delete(id);
	}

	@Override
	public DeviceStation query(String id) {
		return deviceStationMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = deviceStationMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<DeviceStation> list = deviceStationMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<DeviceStation> listAll() {
        return deviceStationMapper.listAll();
    }
    
    @Override
    public List<DeviceStation> list(int start, int limit) {
        return deviceStationMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return deviceStationMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return deviceStationMapper.isExists(params);
	}
	
	@Override
	public int isExistsDevice(Map params) {
		return deviceStationMapper.isExistsDevice(params);
	}
    
    @Override
    public List<DeviceStation> listByParams(Map params) {
        return deviceStationMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<DeviceStation> listDeviceStationInsert) {
        return deviceStationMapper.batchInsert(listDeviceStationInsert);
    }

    @Override
    @Transactional
    public boolean batchUpdate(List<DeviceStation> listDeviceStationUpdate) {
    	boolean isOK = true;
    	if (!CollectionUtils.isEmpty(listDeviceStationUpdate)) {
    		for (DeviceStation tmpDeviceStation : listDeviceStationUpdate) {
    			int count = deviceStationMapper.update(tmpDeviceStation);
    			if (count == 0) {
    				isOK = false;
    				break;
    			}
			}
    	}
        return isOK;
    }
    
    @Override
    public int batchReplace(List<DeviceStation> listDeviceStationReplace) {
        return deviceStationMapper.batchReplace(listDeviceStationReplace);
    }

	@Override
	public int batchDelete(String[] delIds) {
		return deviceStationMapper.batchDelete(delIds);
	}
}