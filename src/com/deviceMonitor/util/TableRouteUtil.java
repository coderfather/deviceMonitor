package com.deviceMonitor.util;

public class TableRouteUtil {
	private static final String ZERO = "0";
	private static int factor = 7;
	
    public static String getDbNoByTempHash(String str) {
        int idNo = hash(str);
        long dbNo = Math.abs(idNo % factor) + 1;
        return (dbNo < ConstantUtil.NUM_10) ? ZERO + dbNo : Long.toString(dbNo);
    }
    
    /**
     * 32位hash算法
     * 
     * @param data 字符串
     * @return int值
     */
    private static int hash(String data) {
        final int p = ConstantUtil.NUM_16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << ConstantUtil.NUM_13;
        hash ^= hash >> ConstantUtil.NUM_7;
        hash += hash << ConstantUtil.NUM_3;
        hash ^= hash >> ConstantUtil.NUM_17;
        hash += hash << ConstantUtil.NUM_5;
        return hash;
    }
}
