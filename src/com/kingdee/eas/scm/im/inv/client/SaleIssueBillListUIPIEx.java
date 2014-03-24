package com.kingdee.eas.scm.im.inv.client;

import java.awt.event.ActionEvent;

import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.jdbc.rowset.IRowSet;

public class SaleIssueBillListUIPIEx extends SaleIssueBillListUI {

	public SaleIssueBillListUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
	//	checkSelected();
	//	if (isSourceBillHasAllocate()) {
	//		MsgBoxEx.showInfo("来源维修工单已做了费用分担，不允许删除销售出库单！");
	//		return;
	//	}
		super.actionRemove_actionPerformed(e);
	}
	
	private boolean isSourceBillHasAllocate() throws Exception {
		String id = getSelectedKeyValue();
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from T_IM_SaleIssueEntry a")
			.append(" where exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b")
			.append(" where b.fid=a.FSourceBillEntryId and b.CFAllocateCount<>a.CFSourceEntryAllocateCount)")
			.append(" and a.FCoreBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI='")
			.append(String.format(" and a.FParentID='%s'",id));
		IRowSet rs = DBUtils.executeQueryForDialect(null, sql.toString());
		if (rs != null && rs.next()) return true;
		return false;
	}

}
