package com.deviceMonitor.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class CsvCovSql {

    public static void main(String [] args){
        try {
            readData();
        } catch (Exception e) {            
            e.printStackTrace();
        }
    }
    
    static int rec_id = 1;
    public static void readData() throws Exception{
        String fileName = "C:/a2.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String str = "";
         
        FileOutputStream fos = new FileOutputStream("C:/1.sql");
        while( (str = br.readLine())!=null ){
            String [] tmps = str.split(",");
           
            String code=tmps[2].trim();
            String type=tmps[3].trim();
            String group=tmps[4].trim();
            String division=tmps[7].trim();      
            String brand=tmps[11].trim();
            String width=tmps[8].trim();
            String length=tmps[9].trim();
            String height=tmps[10].trim();
            String netWeight=tmps[6].trim();
            String grossWeight=tmps[5].trim();  
            String name=tmps[13].trim();
            
            String sql = "INSERT INTO CSCMETA.MD_COMMODITY" +
            		"(COMMODITY_CODE,COMMODITY_TYPE,COMMODITY_GROUP,COMMODITY_DIVISION,COMMODITY_BRAND," +
            		"SUPPLIER_COMMODITY_CODE,COMMODITY_WIDTH,COMMODITY_LENGTH,COMMODITY_HEIGHT,NET_WEIGHT," +
            		"GROSS_WEIGHT,USE_AREA,COMMODITY_NAME,CATEGORY_CODE,FIX_FLAG,COMMODITY_DISPLAY_NAME," +
            		"DELIVERY_TYPE,CREATE_TIME,CREATE_USER_CODE,CREATE_USER_NAME,MODIFY_TIME,MODIFY_USER_CODE," +
            		"MODIFY_USER_NAME,ACTIVE_FLAG,NOTES)VALUES" +
            		"('"+code+"','"+type+"','"+group+"','"+division+"','"+brand+"','',"+width+","+
            		length+","+height+","+netWeight+","+grossWeight+",'','"+name+
            		"','','0','','EXPRESS','2014-06-01 00:00:00','','','2014-06-01 00:00:00','','','1','');\r\n";
            
            fos.write(sql.getBytes("utf-8"));
        }
        fos.flush();
        fos.close();
    }
}