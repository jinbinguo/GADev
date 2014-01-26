package com.kingdee.eas.myframework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kingdee.eas.common.EASBizException;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class ExcelUtils {

    private Workbook  wb; 
    private FileInputStream inputStream;
    private File file;
    
    private DateFormat dformat = DateFormat.getDateInstance();
    
    public ExcelUtils(boolean isOverExcel2007) {
    	if (isOverExcel2007) 
    		wb = new XSSFWorkbook();
    	else
    		wb = new HSSFWorkbook();
    	
    }
    
    public ExcelUtils(File file) throws Exception {
    	this.file = file;
    	inputStream = new FileInputStream (file);
    	if (isOverExcel2007(file.getName())) 
    		wb = new XSSFWorkbook(inputStream);
    	else
    		wb = new HSSFWorkbook(inputStream);
    }
    
    public boolean isOverExcel2007(String fileName) throws Exception {
    	if (fileName.toLowerCase().endsWith(".xls"))
    		return false;
    	else if (fileName.toLowerCase().endsWith(".xlsx"))
    		return true;
    	else throw new Exception("�ļ���ʽ����ȷ��");
    }
    
    public Sheet createSheet(String sheetName) {
    	if (StringUtils.isEmpty(sheetName)) sheetName = "Sheet1";
    	Sheet sheet = wb.createSheet(sheetName);
    	return sheet;   	
    }
    public void createCellValue(Sheet sheet, int rowIndex,int cellIndex, String value) {
    	if (value == null) return;
    	Row row = sheet.getRow(rowIndex);
    	if (row == null) row = sheet.createRow(rowIndex);
    	Cell cell = row.getCell(cellIndex);
    	if (cell == null) cell = row.createCell(cellIndex);
    	cell.setCellValue(value);
    	
    }
    public void createCellValue(Sheet sheet, int rowIndex,int cellIndex, Date value) {
    	if (value == null) return;
    	Row row = sheet.getRow(rowIndex);
    	if (row == null) row = sheet.createRow(rowIndex);
    	Cell cell = row.getCell(cellIndex);
    	if (cell == null) cell = row.createCell(cellIndex);
    	cell.setCellType(Cell.CELL_TYPE_NUMERIC);
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	cell.setCellValue(sf.format(value));
    }
    public void save(String filePath) throws Exception {
    	 FileOutputStream out=new FileOutputStream(filePath);
    	 wb.write(out);
    	 out.close();
    }
    
    public String getFileName() {
    	return file.getName();
    }
    
    
   
    
    public String[] getAllSheetName() {
    	if (wb == null) return null;
    	int i = 0; 
    	Vector<String> vecSheetName = new Vector<String>();
    	while (true) {
    		try {
	    		Sheet sheet = wb.getSheetAt(i);
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
    
    public Sheet getSheet(String sheetName) {
    	Sheet sheet = wb.getSheet(sheetName);    	
    	return sheet;
    }
    public Sheet getSheet(int index) {
    	return wb.getSheetAt(index);
    }
    
    public HashMap<String,Integer> getTitle(Sheet sheet, int titleRow) throws Exception{
    	Row row = sheet.getRow(titleRow);
    	if (row == null) return null;
    	int colNum = row.getPhysicalNumberOfCells();  
    	HashMap<String, Integer> title = new HashMap<String, Integer>();
        for (int i=0; i<=colNum; i++) { 
        	String titleName = getStringCellValue(row.getCell((short) i)); 
        	if ("".equals(titleName)) continue;
        	title.put(titleName, new Integer(i));
        }  
        return title;
    }
    public Row getRow(Sheet sheet, int rowIndex) {  	    	
    	return sheet.getRow(rowIndex);
    }
    
    public void close() throws Exception {
    	inputStream.close();
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
    public BigDecimal getBigDecimalCellValue(Row row, int cellIndex, boolean isAllowBlank) throws Exception {
    	Cell cell = row.getCell(cellIndex);
    	return getBigDecimalCellValue(cell,isAllowBlank);
    }
    
    private String getColumnFlag(int cellIndex) {
    	String columnFlag = "";
    	int columnIndex1 = cellIndex / 26;
		int columnIndex2 = cellIndex % 26;
		if (cellIndex>26) {
			if (columnIndex2 == 0)
				columnFlag = String.valueOf((char)((columnIndex1-1) + 64)) + "Z";
			else columnFlag = String.valueOf((char)((columnIndex1) + 64)) +  String.valueOf((char)((columnIndex2) + 64));
			
		} else {
			columnFlag = String.valueOf((char)(cellIndex + 64));
		}
		return columnFlag;
    }
    public BigDecimal getBigDecimalCellValue(Cell cell, boolean isAllowBlank) throws Exception {
    	if (cell == null) return null;
    	int rowIndex = cell.getRowIndex()+1;
    	int cellIndex = cell.getColumnIndex() + 1;
		String columnFlag = getColumnFlag(cellIndex);
		
    	
    	String expMsg = String.format("��%s�У���%s�����ָ�ʽ����", rowIndex,columnFlag);
    	String msg2 = String.format("��%s�У���%s�����ֲ������", rowIndex,columnFlag);
    	BigDecimal cellValue = null;
    	switch (cell.getCellType()) {
    	case Cell.CELL_TYPE_NUMERIC:
    		cellValue = BigDecimal.valueOf(cell.getNumericCellValue());
    		break;
    	case Cell.CELL_TYPE_STRING:
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
    	case Cell.CELL_TYPE_FORMULA:
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
    	case Cell.CELL_TYPE_BLANK:
    		if (!isAllowBlank) 
    			throw new EASBizException(new NumericExceptionSubItem("",msg2));
    		break;
    	default:
    		throw new EASBizException(new NumericExceptionSubItem("",expMsg));
    	}
    	return cellValue;
    }
    
    public Workbook getWorkbook(){
    	return wb;
    }
    public BigDecimal getBigDecimalCellValue(Row row, int cellIndex) throws Exception {
    	Cell cell = row.getCell(cellIndex);
    	
    	return getBigDecimalCellValue(cell);
    }
    
    public BigDecimal getBigDecimalCellValue(Cell cell) throws Exception {
    	return getBigDecimalCellValue(cell,true);
    }
    
    
    public String getStringCellValue(Row row, int cellIndex) throws Exception {
    	if (row == null) return "";
    	Cell cell = row.getCell(cellIndex);
    	return getStringCellValue(cell);
    }
    /** 
     * ��ȡ��Ԫ����������Ϊ�ַ������͵����� 
     * @param cell Excel��Ԫ�� 
     * @return String ��Ԫ���������� 
     */  
    public String getStringCellValue(Cell cell) throws Exception {  
    	String strCell = "";  
        if (cell == null) return "";
        
    	int rowIndex = cell.getRowIndex()+1;
    	int cellIndex = cell.getColumnIndex() + 1;
		String columnFlag = getColumnFlag(cellIndex);
    	
        
        try {
	        switch (cell.getCellType()) {  
	        case Cell.CELL_TYPE_STRING:  
	            strCell = cell.getStringCellValue();  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:
	        	double value = cell.getNumericCellValue();
	        	if (value * 100000 == (long)value * 100000)
	        		strCell = String.valueOf((long) cell.getNumericCellValue());
	        	else strCell = String.valueOf((float)cell.getNumericCellValue());
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:  
	            strCell = String.valueOf(cell.getBooleanCellValue());  
	            break;  
	        case Cell.CELL_TYPE_BLANK:  
	            strCell = "";  
	            break;  
	        case Cell.CELL_TYPE_FORMULA:
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
        } catch (Exception e) {
        	throw new Exception(String.format("��%s�У���%s�ַ���ʽ����%s", rowIndex,columnFlag,e.getMessage()));
        }
        return strCell.trim();  
    }  
      
    public Date getDateCellValue(Row row, int cellIndex) throws Exception {
    	Cell cell = row.getCell(cellIndex);
    	return getDateCellValue(cell);
    }
    
    /** 
     * ��ȡ��Ԫ����������Ϊ�������͵����� 
     * @param cell Excel��Ԫ�� 
     * @return String ��Ԫ���������� 
     */  
    public Date getDateCellValue(Cell cell) throws Exception { 
    	if (cell == null) return null;
    	
        Date result = null;
        String tmp="";
        try {
	        int cellType = cell.getCellType();  
	        if (cellType == Cell.CELL_TYPE_NUMERIC) {  
	        	result = cell.getDateCellValue();  
	        } else if (cellType == Cell.CELL_TYPE_STRING) {  
	            String date = getStringCellValue(cell); 
	            tmp = date;
	            result = dformat.parse(date); 
	        } else if (cellType == Cell.CELL_TYPE_BLANK) {  
	              
	        }  
        } catch (Exception e) {
        	String columnFlag = getColumnFlag(cell.getColumnIndex()+1);
        	String expMsg = String.format("��%s�У���%s�����ڸ�ʽ����[%s]", cell.getRowIndex() + 1,columnFlag,tmp);
        	throw new EASBizException(new NumericExceptionSubItem("",expMsg));
        }
        return result;  
    }  
    
    public boolean isCellEmpty(Row row, int cellIndex) throws Exception {
    	if ("".equals(getStringCellValue(row, cellIndex).trim())) 
    		return true;
    	else return false;
    		
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
    }  
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
}*/
}
