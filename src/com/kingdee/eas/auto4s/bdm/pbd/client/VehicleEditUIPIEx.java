/**
 * output package name
 */
package com.kingdee.eas.auto4s.bdm.pbd.client;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;

/**
 * output class name
 */
public class VehicleEditUIPIEx extends VehicleEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(VehicleEditUIPIEx.class);
    

    public VehicleEditUIPIEx() throws Exception  {
        super();
    }
    
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	
    	
    }
    
    @Override
    public SelectorItemCollection getSelectors() {
    	SelectorItemCollection sic = super.getSelectors();
		sic.add(new SelectorItemInfo("RepairSender.*"));
		sic.add(new SelectorItemInfo("RepairRemark.*"));
    	return sic;
    }
    
    @Override
    protected void beforeStoreFields(ActionEvent arg0) throws Exception {
    	super.beforeStoreFields(arg0);
    	for (int i=0,n=kdtRepairSender.getRowCount();i<n;i++) {
			if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(kdtRepairSender.getCell(i,"name").getValue())) {
				throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"ËÍÐÞÈËÐÕÃû"});
			}
		}
    }
    
   


}