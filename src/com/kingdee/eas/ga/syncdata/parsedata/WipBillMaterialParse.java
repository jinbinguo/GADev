package com.kingdee.eas.ga.syncdata.parsedata;

import java.io.File;
import java.io.FileInputStream;

import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Collection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Info;
import com.kingdee.util.NumericExceptionSubItem;

public class WipBillMaterialParse extends BaseExcelParse {

	private int titleRowIndex = 1;
	private int beginDataRow = 2;
	private int sheetIndex = 0;

	private String[][] titleNames = new String[][] {
			{"accountCode","账户编号","string"},
			{"billNum","账单编号","string"},
			{"billStatus","账单状态","string"},
			{"orderStatus","定货状态","string"},
			{"materialNum","零件编号","string"},
			{"wip","WIP号","string"},
			{"chaimCode","索赔代码","string"},
			{"realLineSeq","实际行号（排序）","string"},
			{"lastEditTime","最后编辑日期","date"},
			{"remark","说明","string"},
			{"discountRate","折扣百分比","decimal"},
			{"lineSeq","行号","int"},
			{"orderQty","定货数量","int"},
			{"salePrice","销售价格","decimal"},
			{"taxBillCode","增值税代码","string"}
			
	};
	private String keyTitleName = "零件编号";
	
	public WipBillMaterialParse(File file) throws Exception {
		super(file);
		readSheet(sheetIndex, "");
		setTitleRowIndex(titleRowIndex);
		setBeginDataRow(beginDataRow);
		setTitleNames(titleNames);
		setKeyTitleName(keyTitleName);
	}
	
	public DMSWipBillEntry2Collection parseContent() throws Exception {
		try {
			int maxDataRow = getMaxDataRow();
			DMSWipBillEntry2Collection dmsWipBillEntry2Collection = new DMSWipBillEntry2Collection();
			for (int rowIndex = beginDataRow; rowIndex <= maxDataRow; rowIndex++) {
				DMSWipBillEntry2Info dmsWipBillEntry2Info = new DMSWipBillEntry2Info();
				for (int i = 0; i < titleNames.length; i++) {
					String titleField = titleNames[i][0];
					String titleName = titleNames[i][1];
					String dataType = titleNames[i][2];
					Object value = getCellValue(rowIndex, titleName, dataType);
					dmsWipBillEntry2Info.put(titleField, value);
				}
				dmsWipBillEntry2Collection.add(dmsWipBillEntry2Info);
			}
			return dmsWipBillEntry2Collection;
		} catch (Exception e) {
			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage())); 
		} finally {
			close();
		}
		}

}
