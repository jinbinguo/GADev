package com.kingdee.eas.auto4s.arp.aar.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.auto4s.arp.aar.IReceivingBill;
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
	
	@Override
	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		
		ArrayList<IRow> lstRow = new ArrayList<IRow>();
		for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
			lstRow.add(kdtEntrys.getRow(i));
		}
		super.actionSave_actionPerformed(e);
		kdtEntrys.removeRows();
		for (int i = 0; i < lstRow.size(); i++) {
			IRow row = lstRow.get(i);
			if (row.getCell("DiscountAmount").getValue() == null)
				row.getCell("DiscountAmount").setValue(BigDecimal.ZERO);
			kdtEntrys.addRow(i, row);
		}
		storeFields();
		getBizInterface().update(new ObjectUuidPK(editData.getString("id")),editData);
	}
	
	@Override
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		ArrayList<IRow> lstRow = new ArrayList<IRow>();
		for (int i = 0; i < kdtEntrys.getRowCount(); i++) {
			lstRow.add(kdtEntrys.getRow(i));
		}
		super.actionSubmit_actionPerformed(e);
		kdtEntrys.removeRows();
		for (int i = 0; i < lstRow.size(); i++) {
			IRow row = lstRow.get(i);
			if (row.getCell("DiscountAmount").getValue() == null)
				row.getCell("DiscountAmount").setValue(BigDecimal.ZERO);
			kdtEntrys.addRow(i, row);
		}
		storeFields();
		getBizInterface().update(new ObjectUuidPK(editData.getString("id")),editData);
	}
	
}
