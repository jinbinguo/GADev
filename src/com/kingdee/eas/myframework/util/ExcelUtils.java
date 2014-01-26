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
    	else throw new Exception("文件格式不正确！");
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
        //得到总行数  
        int rowNum = sheet.getLastRowNum();  
        row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();  
        //正文内容应该从第二行开始,第一行为表头的标题  
        for (int i = 1; i <= rowNum; i++) {  
            row = sheet.getRow(i);  
            int j = 0;  
            while (j<colNum) {  
        //每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据  
        //也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean  
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
		
    	
    	String expMsg = String.format("第%s行，第%s列数字格式错误", rowIndex,columnFlag);
    	String msg2 = String.format("第%s行，第%s列数字不允许空", rowIndex,columnFlag);
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
     * 获取单元格数据内容为字符串类型的数据 
     * @param cell Excel单元格 
     * @return String 单元格数据内容 
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
        	throw new Exception(String.format("第%s行，第%s字符格式错误：%s", rowIndex,columnFlag,e.getMessage()));
        }
        return strCell.trim();  
    }  
      
    public Date getDateCellValue(Row row, int cellIndex) throws Exception {
    	Cell cell = row.getCell(cellIndex);
    	return getDateCellValue(cell);
    }
    
    /** 
     * 获取单元格数据内容为日期类型的数据 
     * @param cell Excel单元格 
     * @return String 单元格数据内容 
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
        	String expMsg = String.format("第%s行，第%s列日期格式错误[%s]", cell.getRowIndex() + 1,columnFlag,tmp);
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
            //对读取Excel表格标题测试  
            InputStream is = new FileInputStream("C:\\Excel表格测试.xls");  
            ExcelReader excelReader = new ExcelReader();  
            String[] title = excelReader.readExcelTitle(is);  
            System.out.println("获得Excel表格的标题:");  
            for (String s : title) {  
                System.out.print(s + " ");  
            }  
              
            //对读取Excel表格内容测试  
            InputStream is2 = new FileInputStream("C:\\Excel表格测试.xls");  
            Map<Integer,String> map = excelReader.readExcelContent(is2);  
            System.out.println("获得Excel表格的内容:");  
            for (int i=1; i<=map.size(); i++) {  
                System.out.println(map.get(i));  
            }  
        } catch (FileNotFoundException e) {  
            System.out.println("未找到指定路径的文件!");  
            e.printStackTrace();  
        }  
    }  
public static List readExcel(File file){   
	    
	    List list=new ArrayList();   
	    Workbook wb = null;   
	    try {   
	        //构造Workbook（工作薄）对象   
	        wb=Workbook.getWorkbook(file);   
	    } catch (BiffException e) {   
	        e.printStackTrace();   
	    } catch (IOException e) {   
	        e.printStackTrace();   
	    }   
	       
	    if(wb==null)   
	        return null;   
	       
	    //获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了   
	    Sheet[] sheet = wb.getSheets();   
	       
	    if(sheet!=null&&sheet.length>0){
	    	
	        //对每个工作表进行循环   
	        for(int i=0;i<sheet.length;i++){ 
	        	
	            //得到当前工作表的行数   
	            int rowNum = sheet[i].getRows();   
	            for(int j=1;j<rowNum;j++){
	            	StringBuffer sb = new StringBuffer();   
	                //得到当前行的所有单元格   
	                Cell[] cells = sheet[i].getRow(j);   
	                if(cells!=null&&cells.length>0){   
	                    //对每个单元格进行循环   
	                    for(int k=0;k<cells.length;k++){ 
	                        //读取当前单元格的值

	                    		String cellValue = cells[k].getContents();		                        
	                       // if(null !=cellValue && !"".equals(cellValue)){ 
	                        	sb.append(cellValue+"囧☆"); 
	                    	
	                       // }
	                    }   
	                }
	                list.add(sb.toString());
	            }
	        }
	    } 
	    //最后关闭资源，释放内存
	    wb.close();   
	    return list;   
	}
public static void writeExcel(String fileName,List list,String workname){
    WritableWorkbook wwb = null;    
    try {    
        //首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象    
        wwb = Workbook.createWorkbook(new File(fileName));    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
    if(wwb!=null){    
        //创建一个可写入的工作表    
        //Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置    
        WritableSheet ws = wwb.createSheet(workname, 0);    
            
        //下面开始添加单元格 
        for(int i=0;i<list.size();i++){    
        	String s=(String)list.get(i);
        	System.out.println(s);
        	String row[]=s.split("\\|");
            for(int j=0;j<row.length;j++){    
                //这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行    
                Label labelC = new Label(j, i, row[j]);    
                try {    
                    //将生成的单元格添加到工作表中    
                    ws.addCell(labelC);    
                } catch (RowsExceededException e) {    
                    e.printStackTrace();    
                } catch (WriteException e) {    
                    e.printStackTrace();    
                }    

            }    
        }    

        try {    
            //从内存中写入文件中    
            wwb.write();    
            //关闭资源，释放内存    
            wwb.close();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }    
    }    
}*/
}
