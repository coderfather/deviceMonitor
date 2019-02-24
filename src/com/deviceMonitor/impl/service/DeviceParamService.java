package com.deviceMonitor.impl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.deviceMonitor.intf.dao.ChannelUserMapper;
import com.deviceMonitor.intf.dao.DeviceParamMapper;
import com.deviceMonitor.intf.dao.DeviceStationMapper;
import com.deviceMonitor.intf.dao.DeviceTagsMapper;
import com.deviceMonitor.intf.dao.DeviceUserMapper;
import com.deviceMonitor.intf.service.IDeviceParamService;
import com.deviceMonitor.model.ChannelUser;
import com.deviceMonitor.model.DeviceParam;
import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.model.DeviceTags;
import com.deviceMonitor.model.syn.DeviceTagsView;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.StringUtil;

@Service
public class DeviceParamService implements IDeviceParamService {
	@Autowired
	private DeviceStationMapper deviceStationMapper;
	
	@Autowired
	private DeviceParamMapper deviceParamMapper;
	
	@Autowired
	private DeviceTagsMapper deviceTagsMapper;
	
	@Autowired
	private ChannelUserMapper channelUserMapper;
	
	@Autowired
	private DeviceUserMapper deviceUserMapper;
	
	@Transactional
    @Override
    public int save(String registerNumber, int resolutionVer, List<DeviceTagsView> listChangedDeviceTagsView, List<Long> listDeviceUserId) {
		int result = -1;
		// 更新注册点的现场设备的版本号
		Map params = new HashMap();
	    params.put("registerNumber", registerNumber);
	    List<DeviceStation> listDeviceStation = deviceStationMapper.listByParams(params);
	    long deviceStationId = listDeviceStation.get(0).getId();
	    result = deviceStationMapper.updateVer(registerNumber, resolutionVer);
	    
	    List<DeviceTags> listAllDeviceTags = deviceTagsMapper.listByParams(params);
	    
	    // 删除掉变化的和多余的通道信息
	    List<Long> listDeleteTagId = new ArrayList<Long>();    // 所有变化的通道名称
	    if (!CollectionUtils.isEmpty(listChangedDeviceTagsView)) {
		    for (DeviceTagsView tmpDeviceTagsView : listChangedDeviceTagsView) {
		        listDeleteTagId.add(tmpDeviceTagsView.getTagId());
	        }
	    }
	    
	    List<DeviceParam> listDeviceParam = deviceParamMapper.listByParams(params);
	    if (!CollectionUtils.isEmpty(listDeviceParam)) {
	    	loop1:
	    	for (DeviceParam tmpDeviceParam : listDeviceParam) {
	    		String tmpTagName = tmpDeviceParam.getTag_name();
	    		
	    		for (DeviceTags tmpDeviceTags : listAllDeviceTags) {
					if (tmpTagName.equals(tmpDeviceTags.getTag_name())) {
						continue loop1;
					}
				}
	    		
	    		listDeleteTagId.add(tmpDeviceParam.getId());
			}
	    }
	    
	    // 将受影响的设备_用户权限管理表的设备解析的版本号+1，删除通道_用户权限管理表的数据
	    if (!CollectionUtils.isEmpty(listDeleteTagId)) {
		    List<ChannelUser> listChannelUser = channelUserMapper.listAllByChannelIds(listDeleteTagId);
		    if (!CollectionUtils.isEmpty(listChannelUser)) {
		    	
		    	for (ChannelUser tmpChannelUser : listChannelUser) {
		    		listDeviceUserId.add(tmpChannelUser.getDevice_user_id());
				}
		    	
		    	deviceUserMapper.batchUpdateVer(listDeviceUserId);
		    	
		    	channelUserMapper.batchDelete(listDeleteTagId.toArray(new Long[listDeleteTagId.size()]));
		    }
		    
		    deviceParamMapper.batchDelete(listDeleteTagId.toArray(new Long[listDeleteTagId.size()]));
	    }
	    
	    // 新增变化的和没有的通道信息
	    List<DeviceParam> listDeviceParamInsert = new ArrayList<DeviceParam>();
	    loop1:
	    for (DeviceTags tmpDeviceTags : listAllDeviceTags) {
	    	String tmpTagName = tmpDeviceTags.getTag_name();
	    	
	    	for (DeviceParam tmpDeviceParam : listDeviceParam) {
	    		if (tmpTagName.equals(tmpDeviceParam.getTag_name())) {
	    			continue loop1;
	    		}
			}
	    	
	    	DeviceParam deviceParam = new DeviceParam();
	    	deviceParam.setDevice_station_id(deviceStationId);
	    	deviceParam.setRegister_number(tmpDeviceTags.getRegister_number());
	    	deviceParam.setDevice_code(tmpDeviceTags.getDevice_code());
	    	deviceParam.setTag_name(tmpDeviceTags.getTag_name());
	    	deviceParam.setLimit_set(StringUtil.integer2Str(tmpDeviceTags.getRw_flag()));
	    	deviceParam.setData_type(StringUtil.integer2Str(tmpDeviceTags.getData_type()));
	    	
			listDeviceParamInsert.add(deviceParam);
		}
	    
	    if (!CollectionUtils.isEmpty(listChangedDeviceTagsView)) {
		    for (DeviceTagsView tmpDeviceTagsView : listChangedDeviceTagsView) {
		    	DeviceParam deviceParam = new DeviceParam();
		    	deviceParam.setDevice_station_id(deviceStationId);
		    	deviceParam.setRegister_number(tmpDeviceTagsView.getRegister_number());
		    	deviceParam.setDevice_code(tmpDeviceTagsView.getDevice_code());
		    	deviceParam.setTag_name(tmpDeviceTagsView.getTag_name());
		    	deviceParam.setLimit_set(StringUtil.integer2Str(tmpDeviceTagsView.getRw_flag()));
		    	deviceParam.setData_type(StringUtil.integer2Str(tmpDeviceTagsView.getData_type()));
		    	deviceParam.setDisplay_name(tmpDeviceTagsView.getDisplay_name());
		    	deviceParam.setDisplay_value(tmpDeviceTagsView.getDisplay_value());
		    	deviceParam.setDisplay_format(tmpDeviceTagsView.getDisplay_format());
		    	deviceParam.setDisplay_remark(tmpDeviceTagsView.getDisplay_remark());
		    	
				listDeviceParamInsert.add(deviceParam);
			}
	    }
	    
	    if (!CollectionUtils.isEmpty(listDeviceParamInsert)) {
	    	result = deviceParamMapper.batchInsert(listDeviceParamInsert);
	    }
        
		return result;
    }
	
