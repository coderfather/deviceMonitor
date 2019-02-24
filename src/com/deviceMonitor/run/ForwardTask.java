package com.deviceMonitor.run;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.deviceMonitor.constant.ActionEnum;
import com.deviceMonitor.constant.Const;
import com.deviceMonitor.constant.ResultEnum;
import com.deviceMonitor.impl.service.RemoveVerCodeService;
import com.deviceMonitor.intf.service.IChannelUserService;
import com.deviceMonitor.intf.service.IDeviceParamService;
import com.deviceMonitor.intf.service.IDeviceStationService;
import com.deviceMonitor.intf.service.IDeviceUserService;
import com.deviceMonitor.intf.service.IUserQueueService;
import com.deviceMonitor.intf.service.IUserService;
import com.deviceMonitor.model.ChannelUser;
import com.deviceMonitor.model.DeviceParam;
import com.deviceMonitor.model.DeviceStation;
import com.deviceMonitor.model.DeviceUser;
import com.deviceMonitor.model.UserQueue;
import com.deviceMonitor.model.VerificationCode;
import com.deviceMonitor.model.syn.ChannelDTO;
import com.deviceMonitor.model.syn.ChannelDetailDTO;
import com.deviceMonitor.model.syn.ChannelInputDTO;
import com.deviceMonitor.model.syn.ChannelNewOutputDTO;
import com.deviceMonitor.model.syn.ChannelOutputDTO;
import com.deviceMonitor.model.syn.ForgetPwd1InputDTO;
import com.deviceMonitor.model.syn.ForgetPwd2InputDTO;
import com.deviceMonitor.model.syn.LoginSynResultMessage;
import com.deviceMonitor.model.syn.LoginUser;
import com.deviceMonitor.model.syn.NewUser;
import com.deviceMonitor.model.syn.SMSVerCodeInputDTO;
import com.deviceMonitor.model.syn.SMSVerCodeOutputDTO;
import com.deviceMonitor.model.syn.StationInfoReadInputDTO;
import com.deviceMonitor.model.syn.StationInfoReadOutputDTO;
import com.deviceMonitor.model.syn.StationInfoSaveInputDTO;
import com.deviceMonitor.model.syn.StationQueueSaveInputDTO;
import com.deviceMonitor.model.syn.SynBase;
import com.deviceMonitor.model.syn.SynDeviceStation;
import com.deviceMonitor.model.syn.User;
import com.deviceMonitor.util.JacksonUtil;
import com.deviceMonitor.util.Md5;
import com.deviceMonitor.util.MessageUtil;
import com.deviceMonitor.util.StringUtil;

/**
 * 服务器端进行消息转发的Task
 * 
 */
