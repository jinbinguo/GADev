package com.kingdee.eas.auto4s.bdm.rsm.client;

import java.awt.event.ActionEvent;

import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;

public class RepairItemEditUIPIEx extends RepairItemEditUI {

	public RepairItemEditUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		super.actionSubmit_actionPerformed(e);
		if (editData != null && PublicUtils.equals(OprtState.EDIT, getOprtState()) && !PublicUtils.isEmpty(editData.getString("id"))) {
			//编辑状态下，修改项目编码,更新维修工单物料
			String sql = String.format("update a " +
				"set a.CFItemSPNum=(select b.FNumber from T_ATS_RepairItem b where b.FID=a.CFRepairItemID) " +
				"from CT_ATS_RepairWORWOItemSpEntry a " +
				"where a.CFItemspNum<>(select b.FNumber from T_ATS_RepairItem b where b.FID=a.CFRepairItemID) " +
				"and a.CFRepairItemID='%s'", editData.getString("id"));
			DBUtils.executeForDialect(null, sql);
			
		}
	}
	@Override
	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		
		
		super.actionSave_actionPerformed(e);
		
	}
	
	

}
