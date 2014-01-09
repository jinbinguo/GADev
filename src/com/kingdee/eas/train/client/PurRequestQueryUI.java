/**
 * output package name
 */
package com.kingdee.eas.train.client;

import java.util.Date;

import org.apache.log4j.Logger;

import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class PurRequestQueryUI extends AbstractPurRequestQueryUI
{
    private static final Logger logger = CoreUIObject.getLogger(PurRequestQueryUI.class);
    
    /**
     * output class constructor
     */
    public PurRequestQueryUI() throws Exception
    {
        super();
    }

    /**
     * output storeFields method
     */
    public void storeFields()
    {
        super.storeFields();
    }

   
    @Override
    public FilterInfo getFilterInfo() {
    	FilterInfo filter = new FilterInfo();
		PurchaseOrgUnitInfo purOrgInfo = (PurchaseOrgUnitInfo) prmtPurOrg.getData();
		MaterialInfo materialInfo = (MaterialInfo) prmtMaterial.getData();
		
		
		Date beginDate = (Date) kDDateBizDateFrom.getValue();
		if (beginDate != null) {
			beginDate.setHours(23);
			beginDate.setMinutes(59);
			beginDate.setSeconds(59);
			beginDate.setDate(beginDate.getDate()-1);
		}

		
		Date endDate = (Date) kDDateBizDateTo.getValue();
		if (endDate != null) {
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
		}
		
		if (purOrgInfo != null) {
			filter.getFilterItems().add(new FilterItemInfo("purOrg.id",purOrgInfo.getId().toString()));
		}
		if (materialInfo != null) {
			filter.getFilterItems().add(new FilterItemInfo("entry.material.id", materialInfo.getId().toString()));
		}
		if (beginDate != null) {
			filter.getFilterItems().add(new FilterItemInfo("bizDate", beginDate, CompareType.GREATER_EQUALS));
		}
		if (endDate != null) {
			filter.getFilterItems().add(new FilterItemInfo("bizDate", endDate, CompareType.LESS_EQUALS));
		}
			
		
		
		return filter;

    }
    
    @Override
    public boolean verify() {
    	
    	boolean b =  super.verify();
    	if (!b) return b;
    	if (prmtPurOrg.getData() == null)  {
    		MsgBox.showInfo("采购组织不能为空！");
    		return false;
    	}
    	
    	
    	return b;
    	
    }
    
    
    
    
}