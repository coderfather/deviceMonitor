package com.deviceMonitor.socket;

import io.netty.channel.ChannelHandlerContext;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.deviceMonitor.constant.ActionEnum;
import com.deviceMonitor.constant.Const;
import com.deviceMonitor.constant.ResultEnum;
import com.deviceMonitor.intf.service.IUserService;
import com.deviceMonitor.model.syn.LoginSynResultMessage;
import com.deviceMonitor.model.syn.LoginUser;
import com.deviceMonitor.model.syn.User;
import com.deviceMonitor.util.JacksonUtil;
import com.deviceMonitor.util.StringUtil;

public class ServerHandler extends BaseServerHandler {
	private IUserService userService;
	
	private void initObject() {
		if (userService == null) {
			userService = super.getBean(IUserService.class, "userService");
		}
	}
	
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
    	initObject();
    	
    	//msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
        // 收到消息直接打印输出
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

        if (StringUtil.isBlank(msg)) {
        	writeSynResultMessage(ctx, ResultEnum.Fail_negative3, "上传的数据不允许为空，请检查。");
        	return; 
		}
        if (JacksonUtil.isBadJson(msg)) {
        	writeSynResultMessage(ctx, ResultEnum.Fail_negative3, "无效JSON数据，请检查。");
        	return;
        }
        
        Object obj = JacksonUtil.json2Bean(msg, Object.class);
        String action = StringUtil.strRemoveNull(((LinkedHashMap) obj).get("action"));
        ActionEnum actionEnum = ActionEnum.getValueName(action);
        
        if (actionEnum == null) {
    		writeSynResultMessage(ctx, ResultEnum.Fail_negative2, "无效action，请检查。");
    		return;
    	}
        
        switch (actionEnum) {
			case Register:
				register(ctx, msg);
				break;
			case Modify:
				modify(ctx, msg);
				break;
			case Login:
				login(ctx, msg);
				break;
			default:
				break;
		}
    }

	private void register(ChannelHandlerContext ctx, String json) {
		User synUser = JacksonUtil.json2Bean(json, User.class);
        String loginName = StringUtil.strRemoveNullAndBlank(synUser.getUser());
        String pwd = StringUtil.strRemoveNullAndBlank(synUser.getPass());
    	if (StringUtil.isBlank(loginName) || StringUtil.isBlank(pwd)) {
        	writeSynResultMessage(ctx, ResultEnum.Fail_negative3, "用户名或密码不允许为空，请检查。");
        	return;
    	}
        
        Map params = new HashMap();
        params.put("key", loginName);
        params.put("id", "");
        int count = userService.isExists(params);
        if (count > 0) {
        	writeSynResultMessage(ctx, ResultEnum.Fail_negative2, "已存在该用户名。");
        	return;
        }
          
        com.deviceMonitor.model.User user = new com.deviceMonitor.model.User();
        user.setLogin_name(synUser.getUser());
        user.setPwd(synUser.getPass());
        user.setImei(synUser.getImei());
        user.setUser_name(synUser.getName());
        user.setPhone(StringUtil.long2Str(synUser.getSnumber()));
        user.setCompany(synUser.getCompany());
        user.setEnabled(Const.YES);
        int result = userService.insert(user);
        boolean isOK = result > 0;
        writeSynResultMessage(ctx, isOK ? ResultEnum.Success : ResultEnum.Fail_negative3, isOK ? "" : "用户注册失败。");
	}
	
	private void modify(ChannelHandlerContext ctx, String json) {
		User synUser = JacksonUtil.json2Bean(json, User.class);
        String loginName = StringUtil.strRemoveNullAndBlank(synUser.getUser());
        String pwd = StringUtil.strRemoveNullAndBlank(synUser.getPass());
    	if (StringUtil.isBlank(loginName) || StringUtil.isBlank(pwd)) {
        	writeSynResultMessage(ctx, ResultEnum.Fail_negative3, "用户名或密码不允许为空，请检查。");
        	return;
    	}
        
        com.deviceMonitor.model.User user = userService.login(loginName, pwd);
        if (user == null) {
            Map params = new HashMap();
            params.put("key", loginName);
            params.put("id", "");
            int count = userService.isExists(params);
            writeSynResultMessage(ctx, ResultEnum.Fail_negative2, count == 0 ? "不存在该用户名。" : "用户名或密码错误。");
            return;
        } 
        
        if (Const.NO.equals(user.getEnabled())) {
            writeSynResultMessage(ctx, ResultEnum.Fail_negative2, "用户已禁止使用，请联系管理员。");
            return;
        }
        
        user.setLogin_name(synUser.getUser());
        user.setPwd(synUser.getPass());
        user.setImei(synUser.getImei());
        user.setUser_name(synUser.getName());
        user.setPhone(StringUtil.long2Str(synUser.getSnumber()));
        user.setCompany(synUser.getCompany());
        int result = userService.update(user);
        boolean isOK = result > 0;
        writeSynResultMessage(ctx, isOK ? ResultEnum.Success : ResultEnum.Fail_negative3, isOK ? "" : "用户信息修改失败。");
	}

	private void login(ChannelHandlerContext ctx, String json) {
		LoginUser synUser = JacksonUtil.json2Bean(json, LoginUser.class);
        String loginName = StringUtil.strRemoveNullAndBlank(synUser.getUser());
        String pwd = StringUtil.strRemoveNullAndBlank(synUser.getPass());
    	if (StringUtil.isBlank(loginName) || StringUtil.isBlank(pwd)) {
        	writeSynResultMessage(ctx, ResultEnum.Fail_negative3, "用户名或密码不允许为空，请检查。");
        	return;
    	}
        
        com.deviceMonitor.model.User user = userService.login(loginName, pwd);
        if (user == null) {
            Map params = new HashMap();
            params.put("key", loginName);
            params.put("id", "");
            int count = userService.isExists(params);
            writeSynResultMessage(ctx, ResultEnum.Fail_negative2, count == 0 ? "不存在该用户名。" : "用户名或密码错误。");
            return;
        } 
        
        if (Const.NO.equals(user.getEnabled())) {
            writeSynResultMessage(ctx, ResultEnum.Fail_negative2, "用户已禁止使用，请联系管理员。");
            return;
        }
        
        if (!user.getImei().equals(synUser.getImei())) {
        	writeSynResultMessage(ctx, ResultEnum.Fail_negative1, "用户已经在其他地方登录。");
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
        ctx.writeAndFlush(result + "\n");
	}
	
	/*
     * 
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * 
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("RamoteAddress : " + ctx.channel().remoteAddress()
                + " active !");

        ctx.writeAndFlush("Welcome to "
                + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
