package com.deviceMonitor.util;

import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.logging.Logger; 

/** 
* 二维结构转换 
* File: MatrixStructure.java 
* User: leizhimin 
* Date: 2008-3-10 16:29:36 
*/ 

public class MatrixStructure { 
    private static Logger log = Logger.getLogger(MatrixStructure.class.getName()); 

    /** 
     * 将结果集二维集合数据转换为XML 
     * 
     * @param columns 列名集合 
     * @param result  二维结果集 
     * @return 返回格式良好的XML字符串 
     */ 
    public static String listToXML(ArrayList<String> columns, ArrayList<ArrayList> result) { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("<?xml version=\"1.0\"  encoding=\"GB2312\"?>\n") 
                .append("<?xml-stylesheet type=\"text/xsl\" href=\"") 
                .append("\"?>\n\n"); 
        sb.append("<rptcontent>\n"); 
        for (ArrayList al : result) { 
            sb.append("\t<row>"); 
            for (Iterator it_al = al.iterator(), it_c = columns.iterator(); it_al.hasNext() && it_c.hasNext();) { 
                String tmp = it_c.next().toString(); 
                sb.append("\n\t\t<").append(tmp).append(">").append(it_al.next()).append("<").append(tmp).append("/>"); 
            } 
            sb.append("\n\t</row>\n"); 
        } 
        sb.append("</rptcontent>"); 
        return sb.toString(); 
    } 

    /** 
     * 将结果集二维数组数据转换为XML 
     * 
     * @param columns 列名数组 
     * @param result  二维结果集数组 
     * @return 返回格式良好的XML字符串 
     */ 
    public static String arrayToXML(Object[] columns, Object[][] result) { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("<?xml version=\"1.0\"  encoding=\"GB2312\"?>\n") 
                .append("<?xml-stylesheet type=\"text/xsl\" href=\"") 
                .append("\"?>\n\n"); 
        sb.append("<rptcontent>\n"); 
        for (Object[] al : result) { 
            sb.append("\t<row>"); 
            for (int i = 0; i < columns.length - 1; i++) { 
                sb.append("\n\t\t<").append(columns[i].toString()).append(">").append(al[i].toString()).append("<").append(columns[i].toString()).append("/>"); 
            } 
            sb.append("\n\t</row>\n"); 
        } 
        sb.append("</rptcontent>"); 
        return sb.toString(); 
    } 

    /** 
     * 二维集合转为二维数组 
     * 
     * @param result 二维集合 
     * @return 二维数组 
     */ 
    public static Object[][] listToArray(ArrayList<ArrayList> result) { 
        int size = result.size(); 
        Object obj[][] = new Object[size][]; 
        if (size == 0) { 
            log.info("集合为空,转换数组失败，将返回null！"); 
            return null; 
        } 
        for (int i = 0; i < size; i++) { 
            obj[i] = result.get(i).toArray(); 
        } 
        return obj; 
    } 

    /** 
     * 将二维数组转为二维ArrayList 
     * 
     * @param arr 二维数组 
     * @return 二维ArrayList 
     */ 
    public static ArrayList<ArrayList> arrayToList(Object[][] arr) { 
        ArrayList<ArrayList> all = new ArrayList<ArrayList>(); 
        for (int i = 0; i < arr.length; i++) { 
            all.add(arrayToList(arr[i])); 
        } 
        return all; 
    } 

    /** 
     * 将一维数组转为List对象 
     * 
     * @param ar 一维数组 
     * @return List对象 
     */ 
    public static ArrayList arrayToList(Object[] ar) { 
        ArrayList al = new ArrayList(); 
        for (Object obj : ar) { 
            al.add(obj); 
        } 
        return al; 
    } 

    /** 
     * 输出二维集合到控制台 
     * 
     * @param all 二维集合 
     */ 
    public static void listOutputConsole(ArrayList<ArrayList> all) { 
        System.out.println("－－－－－－－输出二维集合到控制台－－－－－－－"); 
        for (ArrayList al : all) { 
            for (Object a : al) { 
                System.out.print(a.toString() + "\t"); 
            } 
            System.out.println(); 
        } 
        System.out.println("\n－－－－－－－－－－－－－－－－－－－－－－－－"); 
    } 


    /** 
     * 将二维数组输出到控制台 
     * 
     * @param arr 二维数组 
     */ 
    public static void arrrayOutputConsole(Object[][] arr) { 
        System.out.println("－－－－－－－输出二维数组到控制台－－－－－－－"); 
        for (Object[] ar : arr) { 
            for (Object a : ar) { 
                System.out.print(a.toString() + "\t"); 
            } 
            System.out.println(); 
        } 
        System.out.println("\n－－－－－－－－－－－－－－－－－－－－－－－－"); 
    } 

    /** 
     * 将二维数组转为格式美观的字符串 
     * 
     * @param arr 二维数组 
     */ 
    public static String arrayToString(Object[][] arr) { 
        StringBuilder sb = new StringBuilder(); 
        for (Object[] ar : arr) { 
            for (Object a : ar) { 
                sb.append(a.toString()).append("\t"); 
            } 
            sb.append("\n"); 
        } 
        return sb.toString(); 
    } 

    /** 
     * 将二维集合转为格式美观的字符串 
     * 
     * @param all 二维集合 
     */ 
    public static String listToString(ArrayList<ArrayList> all) { 
        StringBuilder sb = new StringBuilder(); 
        for (ArrayList al : all) { 
            for (Object a : al) { 
                sb.append(a.toString()).append("\t"); 
            } 
            sb.append("\n"); 
        } 
        return sb.toString(); 
    } 

    public static void main(String args[]) { 
        ArrayList<ArrayList> all = new ArrayList<ArrayList>(); 
        ArrayList al0 = new ArrayList(); 
        ArrayList al1 = new ArrayList(); 
        ArrayList al2 = new ArrayList(); 

        al0.add("01"); 
        al0.add("02"); 
        al1.add("11"); 
        al1.add("12"); 
        al2.add("21"); 
        al2.add("22"); 

        all.add(al0); 
        all.add(al1); 
        all.add(al2); 

        listOutputConsole(all); 

        StringBuffer sb = new StringBuffer(); 
        ArrayList c = new ArrayList(); 
        c.add("c1"); 
        c.add("c2"); 
        c.add("c3"); 


        Object[][] obj = listToArray(all); 
        arrrayOutputConsole(obj); 

        ArrayList<ArrayList> allx = arrayToList(obj); 

        String x = arrayToXML(c.toArray(), obj); 
        System.out.println(x); 
    } 
}
