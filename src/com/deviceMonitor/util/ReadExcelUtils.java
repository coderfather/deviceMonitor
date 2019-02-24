package com.deviceMonitor.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

public class ReadExcelUtils {

	public static final String LINE_01 = "LINE_01";
	public static final String LINE_02 = "LINE_02";
	public static final String LINE_03 = "LINE_03";
	public static final String LINE_04 = "LINE_04";
	public static final String LINE_05 = "LINE_05";
	public static final String LINE_06 = "LINE_06";
	public static final String LINE_07 = "LINE_07";
	public static final String LINE_08 = "LINE_08";
	public static final String LINE_09 = "LINE_09";
	public static final String LINE_10 = "LINE_10";
	public static final String LINE_11 = "LINE_11";
	public static final String LINE_12 = "LINE_12";
	public static final String LINE_13 = "LINE_13";
	public static final String LINE_14 = "LINE_14";
	public static final String LINE_15 = "LINE_15";
	public static final String LINE_16 = "LINE_16";
	public static final String LINE_17 = "LINE_17";
	public static final String LINE_18 = "LINE_18";
	public static final String LINE_19 = "LINE_19";
	public static final String LINE_20 = "LINE_20";
	
	public static final int INDEX_00 = 0;
	public static final int INDEX_01 = 1;
	public static final int INDEX_02 = 2;
	public static final int INDEX_03 = 3;
	public static final int INDEX_04 = 4;
	public static final int INDEX_05 = 5;
	public static final int INDEX_06 = 6;
	public static final int INDEX_07 = 7;
	public static final int INDEX_08 = 8;
	public static final int INDEX_09 = 9;
	public static final int INDEX_10 = 10;
	public static final int INDEX_11 = 11;
	public static final int INDEX_12 = 12;
	public static final int INDEX_13 = 13;
	public static final int INDEX_14 = 14;
	public static final int INDEX_15 = 15;
	public static final int INDEX_16 = 16;
	public static final int INDEX_17 = 17;
	public static final int INDEX_18 = 18;
	public static final int INDEX_19 = 19;
	
	/**
	 * 工具类是静态成员的集合，注定不会被实例化。因此，不应该有公共的构造函数。 所以这里定义一个私有的构造函数，限制其实例化。
	 */
	private ReadExcelUtils() {
	}

	public static List<String> readTitle(String fileName,
			InputStream inputStream) {
		List<String> returnTitleList = new ArrayList<String>();

		Assert.notNull(fileName);
		Assert.notNull(inputStream);
		Workbook wb = null;

		try {

			if (fileName.endsWith("xls")) {
				wb = new HSSFWorkbook(inputStream);
			} else if (fileName.endsWith("xlsx")) {
				wb = new XSSFWorkbook(inputStream);
			}
			if (null != wb) {
				// 暂时只支持读第一个Sheet
				Sheet childSheet = wb.getSheetAt(0);
				// 获取第一行
				Row row = childSheet.getRow(0);
				// 循环该行对应的单元格项
				for (int k = 0; k < row.getLastCellNum(); k++) {
					if (null == row.getCell(0)) {
						break;
					}
					// 根据单元格的类型取值
					Cell cell = row.getCell(k);
					String cellResult = getCellResult(cell);
					returnTitleList.add(cellResult);
				}
			}
		} catch (IOException e) {
			throw new BaseException("-1001", e, "读取Excel文件失败。");
		}

		return returnTitleList;
	}

	/***
	 * 
	 * @param fileName
	 * @param inputStream
	 * @return
	 */
	public static List<Map<String, Object>> readFile (
			String fileName, InputStream inputStream) {
		Assert.notNull(fileName);
		Assert.notNull(inputStream);
		List<Map<String, Object>> result = null;
		Workbook wb = null;

		try {

			if (fileName.endsWith("xls")) {
				wb = new HSSFWorkbook(inputStream);
			} else if (fileName.endsWith("xlsx")) {
				wb = new XSSFWorkbook(inputStream);
			}
			if (null != wb) {
				result = readExcelFile(wb);
			}

		} catch (Exception e) {
			throw new BaseException("-1001", e, "读取Excel文件失败。");
		}

		return result;
	}

