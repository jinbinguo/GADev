package com.kingdee.eas.fi.ap.client;

import java.awt.event.ActionEvent;

import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.jdbc.rowset.IRowSet;

public class OtherBillListUIPIEx extends OtherBillListUI {

	public OtherBillListUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		//checkSelected();
		//if (isSourceBillHasAllocate()) {
		//	MsgBoxEx.showInfo("��Դά�޹��������˷��÷ֵ���������ɾ��Ӧ������");
		//	return;
		//}
		super.actionRemove_actionPerformed(e);
	}
	
	private boolean isSourceBillHasAllocate() throws Exception {
		String id = getSelectedKeyValue();
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from T_AP_OtherBillentry a")
			.append(" where exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b")
			.append(" where b.fid=a.FSourceBillEntryId and b.CFAllocateCount<>a.CFSourceEntryAllocateCount)")
			.append(" and a.FCoreBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI='")
			.append(String.format(" and a.FParentID='%s'",id));
		IRowSet rs = DBUtils.executeQueryForDialect(null, sql.toString());
		if (rs != null && rs.next()) return true;
		return false;
	}
	
	

}
