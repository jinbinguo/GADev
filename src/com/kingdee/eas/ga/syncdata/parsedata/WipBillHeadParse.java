package com.kingdee.eas.ga.syncdata.parsedata;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntryCollection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntryInfo;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.util.NumericExceptionSubItem;

public class WipBillHeadParse extends BaseExcelParse {
	
	private int titleRowIndex = 1;
	private int beginDataRow = 2;
	private int sheetIndex = 0;

	private String[][] titleNames = new String[][] {
			{"vin","底盘号","string"},
			{"createTime","创建日期","date"},
			{"mileage","进厂里程","decimal"},
			{"plateNum","牌照号","string"},
			{"wip","WIP号","string"},
			{"accountCode","账户代码","string"},
			{"deptNum","部门","string"}
	};
	private String keyTitleName = "WIP号";
	

	public WipBillHeadParse(File file) throws Exception {
		super(file);
		readSheet(sheetIndex, "");
		setTitleRowIndex(titleRowIndex);
		setBeginDataRow(beginDataRow);
		setTitleNames(titleNames);
		setKeyTitleName(keyTitleName);
	}
	
	public DMSWipBillEntryCollection parseContent() throws Exception {
		try {
			int maxDataRow = getMaxDataRow();
			DMSWipBillEntryCollection dmsWipBillEntryCollection = new DMSWipBillEntryCollection();
			for (int rowIndex = beginDataRow; rowIndex <= maxDataRow; rowIndex++) {
				DMSWipBillEntryInfo dmsWipBillEntryInfo = new DMSWipBillEntryInfo();
				for (int i = 0; i < titleNames.length; i++) {
					String titleField = titleNames[i][0];
					String titleName = titleNames[i][1];
					String dataType = titleNames[i][2];
					Object value = getCellValue(rowIndex, titleName, dataType);
					dmsWipBillEntryInfo.put(titleField, value);
				}
				dmsWipBillEntryCollection.add(dmsWipBillEntryInfo);
			}
			return dmsWipBillEntryCollection;
	
		} catch (Exception e) {
			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage())); 
		} finally {
			close();
		}
		
	}
	
	


}
