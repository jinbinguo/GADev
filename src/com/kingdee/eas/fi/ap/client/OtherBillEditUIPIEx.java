package com.kingdee.eas.fi.ap.client;

import java.awt.Component;
import java.awt.event.ActionEvent;

import com.kingdee.bos.ctrl.swing.KDDatePicker;
import com.kingdee.bos.ctrl.swing.KDLabelContainer;
import com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUIPIEx;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
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
		if (PublicUtils.equals(OprtState.EDIT, getOprtState()) && isSourceBillHasAllocate()) {
			lockUIForViewStatus();
			MsgBoxEx.showInfo("来源维修工单已做了费用分担，将不允许修改应付单数据！");
		}
	}
	
	@Override
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		super.actionEdit_actionPerformed(e);
		if (isSourceBillHasAllocate()) {
			MsgBoxEx.showInfo("来源维修工单已做了费用分担，将不允许修改应付单数据！");
			lockUIForViewStatus();
		}
	}
	
	@Override
	public void actionSubmit_actionPerformed(ActionEvent arg0) throws Exception {
		super.actionSubmit_actionPerformed(arg0);
		if (RepairWOEditUIPIEx.rwoUI != null) {
			RepairWOEditUIPIEx.rwoUI.actionRefresh_actionPerformed(arg0);
			RepairWOEditUIPIEx.rwoUI = null;
		}
		
		
		getUIWindow().close();
	}
	
	@Override
	public void actionAntiAudit_actionPerformed(ActionEvent e) throws Exception {
		super.actionAntiAudit_actionPerformed(e);
		if (isSourceBillHasAllocate()) {
			lockUIForViewStatus();
			MsgBoxEx.showInfo("来源维修工单已做了费用分担，将不允许修改应付单数据！");
		}
	}
	
	@Override
	public void actionRemove_actionPerformed(ActionEvent arg0) throws Exception {
		if (isSourceBillHasAllocate()) {
			MsgBoxEx.showInfo("来源维修工单已做了费用分担，不允许删除应付单！");
			return;
		}
		super.actionRemove_actionPerformed(arg0);
	}
	@Override
	protected void lockUIForViewStatus() {
		super.lockUIForViewStatus();
		Component comps[] = getComponents();
		for (int i = 0; i < comps.length; i++) {
			if (PublicUtils.equals("contpayDate", comps[i].getName()) &&
					comps[i] instanceof KDLabelContainer) {
				((KDLabelContainer)comps[i]).getComponent(2).setEnabled(true);
				((KDDatePicker)((KDLabelContainer)comps[i]).getComponent(2)).setAccessAuthority(0);
				}
			}
	}
	
	@Override
	protected void beforeStoreFields(ActionEvent e) throws Exception {
		//if (editData.isValueChange() && isSourceBillHasAllocate()) {
		//	throw new EASBizException(new NumericExceptionSubItem("","来源维修工单已做了费用分担，不允许修改保存应付单！"));
		//}
		super.beforeStoreFields(e);
	}
	
	private boolean isSourceBillHasAllocate() throws Exception {
		if (PublicUtils.isEmpty(editData.getString("id"))) return false;
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from T_AP_OtherBillentry a")
			.append(" where exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b")
			.append(" where b.fid=a.FSourceBillEntryId and b.CFAllocateCount<>a.CFSourceEntryAllocateCount)")
			.append(" and a.FCoreBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI='")
			.append(String.format(" and a.FParentID='%s'",editData.getString("id")));
		IRowSet rs = DBUtils.executeQueryForDialect(null, sql.toString());
		if (rs != null && rs.next()) return true;
		return false;
	}
	
}
