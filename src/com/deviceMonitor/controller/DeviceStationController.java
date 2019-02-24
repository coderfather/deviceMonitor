package com.deviceMonitor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.deviceMonitor.constant.Const;
import com.deviceMonitor.intf.service.IDeviceStationService;
import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.model.ResultMessage;
import com.deviceMonitor.util.FileUtil;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.ReadExcelUtils;
import com.deviceMonitor.util.StringUtil;

@Controller
@RequestMapping("/deviceStation")
public class DeviceStationController extends BaseController {
	@Autowired
	private IDeviceStationService deviceStationService;

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		ListRange listRange = new ListRange();
		listRange.setStart(start);
		listRange.setLimit(limit);
		deviceStationService.list(listRange);
		printWriterFromObject(response, listRange);
	}

	@RequestMapping("/isExists")
	public void isExists(HttpServletRequest request, HttpServletResponse response) {
		String key = StringUtil.strRemoveNullAndBlank(request.getParameter("key"));
		String id = StringUtil.strRemoveNullAndBlank(request.getParameter("id"));
		Map params = new HashMap();
		params.put("key", key);
		params.put("id", id);
		int result = deviceStationService.isExists(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}
	
	@RequestMapping("/isExistsDevice")
	public void isExistsDevice(HttpServletRequest request, HttpServletResponse response) {
		String key = StringUtil.strRemoveNullAndBlank(request.getParameter("key"));
		String id = StringUtil.strRemoveNullAndBlank(request.getParameter("id"));
		Map params = new HashMap();
		params.put("key", key);
		params.put("id", id);
		int result = deviceStationService.isExistsDevice(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/insert")
	public void insert(@ModelAttribute(value = "deviceStation") DeviceStation deviceStation,
			HttpServletRequest request, HttpServletResponse response) {
		int result = deviceStationService.insert(deviceStation);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "新增成功。" : "新增失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/update")
	public void update(@ModelAttribute(value = "deviceStation") DeviceStation deviceStation,
			HttpServletRequest request, HttpServletResponse response) {
		int result = deviceStationService.update(deviceStation);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "更新成功。" : "更新失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String delIds = StringUtil.strRemoveNullAndBlank(request.getParameter("delIds"));
		int result = deviceStationService.batchDelete(delIds.split(Const.SPLIT));
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		resMsg.setMessage(result == 1 ? "删除成功。" : "删除失败。");
		printWriterFromObject(response, resMsg);
	}
	
	@RequestMapping("/tempDownload")
	public void tempDownload(HttpServletRequest request, HttpServletResponse response) {
		String base = request.getRealPath("/");  
		base = request.getContextPath();
		String fullPath = request.getServerName() + ":8090" + base;
		
		String filePath = fullPath + "/resources/file/temp.xls";
			
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(true);
		resMsg.setResult(true);
		resMsg.setMessage(filePath);
		printWriterFromObject(response, resMsg);
		
		// super.writeToResponse(response, filePath, "注册点信息模板");
	}
	
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String retMsgKey = "msg";
        String retMsg = null;
        
		LOGGER.info("synUp file begin ...................");
        if (!(request instanceof MultipartHttpServletRequest)) {
        	LOGGER.error("synUp no file ...................");
            retMsg = "导入类型不是文件，请您检查选择的文件是否正确。";
            model.addAttribute(retMsgKey, retMsg);
            return;
        }
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        
        String fileColName = "fileData";
        MultipartFile file = multipartRequest.getFile(fileColName);
        if (file == null || file.isEmpty()) {
        	LOGGER.error("synUp no file ..................." + fileColName);
            retMsg = "导入文件是空文件，请您检查选择的文件是否正确。";
            model.addAttribute(retMsgKey, retMsg);
            return;
        }
        

        // 导入Excel文件
        List<Map<String, Object>> excelResults = null;
        try {
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
                // 文件格式不正确
                retMsg = "文件格式不正确，请您检查选择的文件是否正确。";
                model.addAttribute(retMsgKey, retMsg);
                return;
            }

            excelResults = ReadExcelUtils.readFile(file.getOriginalFilename(),
                    file.getInputStream());
            if (CollectionUtils.isEmpty(excelResults)) {
            	model.addAttribute(retMsgKey, "excel 没有数据。");
            	return;
            }
            
            List<DeviceStation> listTransDetail = convertObjFromExcel(excelResults);
            int result = deviceStationService.batchInsert(listTransDetail);
            if (result == -1) {
            	retMsg = "没有数据需要上传。";
            } else {
            	retMsg = (result == 1) ? "上传成功" : "上传失败";
            }
            
            model.addAttribute(retMsgKey, retMsg);
        } catch (Exception e) {
        	e.printStackTrace();
        	retMsg = "导入失败：" + e.getMessage();
            model.addAttribute(retMsgKey, retMsg);
        	LOGGER.error(retMsg);
        }
	}

	private List<DeviceStation> convertObjFromExcel(
			List<Map<String, Object>> excelResults) {
		if (CollectionUtils.isEmpty(excelResults)) {
        	return null;
        }
		
		List<DeviceStation> listDeviceStation = new ArrayList<DeviceStation>();
		DeviceStation deviceStation = null;
		loop1:
		for (int i = 0; i < excelResults.size(); i++) {
			deviceStation = new DeviceStation();
			deviceStation.setRegister_number(StringUtil.strRemoveNull(excelResults.get(i).get(ReadExcelUtils.LINE_01)));
			deviceStation.setMax_comm_volume(StringUtil.str2Integer(StringUtil.strRemoveNull(excelResults.get(i).get(ReadExcelUtils.LINE_02))));
			deviceStation.setMax_table_volume(StringUtil.str2Integer(StringUtil.strRemoveNull(excelResults.get(i).get(ReadExcelUtils.LINE_03))));
			
			listDeviceStation.add(deviceStation);
		}
		return listDeviceStation;
	}
}