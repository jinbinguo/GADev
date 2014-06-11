package com.kingdee.eas.scm.im.inv.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUIPIEx;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.StorageOrgUnitInfo;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class OtherInWarehsBillEditUIPIEx extends OtherInWarehsBillEditUI {

	public OtherInWarehsBillEditUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		actionSave.setVisible(false);
		if (PublicUtils.equals(OprtState.EDIT, getOprtState()) && isSourceBillHasAllocate()) {
			lockUIForViewStatus();
			MsgBoxEx.showInfo("��Դά�޹��������˷��÷ֵ������������޸�������ⵥ���ݣ�");
		}
		kdtEntry.getColumn("unitFactCost").setRequired(true);
	}
	
	@Override
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		super.actionSubmit_actionPerformed(e);
		if (RepairWOEditUIPIEx.rwoUI != null) {
			RepairWOEditUIPIEx.rwoUI.actionRefresh_actionPerformed(e);
			RepairWOEditUIPIEx.rwoUI = null;
		}
		getUIWindow().close();
	}
	
	@Override
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		super.actionEdit_actionPerformed(e);
		if (isSourceBillHasAllocate()) {
			MsgBoxEx.showInfo("��Դά�޹��������˷��÷ֵ������������޸�������ⵥ���ݣ�");
			lockUIForViewStatus();
		}
	}
	
	public void actionRemove_actionPerformed(ActionEvent arg0) throws Exception {
		if (isSourceBillHasAllocate()) {
				MsgBoxEx.showInfo("��Դά�޹��������˷��÷ֵ���������ɾ��������ⵥ��");
				return;
		}
		super.actionRemove_actionPerformed(arg0);
	}
	
	private boolean isSourceBillHasAllocate() throws Exception {
		if (PublicUtils.isEmpty(editData.getString("id"))) return false;
		StringBuilder sql = new StringBuilder();
		sql.append("select 1 from T_IM_OtherInWarehsBillEntry a")
			.append(" where exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b")
			.append(" where b.fid=a.FSourceBillEntryId and b.CFAllocateCount<>a.CFSourceEntryAllocateCount)")
			.append(" and a.FSourceBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI='")
			.append(String.format(" and a.FParentID='%s'",editData.getString("id")));
		IRowSet rs = DBUtils.executeQueryForDialect(null, sql.toString());
		if (rs != null && rs.next()) return true;
		return false;
	}
	
	@Override
	protected IObjectValue createNewData() {
		IObjectValue value = super.createNewData();
		
		value.put("TransactionType",null);
		return value;
	}
	
	@Override
	protected void verifyInput(ActionEvent actionevent) throws Exception {
		super.verifyInput(actionevent);
		for (int i = 0; i < kdtEntry.getRowCount(); i++) {
			IRow row = kdtEntry.getRow(i);
			BigDecimal unitFactCost = (BigDecimal) row.getCell("unitFactCost").getValue();
			if (unitFactCost == null) {
				MsgBoxEx.showInfo("��λʵ�ʳɱ�����Ϊ��!");
				SysUtil.abort();
			}
			
		}
	}
	
	@Override
	public void setColumnProperty(MaterialInfo materialInfo, int row, int col,
			boolean isOnload) throws Exception {
		super.setColumnProperty(materialInfo, row, col, isOnload);
		if (materialInfo != null && !isOnload) {
			StorageOrgUnitInfo orgUnitInfo = (StorageOrgUnitInfo) prmtStorageOrgUnit.getValue();
			if (orgUnitInfo == null) return;
			String sql = String.format("select isnull(FPrice,0) FPrice from T_BD_MaterialPurchasing where FOrgUnit='%s' and FMaterialID='%s'", 
					orgUnitInfo.getString("id"),materialInfo.getString("id"));
			IRowSet rs = DBUtils.executeQuery(null, sql);
			if (rs != null && rs.next()) {
				BigDecimal price = rs.getBigDecimal("FPrice");
				kdtEntry.getRow(row).getCell("referCost").setValue(price);
			}
			
		}
	}

}
