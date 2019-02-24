package com.deviceMonitor.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class StringUtil {
    /**
     * 工具类是静态成员的集合，注定不会被实例化。因此，不应该有公共的构造函数。
     * 所以这里定义一个私有的构造函数，限制其实例化。 
     */
    private StringUtil() {
    }

    public static String getUUID() {
    	return UUID.randomUUID().toString();
    }
    
    /**
     * Integer 转 String
     */
    public static String integer2Str(Integer integer) {
        return (integer == null) ? "" : integer.toString();
    }
    
    /**
     * Long 转 String
     */
    public static String long2Str(Long longValue) {
        return (longValue == null) ? "" : longValue.toString();
    }

    /**
     * Object 转 String
     */
    public static String object2Str(Object object) {
        return (null == object) ? "" : object.toString();
    }

    /**
     * 功能描述:判断是否为空或null <br>
     * 用org.apache.commons.lang3.StringUtils.isBlank(CharSequence)代替
     * 
     * @param target
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkNull(String target) {
        if (target == null || "".equals(target.trim()) || "null".equalsIgnoreCase(target.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述：判断是否为空或null 输入参数：<按照参数定义顺序>
     * 
     * @param target String 返回值: 类型 <说明>
     * @return boolean
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static boolean checkNull(Object target) {
        if (target == null || "".equals(target.toString().trim()) || "null".equalsIgnoreCase(
                target.toString().trim())) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述：判断是否为空 输入参数：<按照参数定义顺序>
     * 
     * @param target Object 返回值: 类型 <说明>
     * @return boolean
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static boolean isBlank(Object target) {
        if (target == null || "".equals(target.toString().trim())) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述：判断是否不为空 输入参数：<按照参数定义顺序>
     * 
     * @param target Object 返回值: 类型 <说明>
     * @return boolean
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static boolean isNotBlank(Object target) {
        return !isBlank(target);
    }
    
    /**
     * 
     * 功能描述：将字符串去除前后空格，如果为空，返回空字符串
     * 
     * @param str 输入字符串
     * @return 字符串
     * @throw 异常描述
     */
    public static String strRemoveNull(Object str) {
        return (str == null || "null".equalsIgnoreCase(str.toString())) ? "" : str.toString().trim();
    }

    /**
     * 判断字符是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(final String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (!Character.isDigit(ch[i])) {
                return false;
            }
        }
        return true;
    }

    public static Double parseDoubleByString(String str) {
        if (str != null && !"".equals(str.trim())) {
            return Double.valueOf(str);
        }
        return null;
    }

    /**
     * 功能描述: 根据传入参数返回toString形式，如果参数为空则返回null
     * 
     * @param o 传入参数
     * @return String
     */
    public static String toString(Object o) {
        return o == null ? null : o.toString();
    }

    /**
     * 
     * 功能描述: 返回一个字符串的首字母<br>
     * 如果为空，返回null。
     * 
     * @param str
     * @return
     */
    public static String getFirstLetter(String str) {
        if (checkNull(str)) {
            return null;
        } else {
            return str.substring(0, 1);
        }
    }

    public static String transNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    
    /**
     * 
     * 功能描述: 获取堆栈信息<br>
     * 如果为空，str。
     * 
     * @param str
     * @return str
     */
    public static String getStackMsg(Exception e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = e.getStackTrace();
        for (int i = 0; i < stackArray.length; i++) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

    public static Float str2Float(String value) {
        if (value == null) {
            return 0f;
        }

        if ("".equals(value.trim())) {
            return 0f;
        } else {
            return Float.valueOf(value);
        }
    }
    
    public static String formatDecimal(float price, int num) {
    	String str = "";
    	for (int i = 1; i <= num; i++) {
			if (i == 1) {
				str = ".0";
				continue;
			}
			
			str += "0";
		} 
    	DecimalFormat decimalFormat = new DecimalFormat(str);//构造方法的字符格式这里如果小数不足num位,会以0补足.
    	return decimalFormat.format(price);//format 返回的是字符串
    }
    
    public static float formatDecimalFloat(float price, int num) {
    	String str = formatDecimal(price, num);
    	return StringUtil.str2Float(str);
    }
    
    public static int str2Integer(String value) {
        if (checkNull(value)) {
            return 0;
        }
        BigDecimal b = BigDecimal.valueOf(Integer.valueOf(value));
        
        return b.intValue();
    }
    
    public static long str2Long(String value) {
        if (checkNull(value)) {
            return 0L;
        }
        BigDecimal b = BigDecimal.valueOf(Long.valueOf(value));
        
        return b.longValue();
    }

    public static String float2Str(Float value) {
        if (value == null) {
            return "";
        }
        return Float.toString(value);
    }
    
	/**
	 * 判断字符串是否是Null或空字符串
	 * @param str 需要验证的字符串
	 * @return 如果是null或空字符串，则返回true，返回false
	 */
	public static boolean isNullOrEmpty(String str) {
		return null == str || "".equals(str.trim());
	}
	
    /**
     * 
     * 将字符串去除前后空格，如果为空，返回空字符串
     * @param str 输入字符串
     * @return 字符串
     * @throw 异常描述
     */
    public static String strRemoveNullAndBlank(String str) {
        return str == null ? "" : str.trim();
    }
	
	/**
	 * 获取重复字符串
	 * @param orginal 原始字符串
	 * @param rep 重复次数
	 * @return 重复字符串
	 */
	public static String getRepeatString(String orginal, int rep) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<rep;i++){
		   sb.append(orginal);
		}
		return sb.toString();
	}
	
	public static String splitConvertStr(String ids) {
		String[] arr = ids.split(",");
		String newIds = "";
		for (String str : arr) {
			newIds += "'"+str+"',";
		}
		if (!"".equals(newIds)) {
			newIds = newIds.substring(0, newIds.length() - 1);
		}
		
		return newIds;
	}
	
	public static List removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		
		return list;
	}
	
	/**
	 * 判断字符串什么编码类型
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}
	
	public static String convertToUTF8(String str) throws Exception {
		String encode = getEncoding(str);
		return new String(str.getBytes(encode), "UTF-8");
	}
}
