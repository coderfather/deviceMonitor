package com.deviceMonitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deviceMonitor.constant.Const;
import com.deviceMonitor.intf.service.IDeviceUserService;
import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.model.ResultMessage;
import com.deviceMonitor.model.DeviceUser;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/deviceUser")
public class DeviceUserController extends BaseController {
	@Autowired
	private IDeviceUserService deviceUserService;

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		ListRange listRange = new ListRange();
		listRange.setStart(start);
		listRange.setLimit(limit);
		deviceUserService.list(listRange);
		printWriterFromObject(response, listRange);
	}

	@RequestMapping("/isExists")
	public void isExists(HttpServletRequest request, HttpServletResponse response) {
		String key = StringUtil.strRemoveNullAndBlank(request.getParameter("key"));
		String id = StringUtil.strRemoveNullAndBlank(request.getParameter("id"));
		Map params = new HashMap();
		params.put("key", key);
		params.put("id", id);
		int result = deviceUserService.isExists(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/insert")
	public void insert(@ModelAttribute(value = "deviceUser") DeviceUser deviceUser,
			HttpServletRequest request, HttpServletResponse response) {
		String deviceCode = StringUtil.strRemoveNullAndBlank(request.getParameter("device_code"));
		String deviceName = StringUtil.strRemoveNullAndBlank(request.getParameter("device_name"));
		String group3Id = StringUtil.strRemoveNullAndBlank(request.getParameter("group3_id"));
		DeviceStation deviceStation = new DeviceStation();
		deviceStation.setId(deviceUser.getDevice_station_id());
		deviceStation.setDevice_code(deviceCode);
		deviceStation.setDevice_name(deviceName);
		deviceStation.setGroup3_id(group3Id);
		int result = deviceUserService.insert(deviceUser, deviceStation);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "新增成功。" : "新增失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/update")
	public void update(@ModelAttribute(value = "deviceUser") DeviceUser deviceUser,
			HttpServletRequest request, HttpServletResponse response) {
		String deviceCode = StringUtil.strRemoveNullAndBlank(request.getParameter("device_code"));
		String deviceName = StringUtil.strRemoveNullAndBlank(request.getParameter("device_name"));
		String group3Id = StringUtil.strRemoveNullAndBlank(request.getParameter("group3_id"));
		DeviceStation deviceStation = new DeviceStation();
		deviceStation.setId(deviceUser.getDevice_station_id());
		deviceStation.setDevice_code(deviceCode);
		deviceStation.setDevice_name(deviceName);
		deviceStation.setGroup3_id(group3Id);
		int result = deviceUserService.update(deviceUser, deviceStation);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "更新成功。" : "更新失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String delIds = StringUtil.strRemoveNullAndBlank(request.getParameter("delIds"));
		int result = deviceUserService.batchDelete(delIds.split(Const.SPLIT));
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		resMsg.setMessage(result == 1 ? "删除成功。" : "删除失败。");
		printWriterFromObject(response, resMsg);
	}
}