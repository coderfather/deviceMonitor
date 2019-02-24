package com.deviceMonitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deviceMonitor.constant.Const;
import com.deviceMonitor.intf.service.IChannelUserService;
import com.deviceMonitor.model.ResultMessage;
import com.deviceMonitor.model.ChannelUser;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/channelUser")
public class ChannelUserController extends BaseController {
	@Autowired
	private IChannelUserService channelUserService;

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		ListRange listRange = new ListRange();
		listRange.setStart(start);
		listRange.setLimit(limit);
		channelUserService.list(listRange);
		printWriterFromObject(response, listRange);
	}

	@RequestMapping("/isExists")
	public void isExists(HttpServletRequest request, HttpServletResponse response) {
		String key = StringUtil.strRemoveNullAndBlank(request.getParameter("key"));
		String id = StringUtil.strRemoveNullAndBlank(request.getParameter("id"));
		Map params = new HashMap();
		params.put("key", key);
		params.put("id", id);
		int result = channelUserService.isExists(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/insert")
	public void insert(@ModelAttribute(value = "channelUser") ChannelUser channelUser,
			HttpServletRequest request, HttpServletResponse response) {
		int result = channelUserService.insert(channelUser);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "新增成功。" : "新增失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/update")
	public void update(@ModelAttribute(value = "channelUser") ChannelUser channelUser,
			HttpServletRequest request, HttpServletResponse response) {
		int result = channelUserService.update(channelUser);
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
		int result = channelUserService.batchDelete(arrLongIds);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		resMsg.setMessage(result == 1 ? "删除成功。" : "删除失败。");
		printWriterFromObject(response, resMsg);
	}
}