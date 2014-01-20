package com.kingdee.eas.ga.syncdata.parsedata;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.tools.ant.taskdefs.Exec;

import com.kingdee.eas.myframework.util.ExcelUtils;

public class BaseExcelParse implements Serializable {
	
	private ExcelUtils excelUtils;
	private int titleRowIndex;
	private int beginDataRow;

	private Sheet sheet;
	private HashMap<String,Integer> titleMapping = new HashMap<String, Integer>();
	private String[][] titleNames;
	private String keyTitleName;
	
	public BaseExcelParse(File file) throws Exception {
		excelUtils = new ExcelUtils(file);
	}
	
	public void readSheet(int sheetIndex,String sheetName) {
		if (sheetIndex >= 0) {
			sheet = excelUtils.getSheet(sheetIndex);
		} else {
			sheet = excelUtils.getSheet(sheetName);
		}
	}
	
	public void setTitleRowIndex(int titleRowIndex) {
		this.titleRowIndex=titleRowIndex;
	}
	
	public void setBeginDataRow(int beginDataRow) {
		this.beginDataRow=beginDataRow;
	}
	public void setTitleNames(String[][] titleNames) {
		this.titleNames = titleNames;
	}
	
	public void setKeyTitleName(String keyTitleName) {
		this.keyTitleName = keyTitleName;
		
	}
	
	public Sheet getSheet() {
		return sheet;
	}
	
	public boolean parseTitle() throws Exception {
		titleMapping = excelUtils.getTitle(sheet, titleRowIndex);
		String fileName = excelUtils.getFileName();
		for (int i = 0; i < titleNames.length; i++) {
			if (titleMapping.get(titleNames[i][1]) == null) {
				throw new Exception(String.format("文件名[%s]不存在表头[%s],请确认",fileName,titleNames[i][1]));
			}
		}
		return true;
	}
	
	public int getMaxDataRow() throws Exception {
		if (titleMapping.isEmpty())
			parseTitle();
		int keyTitleIndex = titleMapping.get(keyTitleName);
		for (int i = beginDataRow; i <= sheet.getPhysicalNumberOfRows(); i++) {
			Row row = sheet.getRow(i);
			if (excelUtils.isCellEmpty(row, keyTitleIndex)) {
				return i;
			}
		}
		return sheet.getPhysicalNumberOfRows();
	}
	
	public Object getCellValue(int rowIndex,String titleName, String dataType) throws Exception {
		int titleIndex = titleMapping.get(titleName);
		Row row = excelUtils.getRow(sheet, rowIndex);
		if ("string".equals(dataType.toLowerCase())) {
			return excelUtils.getStringCellValue(row, titleIndex);
		} else if ("int".equals(dataType.toLowerCase())) {
			return excelUtils.getBigDecimalCellValue(row, titleIndex);
		} else if ("date".equals(dataType.toLowerCase())) {
			return excelUtils.getDateCellValue(row, titleIndex);
		} else if ("decimal".equals(dataType.toLowerCase())) {
			return excelUtils.getBigDecimalCellValue(row, titleIndex);
		} else {
			throw new Exception("BaseExcelParse.getCellValue参数dataType类型不合化！");
		}
	}
	
	public void close() throws Exception {
		excelUtils.close();
	}
	
	
	
	
	
}
