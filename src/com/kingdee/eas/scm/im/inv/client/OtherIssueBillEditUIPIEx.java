package com.kingdee.eas.scm.im.inv.client;

import java.awt.event.ActionEvent;

import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class OtherIssueBillEditUIPIEx extends OtherIssueBillEditUI {

	public OtherIssueBillEditUIPIEx() throws Exception {
		super();
	}

	public void actionRemove_actionPerformed(ActionEvent arg0) throws Exception {
		//if (isSourceBillHasAllocate()) {
		//		MsgBoxEx.showInfo("来源维修工单已做了费用分担，不允许删除其他出库单！");
		//		return;
		//}
			super.actionRemove_actionPerformed(arg0);
	}
	
	@Override
	protected void beforeStoreFields(ActionEvent e) throws Exception {
		//if (editData.isValueChange() && isSourceBillHasAllocate()) {
		//	throw new EASBizException(new NumericExceptionSubItem("","来源维修工单已做了费用分担，不允许修改保存其他出库单！"));
		//}
		super.beforeStoreFields(e);
	}
	
	private boolean isSourceBillHasAllocate() throws Exception {
		if (PublicUtils.isEmpty(editData.getString("id"))) return false;
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from T_IM_OtherIssueBillEntry a")
			.append(" where exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b")
			.append(" where b.fid=a.FSourceBillEntryId and b.CFAllocateCount<>a.CFSourceEntryAllocateCount)")
			.append(" and a.FCoreBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI='")
			.append(String.format(" and a.FParentID='%s'",editData.getString("id")));
		IRowSet rs = DBUtils.executeQueryForDialect(null, sql.toString());
		if (rs != null && rs.next()) return true;
		return false;
	}
}
