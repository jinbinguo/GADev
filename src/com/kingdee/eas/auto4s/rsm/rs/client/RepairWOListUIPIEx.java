/**
 * output package name
 */
package com.kingdee.eas.auto4s.rsm.rs.client;

import org.apache.log4j.Logger;

import com.ibm.db2.jcc.am.on;
import com.kingdee.bos.ui.face.CoreUIObject;

public class RepairWOListUIPIEx extends RepairWOListUI {
	private static final Logger logger = CoreUIObject
			.getLogger(RepairWOListUIPIEx.class);

	public RepairWOListUIPIEx() throws Exception {
		super();
	}
	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		int intendDeliveryTimeIndex = tblMain.getColumnIndex("IntendDeliveryTime");
		tblMain.getHead().getRow(0).getCell(intendDeliveryTimeIndex).setValue("预计出厂时间");
	}
}