public class ForwardTask extends Task {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ForwardTask.class);
	
	private IUserService userService;
	private IDeviceStationService deviceStationService;
	private IDeviceParamService deviceParamService;
	private IChannelUserService channelUserService;
	private IDeviceUserService deviceUserService;
	private IUserQueueService userQueueService;
	
	private void initObject() {
		if (userService == null) {
			userService = BaseServerHandler.getBean(IUserService.class, "userService");
		}
		if (deviceStationService == null) {
			deviceStationService = BaseServerHandler.getBean(IDeviceStationService.class, "deviceStationService");
		}
		if (deviceParamService == null) {
			deviceParamService = BaseServerHandler.getBean(IDeviceParamService.class, "deviceParamService");
		}
		if (channelUserService == null) {
			channelUserService = BaseServerHandler.getBean(IChannelUserService.class, "channelUserService");
		}
		if (deviceUserService == null) {
			deviceUserService = BaseServerHandler.getBean(IDeviceUserService.class, "deviceUserService");
		}
		if (userQueueService == null) {
			userQueueService = BaseServerHandler.getBean(IUserQueueService.class, "userQueueService");
		}
	}
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private boolean onWork = true;

	public ForwardTask(Socket socket) {
		this.socket = socket;

		try {
			dis = new DataInputStream(
					socket.getInputStream());
			dos = new DataOutputStream(
					socket.getOutputStream());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		
		initObject();
	}

	@Override
	public Task[] taskCore() throws Exception {
		return null;
	}

	@Override
	protected boolean useDb() {
		return false;
	}

	@Override
	protected boolean needExecuteImmediate() {
		return false;
	}

	@Override
	public String info() {
		return null;
	}

	/**
	 * 设置线程工作状态，true表示运行，false表示将关闭
	 * 
	 * @param state
	 */
	private void setWorkState(boolean state) {
		onWork = state;
	}

	/**
	 * 任务执行入口
	 */
	public void run() {
		while (onWork) {
			// 读数据
			try {
				receiveMsg();
				setWorkState(false);
			} catch (Exception e) { // 发生异常————通常是连接断开，则跳出循环，一个Task任务执行完毕
				LOGGER.error(e.getMessage());
				break;
			}
		}

		closeStream();
	}
	
	private void closeStream() {
		try {
			if (socket != null)
				socket.close();
			if (dis != null)
				dis.close();
			if (dos != null)
				dos.close();

			socket = null;
			dis = null;
			dos = null;
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}

	// 接收消息
	public void receiveMsg() throws Exception {
		String json = dis.readUTF();
        // 收到消息直接打印输出
		LOGGER.debug("收到消息：" + json);
		
        if (StringUtil.isBlank(json)) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "上传的数据不允许为空，请检查。");
        	return; 
		}
        if (JacksonUtil.isBadJson(json)) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "无效JSON数据，请检查。");
        	return;
        }
        
        Object obj = JacksonUtil.json2Bean(json, Object.class);
        String action = StringUtil.strRemoveNull(((LinkedHashMap) obj).get("action"));
        ActionEnum actionEnum = ActionEnum.getValueName(action);
        
        if (actionEnum == null) {
    		BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, "无效action，请检查。");
    		return;
    	}
        
        switch (actionEnum) {
			case Register:
				register(json);
				break;
			case Modify:
				modify(json);
				break;
			case Login:
				login(json);
				break;
			case Station:
				station(json);
				break;
			case Readver:
				readver(json);
				break;
			case Queue:
				queue(json);
				break;
			case Save:
				save(json);
				break;
			case Readsave:
				readsave(json);
				break;
			case GetSMSVerCode:
				getSMSVerCode(json);
				break;
			case ForgetPwd1:
				forgetPwd1(json);
				break;
			case ForgetPwd2:
				forgetPwd2(json);
				break;
			default:
				break;
		}
    }

	private void forgetPwd1(String json) {
		ForgetPwd1InputDTO inputDTO = JacksonUtil.json2Bean(json, ForgetPwd1InputDTO.class);
		String loginName = inputDTO.getLoginName();
		long phone = inputDTO.getPhone();
		int verCode = inputDTO.getVerCode();
		
		if (StringUtil.isNullOrEmpty(loginName) || String.valueOf(phone).length() != 11 || String.valueOf(verCode).length() != 6) {
			try {
				BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "参数有误。");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		Map params = new HashMap();
		params.put("loginName", loginName);
		params.put("phone", phone);
		List<com.deviceMonitor.model.User> listUser = userService.listByParams(params);
		if (CollectionUtils.isEmpty(listUser)) {
			try {
				BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "用户名和手机号不匹配。");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
    	ResultEnum result = ResultEnum.Fail_negative3;
    	String msg = "";
    	
    	Set<VerificationCode> setVCode = RemoveVerCodeService.getInstance().getSetVCode();
		Iterator<VerificationCode> it = setVCode.iterator();  
		while (it.hasNext()) {
			VerificationCode tbVerificationCode = (VerificationCode) it.next();
			if (phone == tbVerificationCode.getMobileNumber() && 1 == tbVerificationCode.getType()) {
				long timeDiff = Calendar.getInstance().getTimeInMillis() - tbVerificationCode.getCreateTime().getTime();
				boolean isValid = (timeDiff <= 3 * 60 * 1000);
				
				if (isValid && verCode == tbVerificationCode.getVerificationCode()) {
					result = ResultEnum.Success;
				} else if (!isValid) {
					msg = "已过3分钟有效期，请重新获取验证码。";
					it.remove();
				}
				
				break;
			}
		}
		
		try {
			BaseServerHandler.writeSynResultMessage(dos, result, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void forgetPwd2(String json) {
		ForgetPwd2InputDTO inputDTO = JacksonUtil.json2Bean(json, ForgetPwd2InputDTO.class);
		String loginName = inputDTO.getLoginName();
		String newPwd = inputDTO.getNewPwd();
		
		if (StringUtil.isNullOrEmpty(loginName) || StringUtil.isNullOrEmpty(newPwd)) {
			try {
				BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "参数有误。");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		Map params = new HashMap();
		params.put("loginName", loginName);
		List<com.deviceMonitor.model.User> listUser = userService.listByParams(params);
		if (CollectionUtils.isEmpty(listUser)) {
			try {
				BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "找不到用户名。");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		for (com.deviceMonitor.model.User tmpUser : listUser) {
			tmpUser.setPwd(Md5.getMD5Str(newPwd));
		}
		
		int result = userService.batchUpdate(listUser);
		
		try {
			BaseServerHandler.writeSynResultMessage(dos, result > 0 ? ResultEnum.Success : ResultEnum.Fail_negative3,
					result > 0 ? "设置成功。" : "设置失败。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getSMSVerCode(String json) {
		SMSVerCodeInputDTO inoutDTO = JacksonUtil.json2Bean(json, SMSVerCodeInputDTO.class);
		String strPhone = StringUtil.strRemoveNullAndBlank(inoutDTO.getPhone());
		int type = inoutDTO.getType();
		
		if (StringUtil.isBlank(strPhone) || !StringUtil.isNumber(strPhone) || strPhone.length() != 11) {
			try {
				BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "获取验证码失败。");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		String random = MessageUtil.getRandom();
		String templateId = type == 0 ? MessageUtil.REGISTER_TEMPLATE_ID : MessageUtil.FIND_PWD_TEMPLATE_ID;
		String result = MessageUtil.testTemplateSMS(true, random, templateId, strPhone);
		
		SMSVerCodeOutputDTO outputDTO = JacksonUtil.json2Bean(result, SMSVerCodeOutputDTO.class);
		if (outputDTO == null || outputDTO.getResp() == null) {
			try {
				BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "发送短信失败。");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		ResultEnum resultEnum = null;
		String msg = "";
		
		if ("000000".equals(outputDTO.getResp().getRespCode())) {
			boolean isExist = false;
			Iterator<VerificationCode> it = RemoveVerCodeService.getInstance().getSetVCode().iterator();  
			while (it.hasNext()) {
				VerificationCode tbVerificationCode = (VerificationCode) it.next();
				if (strPhone.equals(tbVerificationCode.getMobileNumber()) && type == tbVerificationCode.getType()) {
					isExist = true;
					tbVerificationCode.setVerificationCode(StringUtil.str2Integer(random));
					tbVerificationCode.setCreateTime(Calendar.getInstance().getTime());
					break;
				}
			}
			
			if (!isExist) {
				VerificationCode vCode = new VerificationCode();
				vCode.setMobileNumber(StringUtil.str2Long(strPhone));
				vCode.setVerificationCode(StringUtil.str2Integer(random));
				vCode.setCreateTime(Calendar.getInstance().getTime());
				vCode.setType(type);
				RemoveVerCodeService.getInstance().getSetVCode().add(vCode);
			}
			
			resultEnum = ResultEnum.Success;
		} else {
			msg = "错误码=" + outputDTO.getResp().getRespCode();
			LOGGER.error(msg);
			resultEnum = ResultEnum.Fail_negative3;
		}
		
		try {
			BaseServerHandler.writeSynResultMessage(dos, resultEnum, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 10.	对应站点一些储存信息读取
	 * @param json
	 * @throws Exception 
	 */
	private void readsave(String json) throws Exception {
		StationInfoReadInputDTO inputDTO = JacksonUtil.json2Bean(json, StationInfoReadInputDTO.class);
		com.deviceMonitor.model.User user = loginValidate(inputDTO);
        if (user == null) {
        	return;
        }
        
        Map params = new HashMap();
        params.put("userId", user.getId());
        params.put("registerNumber", inputDTO.getDevice());
		List<DeviceStation> listDeviceStation = deviceStationService.listByParams(params);
		if (!CollectionUtils.isEmpty(listDeviceStation)) {
			StationInfoReadOutputDTO outputDTO = new StationInfoReadOutputDTO();
			outputDTO.setName(listDeviceStation.get(0).getName());
			outputDTO.setCel(listDeviceStation.get(0).getCel());
			outputDTO.setTel(listDeviceStation.get(0).getTel());
			outputDTO.setData1(listDeviceStation.get(0).getData1());
			
	        String result = JacksonUtil.bean2Json(outputDTO);
	        
	        // 返回客户端消息	
	        dos.writeUTF(result);
		}
	}

	/**
	 * 9.	对应站点保存一些信息
	 * @param json
	 * @throws Exception 
	 */
	private void save(String json) throws Exception {
		StationInfoSaveInputDTO inputDTO = JacksonUtil.json2Bean(json, StationInfoSaveInputDTO.class);
		com.deviceMonitor.model.User user = loginValidate(inputDTO);
        if (user == null) {
        	return;
        }
        
        Map params = new HashMap();
        params.put("userId", user.getId());
        params.put("registerNumber", inputDTO.getDevice());
		List<DeviceStation> listDeviceStation = deviceStationService.listByParams(params);
		if (!CollectionUtils.isEmpty(listDeviceStation)) {
			for (DeviceStation tmpDeviceStation : listDeviceStation) {
				tmpDeviceStation.setName(inputDTO.getName());
				tmpDeviceStation.setCel(inputDTO.getCel());
				tmpDeviceStation.setTel(inputDTO.getTel());
				String[] arrChannelSort = inputDTO.getData1();
				StringBuffer sb = new StringBuffer();
				if (arrChannelSort != null && arrChannelSort.length > 0) {
					for(int i = 0; i < arrChannelSort.length; i++){
						sb.append(arrChannelSort[i]);
						if (i != arrChannelSort.length - 1) {
		        			sb.append(",");
		        		}
					}
				}
				tmpDeviceStation.setData1(sb.toString());
			}
			
			boolean isOK = deviceStationService.batchUpdate(listDeviceStation);
			BaseServerHandler.writeSynResultMessage(dos, isOK ? ResultEnum.Success : ResultEnum.Fail_negative3, isOK ? "" : "保存失败。");
		}
	}

	/**
	 * 8.	设备站点排序上发
	 * @param json
	 * @throws Exception 
	 */
	private void queue(String json) throws Exception {
		StationQueueSaveInputDTO inputDTO = JacksonUtil.json2Bean(json, StationQueueSaveInputDTO.class);
		com.deviceMonitor.model.User user = loginValidate(inputDTO);
        if (user == null) {
        	return;
        }
        
        UserQueue userQueue = new UserQueue();
        userQueue.setUser_id(user.getId());
        
        StringBuffer sb = new StringBuffer();
        int[] arrQueue = inputDTO.getQueue();
        if (arrQueue != null && arrQueue.length > 0) {
        	for (int i = 0; i < arrQueue.length; i++) {
        		sb.append(arrQueue[i]);
        		if (i != arrQueue.length - 1) {
        			sb.append(",");
        		}
			}
        }
        
        userQueue.setQueue(sb.toString());
        int count = userQueueService.replace(userQueue);
        boolean isOK = count > 0;
        BaseServerHandler.writeSynResultMessage(dos, isOK ? ResultEnum.Success : ResultEnum.Fail_negative3, isOK ? "" : "保存失败。");
	}
	
	/**
	 * 5.	获取设备点具体通道名称和解析
	 * @param msg
	 */
	private void readverNew(String json) throws Exception {
		ChannelInputDTO channelInputDTO = JacksonUtil.json2Bean(json, ChannelInputDTO.class);
		com.deviceMonitor.model.User user = loginValidate(channelInputDTO);
        if (user == null) {
        	return;
        }
        
        Map params = new HashMap();
		params.put("registerNumber", channelInputDTO.getDevice());	// 注册号
        List<DeviceParam> listDeviceParam = deviceParamService.listByParams(params);
        List<ChannelUser> listChannelUser = new ArrayList<ChannelUser>();
        
        DeviceUser deviceUser = null;
        String[] arrTagName = new String[listDeviceParam.size()];
        String[] arrDisplayName = new String[listDeviceParam.size()]; // 显示名称
        String[] arrPermission = new String[listDeviceParam.size()]; // 读写权
        String[] arrResolution = new String[listDeviceParam.size()]; // 显示值
        
        ChannelNewOutputDTO channelNewOutputDTO = new ChannelNewOutputDTO();
        channelNewOutputDTO.setRetCode("1000");
        channelNewOutputDTO.setRetMsg("SUCCESS");
        ChannelDTO oRet = new ChannelDTO();
        oRet.setResolutionver(deviceUser != null ? StringUtil.integer2Str(deviceUser.getResolution_ver()) : "-1");
        ChannelDetailDTO[] data = new ChannelDetailDTO[listDeviceParam.size()];
		
        if (!CollectionUtils.isEmpty(listDeviceParam)) {
        	List listChannelId = new ArrayList(); 
	        for (DeviceParam deviceParam : listDeviceParam) {
	        	listChannelId.add(deviceParam.getId());
			}
	        listChannelUser = channelUserService.listAllByChannelIds(listChannelId);
        
	        params = new HashMap();
	        params.put("deviceStationId", listDeviceParam.get(0).getDevice_station_id());
	        params.put("userId", user.getId());
			deviceUser = deviceUserService.queryByParams(params);
	        
			for (int i = 0; i < listDeviceParam.size(); i++) {
				ChannelDetailDTO channelDetail = new ChannelDetailDTO();
				channelDetail.setChannel_id(StringUtil.long2Str(listDeviceParam.get(i).getId()));
				channelDetail.setChannel_name(listDeviceParam.get(i).getTag_name());
				channelDetail.setPermission(Const.NUM_1_STR.equals(listDeviceParam.get(i).getLimit_set()) ? "RW" : "R");
				channelDetail.setData_type(listDeviceParam.get(i).getData_type());
				channelDetail.setResolution(listDeviceParam.get(i).getDisplay_format());
				channelDetail.setSort("");
				data[i] = channelDetail;
			}
			
	        for (int i = 0; i < listDeviceParam.size(); i++) {
	        	arrTagName[i] = listDeviceParam.get(i).getTag_name();
	        	arrDisplayName[i] = listDeviceParam.get(i).getDisplay_name();
	        	arrPermission[i] = Const.NUM_1_STR.equals(listDeviceParam.get(i).getLimit_set()) ? "RW" : "R";
	        	arrResolution[i] = listDeviceParam.get(i).getDisplay_format();
			}
        }
        
        oRet.setData(data);
        
        channelNewOutputDTO.setoRet(oRet);
        String result = JacksonUtil.bean2Json(channelNewOutputDTO);
        
        // 返回客户端消息	
        dos.writeUTF(result);
	}

	/**
	 * 5.	获取设备点具体通道名称和解析
	 * @param msg
	 */
	private void readver(String json) throws Exception {
		ChannelInputDTO channelInputDTO = JacksonUtil.json2Bean(json, ChannelInputDTO.class);
		com.deviceMonitor.model.User user = loginValidate(channelInputDTO);
        if (user == null) {
        	return;
        }
        
        String[] arrChannelSort = null;	// 通道的排序
        Map params = new HashMap();
        params.put("userId", user.getId());
        params.put("registerNumber", channelInputDTO.getDevice());	// 注册号
        List<DeviceStation> listDeviceStation = deviceStationService.listByParams(params);
        if (!CollectionUtils.isEmpty(listDeviceStation)) {
        	DeviceStation station = listDeviceStation.get(0);
        	String strChannelSort = station.getData1();
        	if (StringUtil.isNotBlank(strChannelSort)) {
        		arrChannelSort = strChannelSort.split(Const.SPLIT);
        	}
        }
        
        params.clear();
		params.put("registerNumber", channelInputDTO.getDevice());	// 注册号
        List<DeviceParam> listDeviceParam = deviceParamService.listByParams(params);
        List<ChannelUser> listChannelUser = new ArrayList<ChannelUser>();
        
        DeviceUser deviceUser = null;
        String[] arrTagName = new String[listDeviceParam.size()];
        String[] arrDisplayName = new String[listDeviceParam.size()]; // 显示名称
        String[] arrPermission = new String[listDeviceParam.size()]; // 读写权
        String[] arrResolution = new String[listDeviceParam.size()]; // 显示值
        int[] arrDataType = new int[listDeviceParam.size()];	     // 数据类型
        
        if (!CollectionUtils.isEmpty(listDeviceParam)) {
        	List listChannelId = new ArrayList(); 
	        for (DeviceParam deviceParam : listDeviceParam) {
	        	listChannelId.add(deviceParam.getId());
			}
	        listChannelUser = channelUserService.listAllByChannelIds(listChannelId);
        
	        params = new HashMap();
	        params.put("deviceStationId", listDeviceParam.get(0).getDevice_station_id());
	        params.put("userId", user.getId());
			deviceUser = deviceUserService.queryByParams(params);
			
			if (arrChannelSort != null && arrChannelSort.length > 0) {
				for (int j = 0; j < arrChannelSort.length; j++) {
					for (Iterator<DeviceParam> iterator = listDeviceParam.iterator(); iterator
							.hasNext();) {
						DeviceParam tmpDeviceParam = iterator.next();
						
						if (arrChannelSort[j].equals(tmpDeviceParam.getTag_name())) {
							arrTagName[j] = tmpDeviceParam.getTag_name();
							arrDisplayName[j] = tmpDeviceParam.getDisplay_name();

							Long tmpParamId1 = tmpDeviceParam.getId();
							boolean isEmpty = true;
							for (ChannelUser tmpChannelUser : listChannelUser) {
								Long tmpParamId2 = tmpChannelUser.getDevice_param_id();
								if (tmpParamId1 == tmpParamId2) {
									isEmpty = false;
									if (Const.NUM_1_STR.equals(tmpChannelUser.getIs_display())) {
										arrPermission[j] = Const.NUM_1_STR.equals(tmpChannelUser.getIs_modify()) ? "RW" : "R";
									} else {
										arrPermission[j] = "";
									}
									
									break;
								}
							}

							if (isEmpty) {
								arrPermission[j] = "";
							}
							arrResolution[j] = tmpDeviceParam.getDisplay_format();
							arrDataType[j] = StringUtil.str2Integer(tmpDeviceParam.getData_type());
							iterator.remove();
						}
					}
				}
			}
	        
	        for (int i = 0; i < listDeviceParam.size(); i++) {
	        	arrTagName[i] = listDeviceParam.get(i).getTag_name();
	        	arrDisplayName[i] = listDeviceParam.get(i).getDisplay_name();
	        	
	        	Long tmpParamId1 = listDeviceParam.get(i).getId();
	        	boolean isEmpty = true;
	        	for (ChannelUser tmpChannelUser : listChannelUser) {
	        		Long tmpParamId2 = tmpChannelUser.getDevice_param_id();
	        		if (tmpParamId1 == tmpParamId2) {
	        			isEmpty = false;
	        			if (Const.NUM_1_STR.equals(tmpChannelUser.getIs_display())) {
	        				arrPermission[i] = Const.NUM_1_STR.equals(tmpChannelUser.getIs_modify()) ? "RW" : "R";
	        			} else {
	        				arrPermission[i] = "";
	        			}
	        			
	        			break;
	        		}
				}
	        	
	        	if (isEmpty) {
	        		arrPermission[i] = "";
	        	}
	        	arrResolution[i] = listDeviceParam.get(i).getDisplay_format();
	        	arrDataType[i] = StringUtil.str2Integer(listDeviceParam.get(i).getData_type());
			}
        }
        
        ChannelOutputDTO channelOutputDTO = new ChannelOutputDTO();
        channelOutputDTO.setResult("1000");
        channelOutputDTO.setMessage("SUCCESS");
        channelOutputDTO.setResolutionver(deviceUser != null ? deviceUser.getResolution_ver() : -1);
        channelOutputDTO.setChannel(arrTagName);
        channelOutputDTO.setName(arrDisplayName);
        channelOutputDTO.setPermission(arrPermission);
        channelOutputDTO.setResolution(arrResolution);
        channelOutputDTO.setDataType(arrDataType);
        
        // TODO 可以查看的历史数据表名称以及相关、字段信息
        
        String result = JacksonUtil.bean2Json(channelOutputDTO);
        
        // 返回客户端消息	
        dos.writeUTF(result);
	}

	/**
	 * 4.	获取权限站点信息
	 * @param msg
	 * @throws Exception 
	 */
	private void station(String json) throws Exception {
		com.deviceMonitor.model.User user = loginValidate(json, new LoginUser());
        if (user == null) {
        	return;
        }
        
		Map params = new HashMap();
		params.put("userId", user.getId());
		List<DeviceStation> listDeviceStation = deviceStationService.listByParams(params);
		
		String[] arrRegisterNumber = new String[listDeviceStation.size()];
		int[] arrDeviceQueue = new int[listDeviceStation.size()];
		int[] arrDeviceState = new int[listDeviceStation.size()];
		String[] arrDeviceName = new String[listDeviceStation.size()];
		
		List<UserQueue> listUserQueue = userQueueService.listByParams(params);
		if (!CollectionUtils.isEmpty(listUserQueue)) {
			UserQueue userQueue = listUserQueue.get(0);
			String[] arrQueue = userQueue.getQueue().split(Const.SPLIT);
			for (int j = 0; j < arrQueue.length; j++) {
				for (Iterator<DeviceStation> iterator = listDeviceStation.iterator(); iterator
						.hasNext();) {
					DeviceStation tmpDeviceStation = iterator.next();
					
					if (arrQueue[j].equals(tmpDeviceStation.getRegister_number())) {
						arrRegisterNumber[j] = StringUtil.strRemoveNullAndBlank(tmpDeviceStation.getRegister_number());
						arrDeviceQueue[j] = StringUtil.str2Integer(StringUtil.strRemoveNull(tmpDeviceStation.getDevice_queue()));
						arrDeviceState[j] = StringUtil.str2Integer(tmpDeviceStation.getDevice_state());
						arrDeviceName[j] = StringUtil.strRemoveNullAndBlank(tmpDeviceStation.getDevice_name());
						iterator.remove();
					}
				}
			}
			
			for (int i = 0; i < listDeviceStation.size(); i++) {
				arrRegisterNumber[i] = StringUtil.strRemoveNullAndBlank(listDeviceStation.get(i).getRegister_number());
				arrDeviceQueue[i] = StringUtil.str2Integer(StringUtil.strRemoveNull(listDeviceStation.get(i).getDevice_queue()));
				arrDeviceState[i] = StringUtil.str2Integer(listDeviceStation.get(i).getDevice_state());
				arrDeviceName[i] = StringUtil.strRemoveNullAndBlank(listDeviceStation.get(i).getDevice_name());
			}
		} else {
			for (int i = 0; i < listDeviceStation.size(); i++) {
				arrRegisterNumber[i] = StringUtil.strRemoveNullAndBlank(listDeviceStation.get(i).getRegister_number());
				arrDeviceQueue[i] = StringUtil.str2Integer(StringUtil.strRemoveNull(listDeviceStation.get(i).getDevice_queue()));
				arrDeviceState[i] = StringUtil.str2Integer(listDeviceStation.get(i).getDevice_state());
				arrDeviceName[i] = StringUtil.strRemoveNullAndBlank(listDeviceStation.get(i).getDevice_name());
			}
		}
		
		SynDeviceStation synDeviceStation = new SynDeviceStation();
		synDeviceStation.setResult("1000");
		synDeviceStation.setMessage("SUCCESS");
		synDeviceStation.setLimitver(user.getLimit_ver());
		synDeviceStation.setDevice(arrRegisterNumber);
		synDeviceStation.setQueue(arrDeviceQueue);
		synDeviceStation.setState(arrDeviceState);
		synDeviceStation.setName(arrDeviceName);
		String result = JacksonUtil.bean2Json(synDeviceStation);
        
        // 返回客户端消息	
        dos.writeUTF(result);
	}

	/**
	 * 3.	用户登录
	 * @param json
	 * @throws Exception
	 */
	private void login(String json) throws Exception {
		com.deviceMonitor.model.User user = loginValidate(json, new LoginUser());
        if (user == null) {
        	return;
        }
        
        LoginSynResultMessage resultMessage = new LoginSynResultMessage();
        resultMessage.setReturn(ResultEnum.Success);
        resultMessage.setMsg("");
        resultMessage.setName(user.getUser_name());
        resultMessage.setSnumber(user.getPhone());
        resultMessage.setCompany(user.getCompany());
        String result = JacksonUtil.bean2Json(resultMessage);
        
        // 返回客户端消息	
        dos.writeUTF(result);
	}
	
	private <T extends SynBase> com.deviceMonitor.model.User loginValidate(String json, T obj) throws Exception {
		SynBase synUser = JacksonUtil.json2Bean(json, obj.getClass());
        String loginName = StringUtil.strRemoveNullAndBlank(synUser.getUser());
        String pwd = StringUtil.strRemoveNullAndBlank(synUser.getPass());
    	if (StringUtil.isBlank(loginName) || StringUtil.isBlank(pwd)) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "用户名或密码不允许为空，请检查。");
        	return null;
    	}
        
        com.deviceMonitor.model.User user = userService.login(loginName, pwd);
        if (user == null) {
            Map params = new HashMap();
            params.put("key", loginName);
            params.put("id", "");
            int count = userService.isExists(params);
            BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, count == 0 ? "不存在该用户名。" : "用户名或密码错误。");
            return null;
        } 
        
        if (Const.NO.equals(user.getEnabled())) {
            BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, "用户已禁止使用，请联系管理员。");
            return null;
        }
        
        /**
        if (!user.getImei().equals(synUser.getImei())) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative1, "用户已经在其他地方登录。");
        	return null;
        }
        **/
        
        return user;
	}
	
	private <T extends SynBase> com.deviceMonitor.model.User loginValidate(T inputDTO) throws Exception {
        String loginName = StringUtil.strRemoveNullAndBlank(inputDTO.getUser());
        String pwd = StringUtil.strRemoveNullAndBlank(inputDTO.getPass());
    	if (StringUtil.isBlank(loginName) || StringUtil.isBlank(pwd)) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "用户名或密码不允许为空，请检查。");
        	return null;
    	}
        
        com.deviceMonitor.model.User user = userService.login(loginName, pwd);
        if (user == null) {
            Map params = new HashMap();
            params.put("key", loginName);
            params.put("id", "");
            int count = userService.isExists(params);
            BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, count == 0 ? "不存在该用户名。" : "用户名或密码错误。");
            return null;
        } 
        
        if (Const.NO.equals(user.getEnabled())) {
            BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, "用户已禁止使用，请联系管理员。");
            return null;
        }
        
        // TODO
        /**
        if (!user.getImei().equals(inputDTO.getImei())) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative1, "用户已经在其他地方登录。");
        	return null;
        }
        **/
        
        return user;
	}
	
	/**
	 * 2.	用户信息修改
	 * @param json
	 * @throws Exception
	 */
	private void modify(String json) throws Exception {
		NewUser synUser = JacksonUtil.json2Bean(json, NewUser.class);
        String loginName = StringUtil.strRemoveNullAndBlank(synUser.getUser());
        String pwd = StringUtil.strRemoveNullAndBlank(synUser.getPass());
    	if (StringUtil.isBlank(loginName) || StringUtil.isBlank(pwd)) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "用户名或密码不允许为空，请检查。");
        	return;
    	}
        
        com.deviceMonitor.model.User user = userService.login(loginName, pwd);
        if (user == null) {
            Map params = new HashMap();
            params.put("key", loginName);
            params.put("id", "");
            int count = userService.isExists(params);
            BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, count == 0 ? "不存在该用户名。" : "用户名或密码错误。");
            return;
        } 
        
        if (Const.NO.equals(user.getEnabled())) {
            BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, "用户已禁止使用，请联系管理员。");
            return;
        }
        
    	long snumber = synUser.getSnumber();
    	int addsum = synUser.getAddsum();
    	
    	if (String.valueOf(snumber).length() != 11) {
    		BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "手机号码错误，请检查。");
        	return;
    	}
    	
    	if (String.valueOf(addsum).length() != 6) {
    		BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "短信校验码错误，请检查。");
        	return;
    	}
    	
    	ResultEnum result = ResultEnum.Fail_negative3;
    	String msg = "";
    	
    	Set<VerificationCode> setVCode = RemoveVerCodeService.getInstance().getSetVCode();
		Iterator<VerificationCode> it = setVCode.iterator();  
		while (it.hasNext()) {
			VerificationCode tbVerificationCode = (VerificationCode) it.next();
			if (snumber == tbVerificationCode.getMobileNumber() && 1 == tbVerificationCode.getType()) {
				long timeDiff = Calendar.getInstance().getTimeInMillis() - tbVerificationCode.getCreateTime().getTime();
				boolean isValid = (timeDiff <= 3 * 60 * 1000);
				
				if (isValid && addsum == tbVerificationCode.getVerificationCode()) {
					result = ResultEnum.Success;
				} else if (!isValid) {
					msg = "已过3分钟有效期，请重新获取验证码。";
					it.remove();
				}
				
				break;
			}
		}
		
		if (result == ResultEnum.Fail_negative3) {
			BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, msg);
        	return;
		}
        
        if (StringUtil.isNotBlank(synUser.getNewpass()))
        	user.setPwd(synUser.getNewpass());
        else 
        	user.setPwd(synUser.getPass());
        
        user.setLogin_name(synUser.getUser());
        user.setImei(synUser.getImei());
        if (StringUtil.isNotBlank(synUser.getName()))
        	user.setUser_name(synUser.getName());
        user.setPhone(StringUtil.long2Str(synUser.getSnumber()));
        if (StringUtil.isNotBlank(synUser.getCompany()))
        	user.setCompany(synUser.getCompany());
        int result1 = userService.update(user);
        boolean isOK = result1 > 0;
        BaseServerHandler.writeSynResultMessage(dos, isOK ? ResultEnum.Success : ResultEnum.Fail_negative3, isOK ? "" : "用户信息修改失败。");
	}

	/**
	 * 1.	用户注册
	 * @param json
	 * @throws Exception
	 */
	private void register(String json) throws Exception {
		User synUser = JacksonUtil.json2Bean(json, User.class);
        String loginName = StringUtil.strRemoveNullAndBlank(synUser.getUser());
        String pwd = StringUtil.strRemoveNullAndBlank(synUser.getPass());
    	if (StringUtil.isBlank(loginName) || StringUtil.isBlank(pwd)) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "用户名或密码不允许为空，请检查。");
        	return;
    	}
    	
    	long snumber = synUser.getSnumber();
    	int addsum = synUser.getAddsum();
    	
    	if (String.valueOf(snumber).length() != 11) {
    		BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "手机号码错误，请检查。");
        	return;
    	}
    	
    	if (String.valueOf(addsum).length() != 6) {
    		BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, "短信校验码错误，请检查。");
        	return;
    	}
    	
    	ResultEnum result = ResultEnum.Fail_negative3;
    	String msg = "";
    	
    	Set<VerificationCode> setVCode = RemoveVerCodeService.getInstance().getSetVCode();
		Iterator<VerificationCode> it = setVCode.iterator();  
		while (it.hasNext()) {
			VerificationCode tbVerificationCode = (VerificationCode) it.next();
			if (snumber == tbVerificationCode.getMobileNumber() && 0 == tbVerificationCode.getType()) {
				long timeDiff = Calendar.getInstance().getTimeInMillis() - tbVerificationCode.getCreateTime().getTime();
				boolean isValid = (timeDiff <= 3 * 60 * 1000);
				
				if (isValid && addsum == tbVerificationCode.getVerificationCode()) {
					result = ResultEnum.Success;
				} else if (!isValid) {
					msg = "已过3分钟有效期，请重新获取验证码。";
					it.remove();
				}
				
				break;
			}
		}
		
		if (result == ResultEnum.Fail_negative3) {
			BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative3, msg);
        	return;
		}
        
        Map params = new HashMap();
        params.put("key", loginName);
        params.put("id", "");
        int count = userService.isExists(params);
        if (count > 0) {
        	BaseServerHandler.writeSynResultMessage(dos, ResultEnum.Fail_negative2, "已存在该用户名。");
        	return;
        }
          
        com.deviceMonitor.model.User user = new com.deviceMonitor.model.User();
        user.setLogin_name(synUser.getUser());
        user.setPwd(Md5.getMD5Str(synUser.getPass()));
        user.setImei(synUser.getImei());
        user.setUser_name(synUser.getName());
        user.setPhone(StringUtil.long2Str(synUser.getSnumber()));
        user.setCompany(synUser.getCompany());
        user.setEnabled(Const.YES);
        user.setRole_id(Const.NUM_0);
        int result1 = userService.insert(user);
        boolean isOK = result1 > 0;
        BaseServerHandler.writeSynResultMessage(dos, isOK ? ResultEnum.Success : ResultEnum.Fail_negative3, isOK ? "" : "用户注册失败。");
	}
}
