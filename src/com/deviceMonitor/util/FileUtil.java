package com.deviceMonitor.util;

import java.io.File;

public class FileUtil {
	public static String filePath;

    public static String getFilePath() {
        if (StringUtil.isBlank(filePath)) {
            filePath = System.getProperties().getProperty("java.io.tmpdir");
            if (!filePath.endsWith(System.getProperties().get("file.separator").toString())) {
                filePath += System.getProperties().get("file.separator");
            }
        }
        
        return filePath;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "F:\\OutProject\\deviceMonitor\\src\\WebContent\\WEB-INF\\lib";
		File file = new File(path);
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			String name = tempList[i].getName();
			if (name.startsWith("spring")) {
				System.out.println("<dependency>");
				System.out.println("<groupId>org.springframework</groupId>");
				System.out.println("<artifactId>"+name.split("-")[0]+"-"+name.split("-")[1]+"</artifactId>");
				System.out.println("<version>${"+name.split("-")[0]+"-"+name.split("-")[1]+".version}</version>");
				System.out.println("</dependency>");
			}
		}
		
		for (int i = 0; i < tempList.length; i++) {
			String name = tempList[i].getName();
			if (name.startsWith("spring")) {
				String v = name.split("-")[0]+"-"+name.split("-")[1];
				System.out.println("<"+v+".version>"+name.split("-")[2].replaceAll(".jar", "")+"</"+v+".version>");
			}
		}
	}
}
