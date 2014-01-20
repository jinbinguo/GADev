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
			{"accountCode","�˻�����","string"},
			{"remark","˵��","string"},
			{"dispatchStatus","����״̬","string"},
			{"taxBillCode","��ֵ˰����","string"},
			{"wip","WIP��","string"},
			{"payCode","�������","string"},
			{"billNum","�˵����","string"},
			{"billStatus","�˵�״̬","string"},
			{"realLineSeq","ʵ���У�����","int"},
			{"standardHour","��׼ʱ��","decimal"},
			{"lastEditTime","�༭����","date"},
			{"discountRate","�ۿ۰ٷֱ�","decimal"},
			{"lineSeq","�к�","int"},
			{"unitMI","��λ������","decimal"},
			{"hourRate","Сʱ��ʱ��","decimal"}
	};
	private String keyTitleName = "�༭����";
	
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
