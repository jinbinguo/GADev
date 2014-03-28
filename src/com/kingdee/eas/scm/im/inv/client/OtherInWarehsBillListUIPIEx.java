package com.kingdee.eas.scm.im.inv.client;

import java.awt.event.ActionEvent;

import com.kingdee.eas.framework.batchaction.BatchActionEnum;
import com.kingdee.eas.framework.batchaction.BatchSelectionEntries;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.jdbc.rowset.IRowSet;

public class OtherInWarehsBillListUIPIEx extends OtherInWarehsBillListUI {

	public OtherInWarehsBillListUIPIEx() throws Exception {
		super();
	}

	
	
	@Override
	public boolean beforeAction(BatchActionEnum bizAction,
			BatchSelectionEntries selectionEntries, ActionEvent event) { 
		try {
			if (PublicUtils.equals(BatchActionEnum.DELETE, bizAction) && isSourceBillHasAllocate(selectionEntries)) {
				MsgBoxEx.showInfo("来源维修工单已做了费用分担，不允许删除其他出库单！");
				return false;
			}
		} catch (Exception exc) {
			UIUtils.handUIException(exc);
			return false;
		}
		return super.beforeAction(bizAction, selectionEntries, event);
	}
	
	private boolean isSourceBillHasAllocate(BatchSelectionEntries selectionEntries) throws Exception {
		String idArray[] = selectionEntries.getIdArray();
		if (idArray == null) return false;
		String parentIds = "";
		for (int i = 0; i < idArray.length; i++) {
			parentIds = parentIds + "'" + idArray[i] + "'";
			if (i != idArray.length -1) parentIds = parentIds + ",";
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from T_IM_OtherInWarehsBillEntry a")
			.append(" where exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b")
			.append(" where b.fid=a.FSourceBillEntryId and b.CFAllocateCount<>a.CFSourceEntryAllocateCount)")
			.append(" and a.FSourceBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI='")
			.append(String.format(" and a.FParentID in (%s)",parentIds));
		IRowSet rs = DBUtils.executeQueryForDialect(null, sql.toString());
		if (rs != null && rs.next()) return true;
		return false;
	}
}
