package com.kingdee.eas.ga.syncdata.parsedata;

import java.io.File;
import java.io.FileInputStream;

import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Collection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Info;
import com.kingdee.util.NumericExceptionSubItem;

public class WipBillManHourParse extends BaseExcelParse {

	private int titleRowIndex = 1;
	private int beginDataRow = 2;
	private int sheetIndex = 0;

	private String[][] titleNames = new String[][] {
			{"accountCode","账户代码","string"},
			{"remark","说明","string"},
			{"dispatchStatus","调度状态","string"},
			{"taxBillCode","增值税代码","string"},
			{"wip","WIP号","string"},
			{"payCode","付款代码","string"},
			{"billNum","账单编号","string"},
			{"billStatus","账单状态","string"},
			{"realLineSeq","实际行（排序）","int"},
			{"standardHour","标准时间","decimal"},
			{"lastEditTime","编辑日期","date"},
			{"discountRate","折扣百分比","decimal"},
			{"lineSeq","行号","int"},
			{"unitMI","单位分钟数","decimal"},
			{"hourRate","小时工时率","decimal"}
	};
	private String keyTitleName = "编辑日期";
	
	public WipBillManHourParse(File file) throws Exception {
		super(file);
		readSheet(sheetIndex, "");
		setTitleRowIndex(titleRowIndex);
		setBeginDataRow(beginDataRow);
		setTitleNames(titleNames);
		setKeyTitleName(keyTitleName);
	}
	
	public DMSWipBillEntry3Collection parseContent() throws Exception {
		try{
			int maxDataRow = getMaxDataRow();
			DMSWipBillEntry3Collection dmsWipBillEntry3Collection = new DMSWipBillEntry3Collection();
			for (int rowIndex = beginDataRow; rowIndex < maxDataRow; rowIndex++) {
				DMSWipBillEntry3Info dmsWipBillEntry3Info = new DMSWipBillEntry3Info();
				for (int i = 0; i < titleNames.length; i++) {
					String titleField = titleNames[i][0];
					String titleName = titleNames[i][1];
					String dataType = titleNames[i][2];
					Object value = getCellValue(rowIndex, titleName, dataType);
					dmsWipBillEntry3Info.put(titleField, value);
				}
				dmsWipBillEntry3Collection.add(dmsWipBillEntry3Info);
			}
			return dmsWipBillEntry3Collection;
		} catch (Exception e) {
			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage())); 
		} finally {
			close();
		}
	}

}
