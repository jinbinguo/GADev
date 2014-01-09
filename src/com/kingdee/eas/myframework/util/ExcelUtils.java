package com.kingdee.eas.myframework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.kingdee.eas.common.EASBizException;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class ExcelUtils {

    private HSSFWorkbook wb;  
    
    public ExcelUtils() {
    	wb = new HSSFWorkbook();
    }
    
    public HSSFSheet createSheet(String sheetName) {
    	if (StringUtils.isEmpty(sheetName)) sheetName = "Sheet1";
    	HSSFSheet sheet = wb.createSheet(sheetName);
    	return sheet;   	
    }
    public void createCellValue(HSSFSheet sheet, int rowIndex,int cellIndex, String value) {
    	if (value == null) return;
    	HSSFRow row = sheet.getRow(rowIndex);
    	if (row == null) row = sheet.createRow(rowIndex);
    	HSSFCell cell = row.getCell(cellIndex);
    	if (cell == null) cell = row.createCell(cellIndex);
    	cell.setCellValue(value);
    	
    }
    public void createCellValue(HSSFSheet sheet, int rowIndex,int cellIndex, Date value) {
    	if (value == null) return;
    	HSSFRow row = sheet.getRow(rowIndex);
    	if (row == null) row = sheet.createRow(rowIndex);
    	HSSFCell cell = row.getCell(cellIndex);
    	if (cell == null) cell = row.createCell(cellIndex);
    	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	cell.setCellValue(sf.format(value));
    }
    public void save(String filePath) throws Exception {
    	 FileOutputStream out=new FileOutputStream(filePath);
    	 wb.write(out);
    	 out.close();
    }
    
    
    public ExcelUtils(InputStream is) {
    	try {  
    		POIFSFileSystem fs = new POIFSFileSystem(is);  
            wb = new HSSFWorkbook(fs);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
   
    
    public String[] getAllSheetName() {
    	if (wb == null) return null;
    	int i = 0; 
    	Vector<String> vecSheetName = new Vector<String>();
    	while (true) {
    		try {
	    		HSSFSheet sheet = wb.getSheetAt(i);
	    		if (sheet == null) break;
	    		String sheetName = sheet.getSheetName();
	    		if (sheetName == null || sheetName.equals("")) break;
	    		vecSheetName.add(sheetName);
	    		i++;
    		} catch (Exception e) {break;}
    	}
    	String[] sheetNames = new String[vecSheetName.size()];
    	vecSheetName.toArray(sheetNames);
    	return sheetNames;
    }
    
    public HSSFSheet getSheet(String sheetName) {
    	HSSFSheet sheet =  wb.getSheet(sheetName);    	
    	return sheet;
    }
    public HSSFSheet getSheet(int index) {
    	return wb.getSheetAt(index);
    }
    
    public HashMap<String,Integer> getTitle(HSSFSheet sheet, int titleRow) {
    	HSSFRow row = sheet.getRow(titleRow);
    	if (row == null) return null;
    	int colNum = row.getPhysicalNumberOfCells();  
    	HashMap<String, Integer> title = new HashMap<String, Integer>();
        for (int i=0; i<colNum; i++) { 
        	String titleName = getStringCellValue(row.getCell((short) i));  
        	title.put(titleName, new Integer(i));
        }  
        return title;
    }
    public HSSFRow getRow(HSSFSheet sheet, int rowIndex) {  	    	
    	return sheet.getRow(rowIndex);
    }
    
 /*       
    public Map<Integer,String> readExcelContent(InputStream is) {  
        Map<Integer,String> content = new HashMap<Integer,String>();  
        String str = "";  
        try {  
            fs = new POIFSFileSystem(is);  
            wb = new HSSFWorkbook(fs);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        sheet = wb.getSheetAt(0);  
        //�õ�������  
        int rowNum = sheet.getLastRowNum();  
        row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();  
        //��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���  
        for (int i = 1; i <= rowNum; i++) {  
            row = sheet.getRow(i);  
            int j = 0;  
            while (j<colNum) {  
        //ÿ����Ԫ�������������"-"�ָ���Ժ���Ҫʱ��String���replace()������ԭ����  
        //Ҳ���Խ�ÿ����Ԫ����������õ�һ��javabean�������У���ʱ��Ҫ�½�һ��javabean  
                str += getStringCellValue(row.getCell((short) j)).trim() + "-";  
                j ++;  
            }  
            content.put(i, str);  
            str = "";  
        }  
        return content;  
    }  
      */
    public BigDecimal getBigDecimalCellValue(HSSFRow row, int cellIndex, boolean isAllowBlank) throws Exception {
    	HSSFCell cell = row.getCell(cellIndex);
    	return getBigDecimalCellValue(cell,isAllowBlank);
    }
    
    public BigDecimal getBigDecimalCellValue(HSSFCell cell, boolean isAllowBlank) throws Exception {
    	if (cell == null) return null;
    	int rowIndex = cell.getRowIndex()+1;
    	int cellIndex = cell.getColumnIndex() + 1;
		String columnStr = "";
		int columnIndex1 = cellIndex / 26;
		int columnIndex2 = cellIndex % 26;
		if (cellIndex>26) {
			if (columnIndex2 == 0)
				columnStr = String.valueOf((char)((columnIndex1-1) + 64)) + "Z";
			else columnStr = String.valueOf((char)((columnIndex1) + 64)) +  String.valueOf((char)((columnIndex2) + 64));
			
		} else {
			columnStr = String.valueOf((char)(cellIndex + 64));
		}
    	
    	String expMsg = String.format("��%s�У���%s�����ָ�ʽ����", rowIndex,columnStr);
    	String msg2 = String.format("��%s�У���%s�����ֲ������", rowIndex,columnStr);
    	BigDecimal cellValue = null;
    	switch (cell.getCellType()) {
    	case HSSFCell.CELL_TYPE_NUMERIC:
    		cellValue = BigDecimal.valueOf(cell.getNumericCellValue());
    		break;
    	case HSSFCell.CELL_TYPE_STRING:
    		String value = getStringCellValue(cell);
    		if (!isAllowBlank && StringUtils.isEmpty(value))
    			throw new EASBizException(new NumericExceptionSubItem("",msg2));
    		try {
    			if (!StringUtils.isEmpty(value))
    				cellValue = new BigDecimal(value);
    		} catch (Exception e) {
    			throw new EASBizException(new NumericExceptionSubItem("",expMsg));
    		}
    		break;
    	case HSSFCell.CELL_TYPE_FORMULA:
    		try {
    			cellValue = BigDecimal.valueOf(cell.getNumericCellValue());
    		} catch (Exception e) {
    			String value1 = getStringCellValue(cell);
        		if (!isAllowBlank && StringUtils.isEmpty(value1))
        			throw new EASBizException(new NumericExceptionSubItem("",msg2));
        		try {
        			if (!StringUtils.isEmpty(value1))
        				cellValue = new BigDecimal(value1);
        		} catch (Exception ee) {
        			throw new EASBizException(new NumericExceptionSubItem("",expMsg));
        		}
    			
    		}
    		break;
    	case HSSFCell.CELL_TYPE_BLANK:
    		if (!isAllowBlank) 
    			throw new EASBizException(new NumericExceptionSubItem("",msg2));
    		break;
    	default:
    		throw new EASBizException(new NumericExceptionSubItem("",expMsg));
    	}
    	return cellValue;
    }
    public HSSFWorkbook getWorkbook(){//add by xierongyu 2012-11-29
    	return wb;
    }
    public BigDecimal getBigDecimalCellValue(HSSFRow row, int cellIndex) throws Exception {
    	HSSFCell cell = row.getCell(cellIndex);
    	
    	return getBigDecimalCellValue(cell);
    }
    
    public BigDecimal getBigDecimalCellValue(HSSFCell cell) throws Exception {
    	return getBigDecimalCellValue(cell,true);
    }
    
    
    public String getStringCellValue(HSSFRow row, int cellIndex) {
    	HSSFCell cell = row.getCell(cellIndex);
    	return getStringCellValue(cell);
    }
    /** 
     * ��ȡ��Ԫ����������Ϊ�ַ������͵����� 
     * @param cell Excel��Ԫ�� 
     * @return String ��Ԫ���������� 
     */  
    public String getStringCellValue(HSSFCell cell) {  
        String strCell = "";  
        if (cell == null) return "";
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_STRING:  
            strCell = cell.getStringCellValue();  
            break;  
        case HSSFCell.CELL_TYPE_NUMERIC:
        	double value = cell.getNumericCellValue();
        	if (value * 100000 == (long)value * 100000)
        		strCell = String.valueOf((long) cell.getNumericCellValue());
        	else strCell = String.valueOf((float)cell.getNumericCellValue());
            break;  
        case HSSFCell.CELL_TYPE_BOOLEAN:  
            strCell = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case HSSFCell.CELL_TYPE_BLANK:  
            strCell = "";  
            break;  
        case HSSFCell.CELL_TYPE_FORMULA:
        	 strCell = String.valueOf(cell.getStringCellValue());  
             break;  
        default:  
            strCell = "";  
            break;  
        }  
        if (strCell.equals("") || strCell == null) {  
            return "";  
        }  
        if (cell == null) {  
            return "";  
        }  
        return strCell.trim();  
    }  
      
    public Date getDateCellValue(HSSFRow row, int cellIndex) throws Exception {
    	HSSFCell cell = row.getCell(cellIndex);
    	return getDateCellValue(cell);
    }
    
    /** 
     * ��ȡ��Ԫ����������Ϊ�������͵����� 
     * @param cell Excel��Ԫ�� 
     * @return String ��Ԫ���������� 
     */  
    public Date getDateCellValue(HSSFCell cell) throws Exception { 
    	if (cell == null) return null;
    	DateFormat dformat = DateFormat.getDateInstance();
        Date result = null;
 
        int cellType = cell.getCellType();  
        if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {  
        	result = cell.getDateCellValue();  
        } else if (cellType == HSSFCell.CELL_TYPE_STRING) {  
            String date = getStringCellValue(cell);  
            result = dformat.parse(date); 
        } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {  
              
        }  
        return result;  
    }  
   /*   
    public static void main(String[] args) {  
        try {  
            //�Զ�ȡExcel���������  
            InputStream is = new FileInputStream("C:\\Excel������.xls");  
            ExcelReader excelReader = new ExcelReader();  
            String[] title = excelReader.readExcelTitle(is);  
            System.out.println("���Excel���ı���:");  
            for (String s : title) {  
                System.out.print(s + " ");  
            }  
              
            //�Զ�ȡExcel������ݲ���  
            InputStream is2 = new FileInputStream("C:\\Excel������.xls");  
            Map<Integer,String> map = excelReader.readExcelContent(is2);  
            System.out.println("���Excel��������:");  
            for (int i=1; i<=map.size(); i++) {  
                System.out.println(map.get(i));  
            }  
        } catch (FileNotFoundException e) {  
            System.out.println("δ�ҵ�ָ��·�����ļ�!");  
            e.printStackTrace();  
        }  
    }  */
public static List readExcel(File file){   
	    
	    List list=new ArrayList();   
	    Workbook wb = null;   
	    try {   
	        //����Workbook��������������   
	        wb=Workbook.getWorkbook(file);   
	    } catch (BiffException e) {   
	        e.printStackTrace();   
	    } catch (IOException e) {   
	        e.printStackTrace();   
	    }   
	       
	    if(wb==null)   
	        return null;   
	       
	    //�����Workbook����֮�󣬾Ϳ���ͨ�����õ�Sheet��������������   
	    Sheet[] sheet = wb.getSheets();   
	       
	    if(sheet!=null&&sheet.length>0){
	    	
	        //��ÿ�����������ѭ��   
	        for(int i=0;i<sheet.length;i++){ 
	        	
	            //�õ���ǰ�����������   
	            int rowNum = sheet[i].getRows();   
	            for(int j=1;j<rowNum;j++){
	            	StringBuffer sb = new StringBuffer();   
	                //�õ���ǰ�е����е�Ԫ��   
	                Cell[] cells = sheet[i].getRow(j);   
	                if(cells!=null&&cells.length>0){   
	                    //��ÿ����Ԫ�����ѭ��   
	                    for(int k=0;k<cells.length;k++){ 
	                        //��ȡ��ǰ��Ԫ���ֵ

	                    		String cellValue = cells[k].getContents();		                        
	                       // if(null !=cellValue && !"".equals(cellValue)){ 
	                        	sb.append(cellValue+"���"); 
	                    	
	                       // }
	                    }   
	                }
	                list.add(sb.toString());
	            }
	        }
	    } 
	    //���ر���Դ���ͷ��ڴ�
	    wb.close();   
	    return list;   
	}
public static void writeExcel(String fileName,List list,String workname){
    WritableWorkbook wwb = null;    
    try {    
        //����Ҫʹ��Workbook��Ĺ�����������һ����д��Ĺ�����(Workbook)����    
        wwb = Workbook.createWorkbook(new File(fileName));    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
    if(wwb!=null){    
        //����һ����д��Ĺ�����    
        //Workbook��createSheet������������������һ���ǹ���������ƣ��ڶ����ǹ������ڹ������е�λ��    
        WritableSheet ws = wwb.createSheet(workname, 0);    
            
        //���濪ʼ��ӵ�Ԫ�� 
        for(int i=0;i<list.size();i++){    
        	String s=(String)list.get(i);
        	System.out.println(s);
        	String row[]=s.split("\\|");
            for(int j=0;j<row.length;j++){    
                //������Ҫע����ǣ���Excel�У���һ��������ʾ�У��ڶ�����ʾ��    
                Label labelC = new Label(j, i, row[j]);    
                try {    
                    //�����ɵĵ�Ԫ����ӵ���������    
                    ws.addCell(labelC);    
                } catch (RowsExceededException e) {    
                    e.printStackTrace();    
                } catch (WriteException e) {    
                    e.printStackTrace();    
                }    

            }    
        }    

        try {    
            //���ڴ���д���ļ���    
            wwb.write();    
            //�ر���Դ���ͷ��ڴ�    
            wwb.close();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
    }    
}
}
