package com.kingdee.eas.auto4s.arp.aar.client;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.eas.myframework.util.PublicUtils;

public class ReceivingBillEditUIPIEx extends ReceivingBillEditUI {

	public ReceivingBillEditUIPIEx() throws Exception {
		super();
	}

	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		for (int i = 0; i < kdtContent.getRowCount(); i++) {
			IRow row = kdtContent.getRow(i);
			row.getCell("TotalAmount").setValue(row.getCell("ActualAmount").getValue());
		}
		if (PublicUtils.isEmpty(txtsrcEntryIds.getText())) {
			StringBuffer srcEntryIds = new StringBuffer();
			for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
				IRow row = kdtEntrys.getRow(i);
				srcEntryIds.append(row.getCell("SourceBillIEntryID").getValue()).append(",");
				
			}
			txtsrcEntryIds.setText(srcEntryIds.toString());
		}
		getUIContext().put("txtsrcEntryIds", txtsrcEntryIds.getText());
	}
	
	@Override
	public void storeFields() {
		super.storeFields();
		txtsrcEntryIds.setText((String)getUIContext().get("txtsrcEntryIds"));
	}
}
