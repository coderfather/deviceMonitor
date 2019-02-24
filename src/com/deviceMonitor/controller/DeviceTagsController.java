package com.deviceMonitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deviceMonitor.constant.Const;
import com.deviceMonitor.intf.service.IDeviceParamService;
import com.deviceMonitor.intf.service.IDeviceTagsService;
import com.deviceMonitor.intf.service.IUserService;
import com.deviceMonitor.model.DeviceTags;
import com.deviceMonitor.model.ResultMessage;
import com.deviceMonitor.model.User;
import com.deviceMonitor.model.syn.DeviceTagsView;
import com.deviceMonitor.util.JacksonUtil;
import com.deviceMonitor.util.ListRangeEx;
import com.deviceMonitor.util.SocketClientUtil;
import com.deviceMonitor.util.StringUtil;

@Controller
@RequestMapping("/deviceTags")
public class DeviceTagsController extends BaseController {
	@Autowired
	private IDeviceTagsService deviceTagsService;
	
	@Autowired
	private IDeviceParamService deviceParamService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response) {
        // 注册号
        String registerNumber = StringUtil.strRemoveNullAndBlank(request.getParameter("registerNumber"));
        // 修改的通道数据
		String jsonStr = StringUtil.strRemoveNullAndBlank(request.getParameter("data"));
        List<DeviceTagsView> listChangedDeviceTagsView = JacksonUtil.json2List(jsonStr, DeviceTagsView.class);

        // 现场设备的版本号
        int resolutionVer = 0;
		Map params = new HashMap();
	    params.put("registerNumber", registerNumber);
	    List<DeviceTags> listAllDeviceTags = deviceTagsService.listByParams(params);
        
        if (!CollectionUtils.isEmpty(listAllDeviceTags)) {
            resolutionVer = listAllDeviceTags.get(0).getResolution_ver();
        }
        
        List<Long> listDeviceUserId = new ArrayList<Long>();
	    int result = deviceParamService.save(registerNumber, resolutionVer, listChangedDeviceTagsView, listDeviceUserId);
	    if (result >= 1) {
	    	List<User> listUser = userService.listByDeviceUserIds(listDeviceUserId);
	    	String sJsonData = "";
	    	for (User tmpUser : listUser) {
	    		sJsonData += tmpUser.getLogin_name() + ",";
	    	}
	    	
	    	if (sJsonData.endsWith(",")) {
	    		sJsonData = sJsonData.substring(0, sJsonData.length());
	    		sJsonData += "\r\n";
	    	}
	    	
	    	if (StringUtil.isNotBlank(sJsonData)) {
	    		new SocketClientUtil("127.0.0.1", 7000, sJsonData).sendMsg();
	    	}
	    	/**
	    	ExecutorService executor = Executors.newFixedThreadPool(1);
	    	executor.execute(new SocketClientUtil("127.0.0.1", 7000, sJsonData));
	    	
	    	// 等待线程结束并处理异常
	        executor.shutdown();
	        try {
	            executor.awaitTermination(30000, TimeUnit.SECONDS);
	        } catch (InterruptedException e) {
	            LOGGER.error("7000端口调用失败", e);
	        }
	        **/
	    }
	    
	    ResultMessage resMsg = new ResultMessage();
        resMsg.setSuccess(result >= 1);
        resMsg.setResult(result >= 1);
        resMsg.setMessage(result >= 1 ? "设置成功。" : "设置失败。");
        printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		// 注册号
		String registerNumber = StringUtil.strRemoveNullAndBlank(request.getParameter("registerNumber"));
		// 现场设备的版本号
		int resolutionVer = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("resolutionVer")));
		
		ListRangeEx listRangeEx = new ListRangeEx();
		listRangeEx.setStart(start);
		listRangeEx.setLimit(limit);
		listRangeEx.getParams().put("registerNumber", registerNumber);
		deviceTagsService.list(listRangeEx);
		
		List<DeviceTagsView> listDeviceTagsView = listRangeEx.getList();
		if (!CollectionUtils.isEmpty(listDeviceTagsView)) {
		    int tmpResolutionVer = listDeviceTagsView.get(0).getResolution_ver();
		    if (resolutionVer != tmpResolutionVer) {
		        for (DeviceTagsView tmpDeviceTagsView : listDeviceTagsView) {
		            tmpDeviceTagsView.setEditable(true);
                }
		    }
		}
		
		printWriterFromObject(response, listRangeEx);
	}

	@RequestMapping("/isExists")
	public void isExists(HttpServletRequest request, HttpServletResponse response) {
		String key = StringUtil.strRemoveNullAndBlank(request.getParameter("key"));
		String id = StringUtil.strRemoveNullAndBlank(request.getParameter("id"));
		Map params = new HashMap();
		params.put("key", key);
		params.put("id", id);
		int result = deviceTagsService.isExists(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/insert")
	public void insert(@ModelAttribute(value = "deviceTags") DeviceTags deviceTags,
			HttpServletRequest request, HttpServletResponse response) {
		int result = deviceTagsService.insert(deviceTags);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "新增成功。" : "新增失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/update")
	public void update(@ModelAttribute(value = "deviceTags") DeviceTags deviceTags,
			HttpServletRequest request, HttpServletResponse response) {
		int result = deviceTagsService.update(deviceTags);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "更新成功。" : "更新失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String delIds = StringUtil.strRemoveNullAndBlank(request.getParameter("delIds"));
		int result = deviceTagsService.batchDelete(delIds.split(Const.SPLIT));
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		resMsg.setMessage(result == 1 ? "删除成功。" : "删除失败。");
		printWriterFromObject(response, resMsg);
	}
}