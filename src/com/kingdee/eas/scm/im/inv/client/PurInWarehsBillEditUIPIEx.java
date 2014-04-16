package com.kingdee.eas.scm.im.inv.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.org.StorageOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.OrgUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.jdbc.rowset.IRowSet;

public class PurInWarehsBillEditUIPIEx extends PurInWarehsBillEditUI {
	
	private PurchaseOrgUnitInfo purchaseOrgUnitInfo = null;

	public PurInWarehsBillEditUIPIEx() throws Exception {
		super();
	}
	
	private BigDecimal getNotTaxPrice(MaterialInfo materialInfo) throws Exception {

		StorageOrgUnitInfo storageOrgUnitInfo = (StorageOrgUnitInfo) bizPromptStorageOrgUnit.getValue();
		if (storageOrgUnitInfo == null) return null;
		
		if (purchaseOrgUnitInfo == null)
			purchaseOrgUnitInfo = OrgUtils.castToPurchaseOrg(OrgUtils.getOrgUnitInfoByRelation(null, storageOrgUnitInfo, OrgType.Storage, OrgType.Purchase, true));

		String sql = String.format("select isnull(FPrice,0) FPrice from T_BD_MaterialPurchasing where FMaterialID='%s' and FOrgUnit='%s'",
				materialInfo.getString("id"),purchaseOrgUnitInfo.getString("id"));
		IRowSet rs = DBUtils.executeQuery(null, sql);
		if (rs != null && rs.next()) {
			return rs.getBigDecimal("FPrice");
		} else {
			return BigDecimal.ZERO;
		}
	}


    @Override
    protected void dealwithEntryByPriceStrategy(int beginRow, int length) {
    	super.dealwithEntryByPriceStrategy(beginRow, length);
    	
		try { 
	    	for(int i = 0; i < length; i++) {
	    		IRow curRow = detailTable.getRow(beginRow + i);
	    		MaterialInfo materialInfo = (MaterialInfo)curRow.getCell(TB_MATERIAL).getValue();
	    		BigDecimal price = PublicUtils.getBigDecimal(curRow.getCell("price").getValue());
	    		if(materialInfo != null && price.compareTo(BigDecimal.ZERO) == 0) {
	    			//�������޸�״̬ʱ���޸�����
	 
    				BigDecimal taxRate = PublicUtils.getBigDecimal(curRow.getCell("taxRate").getValue());
    				if (taxRate.compareTo(BigDecimal.ZERO) == 0) {
    					taxRate = new BigDecimal(17.00);
    					curRow.getCell("taxRate").setValue(17.00);
    				}
    				if (materialInfo != null ) {
    					BigDecimal notTaxPrice = getNotTaxPrice(materialInfo);
    					curRow.getCell("price").setValue(notTaxPrice);
    					BigDecimal taxPrice = notTaxPrice.multiply(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    					curRow.getCell("taxPrice").setValue(taxPrice);	
    				}
	    		}
	    	}
	    		
    	} catch (Exception e) {
			UIUtils.handUIException(e);
		}
    }

}