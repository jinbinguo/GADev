package com.kingdee.eas.scm.sm.pur.client;

import java.math.BigDecimal;

import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.jdbc.rowset.IRowSet;

public class PurOrderEditUIPIEx extends PurOrderEditUI {

	public PurOrderEditUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		
		if (isBotpBill()) {
			int qtyIndex = kdtEntries.getColumnIndex("qty");
			for (int i = 0; i < kdtEntries.getRowCount(); i++) {
				MaterialInfo materialInfo = (MaterialInfo) kdtEntries.getRow(i).getCell("materialNum").getValue();
				BigDecimal taxRate = PublicUtils.getBigDecimal(kdtEntries.getRow(i).getCell("taxRate").getValue());
				BigDecimal taxPrice = PublicUtils.getBigDecimal(kdtEntries.getRow(i).getCell("taxprice1").getValue());
				BigDecimal qty = PublicUtils.getBigDecimal(kdtEntries.getRow(i).getCell("qty").getValue());
				if (taxPrice.compareTo(BigDecimal.ZERO) > 0)  continue;
				BigDecimal notTaxPrice = getNotTaxPrice(materialInfo); 
				taxPrice = notTaxPrice.multiply(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
				kdtEntries.getRow(i).getCell("taxprice1").setValue(taxPrice);
				//kdtEntries.getRow(i).getCell("taxPrice").setValue(taxPrice);
				
				KDTEditEvent event =  new KDTEditEvent(kdtEntries,0,qty,i,qtyIndex,false,0);
				kdtEntries_EditStopped(event);
				
			}
			
			
		}
	}

	@Override
	public void setEntryAssistProperty(int rowIndex, boolean isLoad) {
		super.setEntryAssistProperty(rowIndex, isLoad);
		try { 
			MaterialInfo materialInfo = (MaterialInfo) kdtEntries.getRow(rowIndex).getCell("materialNum").getValue();
			BigDecimal taxRate = PublicUtils.getBigDecimal(kdtEntries.getRow(rowIndex).getCell("taxRate").getValue());
			if (materialInfo != null) {
				BigDecimal notTaxPrice = getNotTaxPrice(materialInfo);
				BigDecimal taxPrice = notTaxPrice.multiply(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
				kdtEntries.getRow(rowIndex).getCell("taxprice1").setValue(taxPrice);
			}
			
		} catch (Exception e) {
			UIUtils.handUIException(e);
		}
	
	}
	
	private BigDecimal getNotTaxPrice(MaterialInfo materialInfo) throws Exception {
		PurchaseOrgUnitInfo purchaseOrgUnitInfo = (PurchaseOrgUnitInfo) prmtPurchaseOrgUnit.getValue();
		if (purchaseOrgUnitInfo == null) return null;
		String sql = String.format("select FPrice from T_BD_MaterialPurchasing where FMaterialID='%s' and FOrgUnit='%s'",
				materialInfo.getString("id"),purchaseOrgUnitInfo.getString("id"));
		IRowSet rs = DBUtils.executeQuery(null, sql);
		if (rs != null && rs.next()) {
			return rs.getBigDecimal("FPrice");
		} else {
			return null;
		}
	}
}
