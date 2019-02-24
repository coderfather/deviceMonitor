package com.deviceMonitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deviceMonitor.constant.Const;
import com.deviceMonitor.intf.service.IDeviceParamService;
import com.deviceMonitor.model.ResultMessage;
import com.deviceMonitor.model.DeviceParam;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.ListRangeEx;
import com.deviceMonitor.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/deviceParam")
public class DeviceParamController extends BaseController {
	@Autowired
	private IDeviceParamService deviceParamService;

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		ListRange listRange = new ListRange();
		listRange.setStart(start);
		listRange.setLimit(limit);
		deviceParamService.list(listRange);
		printWriterFromObject(response, listRange);
	}
	
	@RequestMapping("/listByDevice")
	public void listByDevice(HttpServletRequest request, HttpServletResponse response) {
		String registerNumber = StringUtil.strRemoveNullAndBlank(request.getParameter("registerNumber"));
		ListRangeEx listRangeEx = new ListRangeEx();
		Map params = new HashMap();
		params.put("registerNumber", registerNumber);
		listRangeEx.setParams(params);
		List<DeviceParam> list = deviceParamService.listByParams(params);
		listRangeEx.setList(list);
		listRangeEx.setSuccess(true);
		listRangeEx.setTotalSize(list.size());
		printWriterFromObject(response, listRangeEx);
	}

	@RequestMapping("/isExists")
	public void isExists(HttpServletRequest request, HttpServletResponse response) {
		String key = StringUtil.strRemoveNullAndBlank(request.getParameter("key"));
		String id = StringUtil.strRemoveNullAndBlank(request.getParameter("id"));
		Map params = new HashMap();
		params.put("key", key);
		params.put("id", id);
		int result = deviceParamService.isExists(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/insert")
	public void insert(@ModelAttribute(value = "deviceParam") DeviceParam deviceParam,
			HttpServletRequest request, HttpServletResponse response) {
		int result = deviceParamService.insert(deviceParam);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "新增成功。" : "新增失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/update")
	public void update(@ModelAttribute(value = "deviceParam") DeviceParam deviceParam,
			HttpServletRequest request, HttpServletResponse response) {
		int result = deviceParamService.update(deviceParam);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "更新成功。" : "更新失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String delIds = StringUtil.strRemoveNullAndBlank(request.getParameter("delIds"));
		String[] arrStrIds = delIds.split(Const.SPLIT);
		Long[] arrLongIds = new Long[arrStrIds.length];
		for (int i = 0; i < arrStrIds.length; i++) {
			arrLongIds[i] = StringUtil.str2Long(arrStrIds[i]);
		}
		int result = deviceParamService.batchDelete(arrLongIds);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		resMsg.setMessage(result == 1 ? "删除成功。" : "删除失败。");
		printWriterFromObject(response, resMsg);
	}
}