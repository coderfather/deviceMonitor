package com.deviceMonitor.impl.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deviceMonitor.intf.dao.DeviceTagsMapper;
import com.deviceMonitor.intf.service.IDeviceTagsService;
import com.deviceMonitor.model.DeviceTags;
import com.deviceMonitor.model.syn.DeviceTagsView;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.ListRangeEx;

@Service
public class DeviceTagsService implements IDeviceTagsService {
	@Autowired
	private DeviceTagsMapper deviceTagsMapper;
	
	@Override
	public int insert(DeviceTags deviceTags) {
		return deviceTagsMapper.insert(deviceTags);
	}

	@Override
	public int update(DeviceTags deviceTags) {
		return deviceTagsMapper.update(deviceTags);
	}

	@Override
	public int replace(DeviceTags deviceTags) {
		return deviceTagsMapper.replace(deviceTags);
	}

	@Override
	public int delete(String id) {
		return deviceTagsMapper.delete(id);
	}

	@Override
	public DeviceTags query(String id) {
		return deviceTagsMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = deviceTagsMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<DeviceTags> list = deviceTagsMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}
	
	@Override
	public void list(ListRangeEx listRangeEx) {
		Integer totalSize = deviceTagsMapper.listAllRecordsCount(listRangeEx);
		if (listRangeEx.getLimit() == 0) {
			listRangeEx.setLimit(totalSize);
		}
		List<DeviceTagsView> list = deviceTagsMapper.list(listRangeEx);
		listRangeEx.setList(list);
		listRangeEx.setTotalSize(totalSize);
		listRangeEx.setSuccess(true);
	}

	@Override
	public int listAllRecordsCount(ListRangeEx listRangeEx) {
		return deviceTagsMapper.listAllRecordsCount(listRangeEx);
	}

    @Override
    public List<DeviceTags> listAll() {
        return deviceTagsMapper.listAll();
    }
    
    @Override
    public List<DeviceTags> list(int start, int limit) {
        return deviceTagsMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return deviceTagsMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return deviceTagsMapper.isExists(params);
	}
    
    @Override
    public List<DeviceTags> listByParams(Map params) {
        return deviceTagsMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<DeviceTags> listDeviceTagsInsert) {
        return deviceTagsMapper.batchInsert(listDeviceTagsInsert);
    }

    @Override
    public int batchUpdate(List<DeviceTags> listDeviceTagsUpdate) {
        return deviceTagsMapper.batchUpdate(listDeviceTagsUpdate);
    }
    
    @Override
    public int batchReplace(List<DeviceTags> listDeviceTagsReplace) {
        return deviceTagsMapper.batchReplace(listDeviceTagsReplace);
    }

	@Override
	public int batchDelete(String[] delIds) {
		return deviceTagsMapper.batchDelete(delIds);
	}
}