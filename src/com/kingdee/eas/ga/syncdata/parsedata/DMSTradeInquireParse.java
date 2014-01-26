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
			{"bizDate","����","date"},
			{"customer","�ͻ�","string"},
			{"supplier","��Ӧ��","string"},
			{"option","ѡ��","string"},
			{"rqn","P/O Rqn","string"},
			{"billNum","�˵�","string"},
			{"wip","WIP��","string"},
			{"lineSeq","�к�","int"},
			{"materialNum","������","string"},
			{"qty","����","decimal"},
			{"supplyQty","��Ӧ����","decimal"},
			{"L","L","String"},
			{"remainQty","ʣ����","decimal"},
			{"cost","�ɱ�","decimal"},
			{"audit","���","string"},
			{"T","T","string"}
			
	};
	private String keyTitleName = "������";
	
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
