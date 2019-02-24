package com.deviceMonitor.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 日期公用类
 */
public class DateUtil {
    /**
     * 工具类是静态成员的集合，注定不会被实例化。因此，不应该有公共的构造函数。
     * 所以这里定义一个私有的构造函数，限制其实例化。 
     */
    private DateUtil(){
    }

    /** yyyyMMdd时间格式 */
    public static final String FORMAT8 = "yyyyMMdd";

    /** yyyyMMddHHmmss时间格式 */
    public static final String FORMAT14 = "yyyyMMddHHmmss";

    /** yyyy-MM-dd HH:mm:ss时间格式 */
    public static final String FORMAT19 = "yyyy-MM-dd HH:mm:ss";
    
    /** yyyy/MM/dd HH:mm:ss时间格式 */
    public static final String FORMAT20 = "yyyy/MM/dd HH:mm:ss";
    
    /** yyyy-MM-dd HH:mm时间格式 */
    public static final String FORMAT16 = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_DAY = "yyyy-MM-dd";
    
    /**
     * 用于长度判断:0
     */
    public static final int LENGTH_ZERO = 0;

    /**
     * 用于长度判断:15
     */
    public static final int LENGTH_15 = 15;
    
    /**
     * 日期时间字符串指针：起始位置
     */
    public static final int POINTER_START = 0;

    /**
     * 日期时间字符串指针：短日期“年”下标
     */
    public static final int POINTER_YEAR_SHORT_DATE = 2;

    /**
     * 日期时间字符串指针：长日期“年”下标
     */
    public static final int POINTER_YEAR_LONG_DATE = 4;

    /**
     * 日期时间字符串指针：月下标
     */
    public static final int POINTER_MONTH = 6;

    /**
     * 日期时间字符串指针：日下标
     */
    public static final int POINTER_DAY = 8;

    /**
     * 日期时间字符串指针：时下标
     */
    public static final int POINTER_HOUR = 10;

    /**
     * 日期时间字符串指针：分下标
     */
    public static final int POINTER_MINUTE = 12;

    /**
     * 日期时间字符串指针：秒下标
     */
    public static final int POINTER_SECOND = 14;

    /**
     * 日期时间字符串指针：format19格式，月下标起始位置
     */
    public static final int POINTER_FORMAT19_MONTH_START = 5;

    /**
     * 日期时间字符串指针：format19格式，月下标结束位置
     */
    public static final int POINTER_FORMAT19_MONTH_END = 7;

    /**
     * 日期时间字符串指针：format19格式，日下标起始位置
     */
    public static final int POINTER_FORMAT19_DAY_START = 8;

    /**
     * 日期时间字符串指针：format19格式，日下标结束位置
     */
    public static final int POINTER_FORMAT19_DAY_END = 10;

    /**
     * 日期时间字符串指针：format19格式，时下标起始位置
     */
    public static final int POINTER_FORMAT19_HOUR_START = 11;

    /**
     * 日期时间字符串指针：format19格式，时下标结束位置
     */
    public static final int POINTER_FORMAT19_HOUR_END = 13;

    /**
     * 日期时间字符串指针：format19格式，分下标起始位置
     */
    public static final int POINTER_FORMAT19_MINUTE_START = 14;

    /**
     * 日期时间字符串指针：format19格式，分下标结束位置
     */
    public static final int POINTER_FORMAT19_MINUTE_END = 16;

    /**
     * 日期时间字符串指针：format19格式，秒下标起始位置
     */
    public static final int POINTER_FORMAT19_SECOND_START = 17;

    /**
     * 日期时间字符串指针：format19格式，秒下标结束位置
     */
    public static final int POINTER_FORMAT19_SECOND_END = 19;

    /**
     * 一年拥有的月份数 12
     */
    public static final int NUM_OF_MONTH_ONE_YEAR = 12;

    /**
     * 一年拥有的天数  365
     */
    public static final int NUM_OF_DAY_ONE_YEAR = 365;
    
    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate, String format) {
    	if (StringUtil.isNullOrEmpty(strDate)) {
    		return null;
    	}
    	
    	if (FORMAT19 == format && strDate.contains("/")) {
    		strDate = strDate.replaceAll("/", "-");
    	}
    	
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date strtodate = null;
        try {
            strtodate = formatter.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strtodate;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT19);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    
    /**
     * long类型转换为String类型
     * @param currentTime currentTime要转换的long类型的时间
     * @param formatType formatType要转换的string类型的时间格式
     * @return
     * @throws ParseException
     */
  	public static String longToString(long currentTime, String formatType)
  			throws ParseException {
  		Date date = longToDate(currentTime, formatType); // long类型转成Date类型
  		String strTime = dateToStrLong(date, formatType); // date类型转成String
  		return strTime;
  	}
    
  	/**
  	 * long转换为Date类型
  	 * @param currentTime currentTime要转换的long类型的时间
  	 * @param formatType formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
  	 * @return
  	 */
    public static Date longToDate(long currentTime, String formatType) {
  		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
  		String sDateTime = dateToStrLong(dateOld, formatType); // 把date类型的时间转换为string
  		Date date = strToDateLong(sDateTime, formatType); // 把String类型转换为Date类型
  		return date;
  	}
}
