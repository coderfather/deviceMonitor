package com.deviceMonitor.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.deviceMonitor.constant.ResultEnum;
import com.deviceMonitor.model.syn.SynResultMessage;
import com.deviceMonitor.util.JacksonUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class BaseServerHandler extends SimpleChannelInboundHandler<String> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServerHandler.class);
			
	private static final String[] CONTEXT = {"classpath:springConfig/applicationContext-servlet.xml", 
												"classpath:springConfig/jdbc-context.xml"};
	
	private static ClassPathXmlApplicationContext context = null;
	
	static {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(CONTEXT);			
		}
	}
	
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx,  
            Throwable cause) throws Exception {  
    	if (cause != null) {
    		System.out.println(cause.getMessage());
    	}
        LOGGER.error("Unexpected exception from downstream.", cause);  
        //ctx.close();  
    }  
	
	public void writeSynResultMessage(ChannelHandlerContext ctx, ResultEnum resultEnum, String msg) {
		SynResultMessage resultMessage = new SynResultMessage();
        resultMessage.setReturn(resultEnum.getValue());
        resultMessage.setMsg(msg);
        String result = JacksonUtil.bean2Json(resultMessage);
        
		// 返回客户端消息
        ctx.writeAndFlush(result + "\n");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Object obj, String beanId) {
		return ((T) context.getBean(beanId));
	}
}
