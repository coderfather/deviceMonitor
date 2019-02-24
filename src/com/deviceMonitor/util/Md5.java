package com.deviceMonitor.util;

import java.security.MessageDigest;

/**
 * MD5加密算法类
 */
public class Md5 {
	
	 /**
	  * 使用MD5加密字符串
	 * @param str 原始字符串
	 * @return 加密后的字符串
	 */
	public static String getMD5Str(String str) {  
         MessageDigest messageDigest = null;  
   
         try {  
             messageDigest = MessageDigest.getInstance("MD5");  
   
             messageDigest.reset();  
   
             messageDigest.update(str.getBytes("UTF-8"));  
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
   
         byte[] byteArray = messageDigest.digest();  
   
         StringBuffer md5StrBuff = new StringBuffer();  
   
         for (int i = 0; i < byteArray.length; i++) {              
             if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                 md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
             else  
                 md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
         }  
   
         return md5StrBuff.toString();  
	 }  
	
	public static void main(String[] args) {
		String str = getMD5Str("123456");
		System.out.println(str + "---" + str.length());
		str = getMD5Str("1234567");
		System.out.println(str + "---" + str.length());
		
		str = getMD5Str("123456789012345");
		System.out.println(str + "---" + str.length());
		
		str = getMD5Str("123456@qwe");
		System.out.println(str + "---" + str.length());
	}
}
