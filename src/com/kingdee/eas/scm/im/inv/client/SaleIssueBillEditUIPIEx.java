package com.kingdee.eas.scm.im.inv.client;

import java.awt.event.ActionEvent;

import com.itrus.util.SystemUtils;
import com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUIPIEx;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.framework.batchaction.BatchActionEnum;
import com.kingdee.eas.framework.batchaction.BatchSelectionEntries;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class SaleIssueBillEditUIPIEx extends SaleIssueBillEditUI {

	public SaleIssueBillEditUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		actionSave.setVisible(false);
		actionAddNew.setVisible(false);
		if (PublicUtils.equals(OprtState.EDIT, getOprtState()) && isSourceBillHasAllocate()) {
			lockUIForViewStatus();
			MsgBoxEx.showInfo("来源维修工单已做了费用分担，将不允许修改销售出库单数据！");
		}
	}
	
	@Override
	public void afterAction(BatchActionEnum bizAction,
			BatchSelectionEntries selectionEntries, int countSuccess) {
		if (PublicUtils.equals(bizAction, BatchActionEnum.SUBMIT)) {
			actionSubmit.setEnabled(false);
		}
		super.afterAction(bizAction, selectionEntries, countSuccess);
		if (countSuccess == 0 && PublicUtils.equals(bizAction, BatchActionEnum.SUBMIT)) {
			actionSubmit.setEnabled(true);
		} 
	/*	if (PublicUtils.equals(bizAction, BatchActionEnum.SUBMIT)) {
    		String sql = "select 1 from T_IM_SaleIssueEntry a " +
    				"where a.FSourceBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI=' " +
    				"and exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b where b.fid=a.FSourceBillEntryId and b.CFUnIssueQty<a.FQty) " +
    				"and a.FParentID='" + editData.getString("id") + "'";
    		try {
				IRowSet rs = DBUtils.executeQuery(null, sql);
				if (rs != null && rs.next()) {
					MsgBoxEx.showInfo("配件已出库，不可重复出库！");
					SysUtil.abort();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}*/

	}
	
	@Override
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		super.actionEdit_actionPerformed(e);
		if (isSourceBillHasAllocate()) {
			MsgBoxEx.showInfo("来源维修工单已做了费用分担，将不允许修改销售出库单数据！");
			lockUIForViewStatus();
		}
	}
	
	@Override
	public boolean beforeAction(BatchActionEnum bizAction,
			BatchSelectionEntries selectionEntries, ActionEvent event) {
		
		return super.beforeAction(bizAction, selectionEntries, event);
		
	}
	   
	@Override
	public void actionPrint_actionPerformed(ActionEvent e) throws Exception {
		super.actionPrint_actionPerformed(e);
		if (RepairWOEditUIPIEx.rwoUI != null) {
			RepairWOEditUIPIEx.rwoUI.actionRefresh_actionPerformed(e);
			RepairWOEditUIPIEx.rwoUI = null;
		}
		getUIWindow().close();
	}
	
	@Override
	public void actionPrintPreview_actionPerformed(ActionEvent e)
			throws Exception {
		super.actionPrintPreview_actionPerformed(e);
		if (RepairWOEditUIPIEx.rwoUI != null) {
			RepairWOEditUIPIEx.rwoUI.actionRefresh_actionPerformed(e);
			RepairWOEditUIPIEx.rwoUI = null;
		}
		getUIWindow().close();
	}
	
	@Override
	protected void disposeUIWindow() {
		if (RepairWOEditUIPIEx.rwoUI != null) {
			try {
				RepairWOEditUIPIEx.rwoUI.actionRefresh_actionPerformed(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RepairWOEditUIPIEx.rwoUI = null;
		}
		super.disposeUIWindow();
	}
	
	public void actionRemove_actionPerformed(ActionEvent arg0) throws Exception {
		if (isSourceBillHasAllocate()) {
			MsgBoxEx.showInfo("来源维修工单已做了费用分担，不允许删除销售出库单！");
			return;
		}
		super.actionRemove_actionPerformed(arg0);
	}
	
	
	@Override
	protected void beforeStoreFields(ActionEvent e) throws Exception {
		super.beforeStoreFields(e);
	}
	
	private boolean isSourceBillHasAllocate() throws Exception {
		if (PublicUtils.isEmpty(editData.getString("id"))) return false;
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from T_IM_SaleIssueEntry a")
			.append(" where exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b")
			.append(" where b.fid=a.FSourceBillEntryId and b.CFAllocateCount<>a.CFSourceEntryAllocateCount)")
			.append(" and a.FSourceBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI='")
			.append(String.format(" and a.FParentID='%s'",editData.getString("id")));
		IRowSet rs = DBUtils.executeQueryForDialect(null, sql.toString());
		if (rs != null && rs.next()) return true;
		return false;
	}
}