	/**
	 * 
	 * @param wb
	 */
	private static List<Map<String, Object>> readExcelFile (
			Workbook wb) {

		// 返回值
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		// 暂时只支持读第一个Sheet
		Sheet childSheet = wb.getSheetAt(1);
		// 循环每一行
		for (int j = 2; j <= childSheet.getLastRowNum(); j++) {
			Row row = childSheet.getRow(j);
			// 存放每一列的值
			Map<String, Object> cellResults = new HashMap<String, Object>();
			if (null != row) {
				// 循环该行对应的单元格项
				for (int k = 0; k <= row.getLastCellNum(); k++) {
					if (null == row.getCell(0)) {
						break;
					}
					
					if (k % 2 == 0) {
						continue;
					}
					
					// 根据单元格的类型取值
					Cell cell = row.getCell(k);

					if (null != cell) {
						String cellResult = getCellResult(cell);
						// 这一行的所有值
						switch (k) {
						case INDEX_00:
							cellResults.put(LINE_01, cellResult);
							break;
						case INDEX_01:
							cellResults.put(LINE_02, cellResult);
							break;
						case INDEX_02:
							cellResults.put(LINE_03, cellResult);
							break;
						case INDEX_03:
							cellResults.put(LINE_04, cellResult);
							break;
						case INDEX_04:
							cellResults.put(LINE_05, cellResult);
							break;
						case INDEX_05:
							cellResults.put(LINE_06, cellResult);
							break;
						case INDEX_06:
							cellResults.put(LINE_07, cellResult);
							break;
						case INDEX_07:
							cellResults.put(LINE_08, cellResult);
							break;
						case INDEX_08:
							cellResults.put(LINE_09, cellResult);
							break;
						case INDEX_09:
							cellResults.put(LINE_10, cellResult);
							break;
						case INDEX_10:
							cellResults.put(LINE_11, cellResult);
							break;
						case INDEX_11:
							cellResults.put(LINE_12, cellResult);
							break;
						case INDEX_12:
							cellResults.put(LINE_13, cellResult);
							break;
						case INDEX_13:
							cellResults.put(LINE_14, cellResult);
							break;
						case INDEX_14:
							cellResults.put(LINE_15, cellResult);
							break;
						case INDEX_15:
							cellResults.put(LINE_16, cellResult);
							break;
						case INDEX_16:
							cellResults.put(LINE_17, cellResult);
							break;
						case INDEX_17:
							cellResults.put(LINE_18, cellResult);
							break;
						case INDEX_18:
							cellResults.put(LINE_19, cellResult);
							break;
						case INDEX_19:
							cellResults.put(LINE_20, cellResult);
							break;
						default:
							break;
						}
					}
				}
			}
			// 加入到所有行
			if (!CollectionUtils.isEmpty(cellResults)) {
				result.add(cellResults);
			}
		}
		
		return result;
	}

	/***
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellResult(Cell cell) {
		String cellResult = "";

		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_BLANK:
			break;
		case XSSFCell.CELL_TYPE_ERROR:
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN:
			cellResult = String.valueOf(cell.getBooleanCellValue());
			break;
		case XSSFCell.CELL_TYPE_FORMULA:
			cellResult = String.valueOf(cell.getNumericCellValue());
			break;
		case XSSFCell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {      
                double d = cell.getNumericCellValue();      
                Date date = XSSFDateUtil.getJavaDate(d);    
                cellResult = new SimpleDateFormat("yyyy-MM-dd").format(date); 
            } else {  
            	cellResult = String.valueOf(cell.getNumericCellValue());   
            }  
            break;
			
            /**
			if (DateUtil.isCellDateFormatted(cell)) {
				cellResult = String.valueOf(cell.getDateCellValue());
			} else {
				cellResult = String.valueOf(cell.getNumericCellValue());
			}
			**/
		case XSSFCell.CELL_TYPE_STRING:
			cellResult = String.valueOf(cell.getRichStringCellValue());
			break;
		default:
			cellResult = String.valueOf(cell);
			break;
		}

		return cellResult;
	}
}
