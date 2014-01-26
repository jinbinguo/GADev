package com.kingdee.eas.ga.syncdata.parsedata;

import java.io.File;

import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryCollection;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryInfo;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Collection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Info;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.util.NumericExceptionSubItem;

public class DMSTradeInquireParse extends BaseExcelParse {

	private int titleRowIndex = 1;
	private int beginDataRow = 2;
	private int sheetIndex = 0;
	//																

	private String[][] titleNames = new String[][] {
			{"bizDate","出库","date"},
			{"customer","客户","string"},
			{"supplier","供应商","string"},
			{"option","选项","string"},
			{"rqn","P/O Rqn","string"},
			{"billNum","账单","string"},
			{"wip","WIP号","string"},
			{"lineSeq","行号","int"},
			{"materialNum","零件编号","string"},
			{"qty","数量","decimal"},
			{"supplyQty","供应数量","decimal"},
			{"L","L","String"},
			{"remainQty","剩余数","decimal"},
			{"cost","成本","decimal"},
			{"audit","审计","string"},
			{"T","T","string"}
			
	};
	private String keyTitleName = "零件编号";
	
	public DMSTradeInquireParse(File file) throws Exception {
		super(file);
		readSheet(sheetIndex, "");
		setTitleRowIndex(titleRowIndex);
		setBeginDataRow(beginDataRow);
		setTitleNames(titleNames);
		setKeyTitleName(keyTitleName);
	}
	
	public DMSInOutQueryEntryCollection parseContent() throws Exception {
		try {
			int maxDataRow = getMaxDataRow();
			DMSInOutQueryEntryCollection dmsInOutQueryEntryCollection = new DMSInOutQueryEntryCollection();
			for (int rowIndex = beginDataRow; rowIndex <= maxDataRow; rowIndex++) {
				DMSInOutQueryEntryInfo dmsInOutQueryEntryInfo = new DMSInOutQueryEntryInfo();
				for (int i = 0; i < titleNames.length; i++) {
					String titleField = titleNames[i][0];
					String titleName = titleNames[i][1];
					String dataType = titleNames[i][2];
					Object value = getCellValue(rowIndex, titleName, dataType);
					dmsInOutQueryEntryInfo.put(titleField, value);
				}
				dmsInOutQueryEntryCollection.add(dmsInOutQueryEntryInfo);
			}
			return dmsInOutQueryEntryCollection;
		} catch (Exception e) {
			throw new EASBizException(new NumericExceptionSubItem("",PublicUtils.getStackTrace(e))); 
		} finally {
			close();
		}
		}
}
