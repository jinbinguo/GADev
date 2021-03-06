package com.kingdee.eas.fi.ar.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUIPIEx;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fi.arap.client.util.ArApBillUIUtil;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class OtherBillEditUIPIEx extends OtherBillEditUI {

	public OtherBillEditUIPIEx() throws Exception {
		super();
	}

	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		actionSave.setVisible(false);
	}
	
	@Override
	public void actionSubmit_actionPerformed(ActionEvent arg0) throws Exception {
		actionSubmit.setEnabled(false);
		
		super.actionSubmit_actionPerformed(arg0);
		// 反写维修工单的账单日期与结算状态

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String sql = String.format("update b " +
						"set b.CFArNumber='%s' " + 
						"from CT_ATS_RepairWORWOItemSpEntry b " +
						"where exists (select 1 from T_AR_OtherBillentry a " +
						"	 where a.FCoreBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI=' and a.FSourceBillEntryID=b.FID " +
						"		and a.FParentID='%s') " +
						"and b.CFI='I'", editData.getNumber(),pk.toString());
		DBUtils.executeForDialect(null, sql);
		
		
		
	//	actionPrint_actionPerformed(arg0);
		if (RepairWOEditUIPIEx.rwoUI != null) {
			RepairWOEditUIPIEx.rwoUI.actionRefresh_actionPerformed(arg0);
			RepairWOEditUIPIEx.rwoUI = null;
		}
		getUIWindow().close();
	}

	
	protected void checkTable() {
		int planRowCount = kdtPlan.getRowCount();
		int entryRowCount = kdtEntry.getRowCount();
		if (entryRowCount <= 0) {
			MsgBox.showInfo(this, ArApBillUIUtil.getStrResource("entryIsNull"));
			kDTabbedPane1.setSelectedComponent(kDPanel1);
			SysUtil.abort();
		}
		if (planRowCount <= 0) {
			MsgBox.showInfo(this, ArApBillUIUtil.getStrResource("recPlanIsNull"));
			kDTabbedPane1.setSelectedComponent(kDPanel2);
			SysUtil.abort();
		}
		if (ArApBillUIUtil.compareTotalAmount(kdtEntry, "recPayAmount",txtTotalAmount)
				|| ArApBillUIUtil.compareTotalAmount(kdtEntry,"recPayAmountLocal", txtTotalAmtLocal)
				|| ArApBillUIUtil.compareTotalAmount(kdtEntry,"unVerifyAmount", txtUnVerifyAmt)
				|| ArApBillUIUtil.compareTotalAmount(kdtEntry,"unVerifyAmountLocal", txtUnVerifyAmtLocal)
				|| ArApBillUIUtil.compareTotalAmount(kdtEntry, "verifyAmount",txtVerifyAmt)
				|| ArApBillUIUtil.compareTotalAmount(kdtEntry,"verifyAmountLocal", txtVerifyAmtLocal)) {
			MsgBox.showError(this, ArApBillUIUtil.getStrResource("entryAmountNotEqualsTotalAmount"));
			kDTabbedPane1.setSelectedComponent(kDPanel1);
			SysUtil.abort();
		}
		if (ArApBillUIUtil.compareTotalAmount(kdtPlan, "recPayAmount",txtTotalAmount)
				|| ArApBillUIUtil.compareTotalAmount(kdtPlan,"recPayAmountLoc", txtTotalAmtLocal)) {
			MsgBox.showError(this, ArApBillUIUtil.getStrResource("recPlanAmountNotEqualsTotalAmount"));
			kDTabbedPane1.setSelectedComponent(kDPanel2);
			SysUtil.abort();
		}
		if (ArApBillUIUtil.compareTotalAmount(kdtPlan, "verifyAmt",txtVerifyAmt)
				|| ArApBillUIUtil.compareTotalAmount(kdtPlan, "verifyAmtLoc",txtVerifyAmtLocal)) {
			MsgBox.showError(this,ArApBillUIUtil.getStrResource("payPlanVerifyAmountNotEqualsVerifyAmount"));
			kDTabbedPane1.setSelectedComponent(kDPanel2);
			SysUtil.abort();
		}
		boolean isAllPresent = true;
		int i = 0;
		do {
			if (i >= entryRowCount)
				break;
			if (!((Boolean) kdtEntry.getCell(i, "isPresent").getValue())
					.booleanValue()) {
				isAllPresent = false;
				break;
			}
			i++;
		} while (true);
		/*if (!isAllPresent)
			for (i = 0; i < planRowCount; i++)
				if (kdtPlan.getCell(i, "recPayAmount").getValue() == null
						|| ZERO.compareTo((BigDecimal) kdtPlan.getCell(i,"recPayAmount").getValue()) == 0) {
					MsgBox.showError(this, ArApBillUIUtil.getStrResource("RecPlan_NotNullOrZero"));
					kDTabbedPane1.setSelectedComponent(kDPanel2);
					SysUtil.abort();
				}
		*/
	}
	
	


}
