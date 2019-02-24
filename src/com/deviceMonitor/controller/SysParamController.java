package com.deviceMonitor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deviceMonitor.constant.Const;
import com.deviceMonitor.intf.service.ISysParamService;
import com.deviceMonitor.model.ResultMessage;
import com.deviceMonitor.model.SysParam;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.ListRangeEx;
import com.deviceMonitor.util.StringUtil;

@Controller
@RequestMapping("/sysParam")
public class SysParamController extends BaseController {
	@Autowired
	private ISysParamService sysParamService;

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		ListRange listRange = new ListRange();
		listRange.setStart(start);
		listRange.setLimit(limit);
		sysParamService.list(listRange);
		printWriterFromObject(response, listRange);
	}
	
	@RequestMapping("/listByType")
	public void listByType(HttpServletRequest request, HttpServletResponse response) {
		String type = StringUtil.strRemoveNullAndBlank(request.getParameter("type"));
		ListRangeEx listRangeEx = new ListRangeEx();
		Map params = new HashMap();
		params.put("type", type);
		listRangeEx.setParams(params);
		List<SysParam> list = sysParamService.listByParams(params);
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
		int result = sysParamService.isExists(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/insert")
	public void insert(@ModelAttribute(value = "sysParam") SysParam sysParam,
			HttpServletRequest request, HttpServletResponse response) {
		int result = sysParamService.insert(sysParam);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "新增成功。" : "新增失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/update")
	public void update(@ModelAttribute(value = "sysParam") SysParam sysParam,
			HttpServletRequest request, HttpServletResponse response) {
		int result = sysParamService.update(sysParam);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "更新成功。" : "更新失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String delIds = StringUtil.strRemoveNullAndBlank(request.getParameter("delIds"));
		int result = sysParamService.batchDelete(delIds.split(Const.SPLIT));
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		resMsg.setMessage(result == 1 ? "删除成功。" : "删除失败。");
		printWriterFromObject(response, resMsg);
	}
}