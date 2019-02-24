package com.deviceMonitor.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * socket通信客户端工具类
 * @author Administrator
 *
 */
public class SocketClientUtil implements Runnable {
	private final String ipAddress;
	private final int portNumber;
	private final String sJsonData;
	
	public SocketClientUtil(final String ipAddress, final int portNumber, final String sJsonData) {
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
		this.sJsonData = sJsonData;
	}
	
	@Override
	public void run() {
		sendMsg();
	}
	
	/**
	 * 客户端发送信息的通用类
	 * @param ipAddress IP地址
	 * @param portNumber 端口号
	 * @param sJsonData 发送的JSON数据
	 */
	public String sendMsg() {
		String rJSONData = null;	//返回的JSON数据
		// 定义输入输出流
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Socket mSocket;
		try {  
			//连接服务器 并设置连接超时为5秒  
			mSocket = new Socket();  
			mSocket.connect(new InetSocketAddress(ipAddress, portNumber), 5000);   
			//获取输入输出流
			dis = new DataInputStream(mSocket.getInputStream());
			dos = new DataOutputStream(mSocket.getOutputStream());
			// 向服务器写数据
			dos.writeUTF(sJsonData);
			// 读取服务器发来的数据
			rJSONData = dis.readUTF();		 
			//返回JSON数据  
			System.out.println(rJSONData);
		} catch (SocketTimeoutException e) { 
			//连接超时 返回显示消息  
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {
			try {
				if (dis != null) {
					dis.close();
				}
				if (dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.exit(0);
		} 
		
		return rJSONData;
	}
}
