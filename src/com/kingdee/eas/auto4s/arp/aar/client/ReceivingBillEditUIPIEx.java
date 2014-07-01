package com.kingdee.eas.auto4s.arp.aar.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.auto4s.arp.aar.IReceivingBill;
import com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUIPIEx;
import com.kingdee.eas.myframework.util.DBUtils;
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
		
		actionSave.setVisible(false);
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
		actionSubmit.setEnabled(false);
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
		
		
		//自动审核，收款(收款金额不等于0)
		actionAudit_actionPerformed(e);
		
		//调用打印接口
	//	actionPrint_actionPerformed(e);
		// 反写维修工单的账单日期与结算状态
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String sql = String.format("update b " +
						"set b.CFReceiveNumber='%s' " + 
						"from CT_ATS_RepairWORWOItemSpEntry b " +
						"where exists (select 1 from T_ATS_ReceivingBillWay a " +
						"	 where a.FSourceBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI=' and a.FSourceBillIEntryID=b.FID " +
						"		and a.FParentID='%s') " +
						"and b.CFI='I'", editData.getNumber(),editData.getString("id"));
		DBUtils.executeForDialect(null, sql);
		
		if (BigDecimal.ZERO.compareTo(editData.getAmount()) != 0)
			actionRec_actionPerformed(e);
		
		if (RepairWOEditUIPIEx.rwoUI != null) {
			RepairWOEditUIPIEx.rwoUI.actionRefresh_actionPerformed(e);
			RepairWOEditUIPIEx.rwoUI = null;
		}
		getUIWindow().close();
	}
	
	
	
}
