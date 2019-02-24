package com.deviceMonitor.run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端TCP启动类
 *
 */
public class ServerStart {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServerStart.class);
	
	/**
	 * 服务端监听的端口地址
	 */
	private static final int PORT_NUMBER = 3610;

	public static void main(String[] args) {
		String path = Class.class.getClass().getResource("/").getPath()
				+ "resources/"
				+ "log4j.properties";
		
		//path = ".\\src\\resources\\log4j.properties";
		//System.out.println(System.getProperty("java.class.path"));
		//URL url = ServerStart.class.getResource("");
		//path = url.toString().split("com")[0] + "resources/log4j.properties";
		//System.out.println(path);
		PropertyConfigurator.configure(path);

		LOGGER.info("服务器开启......");
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
			ThreadPool threadPool = ThreadPool.getInstance();
			Socket tmpSocket = null;
			ForwardTask tmpTask = null;
			
			while (true) {
				tmpSocket = serverSocket.accept();
				LOGGER.debug("服务器接收到一个客户端连接");
				tmpTask = new ForwardTask(tmpSocket);
				threadPool.addTask(tmpTask);
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			LOGGER.info("服务器关闭......");
		}
	}
}