	@Override
	public int insert(DeviceParam deviceParam) {
		return deviceParamMapper.insert(deviceParam);
	}

	@Override
	public int update(DeviceParam deviceParam) {
		return deviceParamMapper.update(deviceParam);
	}

	@Override
	public int replace(DeviceParam deviceParam) {
		return deviceParamMapper.replace(deviceParam);
	}

	@Override
	public int delete(String id) {
		return deviceParamMapper.delete(id);
	}

	@Override
	public DeviceParam query(String id) {
		return deviceParamMapper.query(id);
	}

	@Override
	public void list(ListRange listRange) {
		Integer totalSize = deviceParamMapper.listAllRecordsCount();
		if (listRange.getLimit() == 0) {
			listRange.setLimit(totalSize);
		}
		List<DeviceParam> list = deviceParamMapper.list(listRange.getStart(), listRange.getLimit());
		listRange.setList(list);
		listRange.setTotalSize(totalSize);
		listRange.setSuccess(true);
	}

    @Override
    public List<DeviceParam> listAll() {
        return deviceParamMapper.listAll();
    }
    
    @Override
    public List<DeviceParam> list(int start, int limit) {
        return deviceParamMapper.list(start, limit);
    }

    @Override
    public int listAllRecordsCount() {
        return deviceParamMapper.listAllRecordsCount();
    }

	@Override
	public int isExists(Map params) {
		return deviceParamMapper.isExists(params);
	}
    
    @Override
    public List<DeviceParam> listByParams(Map params) {
        return deviceParamMapper.listByParams(params);
    }
    
    @Override
    public int batchInsert(List<DeviceParam> listDeviceParamInsert) {
        return deviceParamMapper.batchInsert(listDeviceParamInsert);
    }

    @Override
    public int batchUpdate(List<DeviceParam> listDeviceParamUpdate) {
        return deviceParamMapper.batchUpdate(listDeviceParamUpdate);
    }
    
    @Override
    public int batchReplace(List<DeviceParam> listDeviceParamReplace) {
        return deviceParamMapper.batchReplace(listDeviceParamReplace);
    }

	@Override
	public int batchDelete(Long[] delIds) {
		return deviceParamMapper.batchDelete(delIds);
	}
}