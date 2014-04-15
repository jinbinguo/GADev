/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import java.awt.event.*;
import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.myframework.util.KDTableUtils;

/**
 * output class name
 */
public class CustomerAccountListUI extends AbstractCustomerAccountListUI
{
    private static final Logger logger = CoreUIObject.getLogger(CustomerAccountListUI.class);
    

    public CustomerAccountListUI() throws Exception {
        super();
    }
    
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	KDTableUtils.formatDecimal(tblMain, "retailDiscountRate", false);
    	KDTableUtils.formatDecimal(tblMain, "repairDiscountRate", false);
    }
    
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception {
        return com.kingdee.eas.ga.rs.CustomerAccountFactory.getRemoteInstance();
    }

    protected com.kingdee.bos.dao.IObjectValue createNewData()
    {
        com.kingdee.eas.ga.rs.CustomerAccountInfo objectValue = new com.kingdee.eas.ga.rs.CustomerAccountInfo();
		
        return objectValue;
    }
    
    @Override
    protected FilterInfo getDefaultFilterForQuery() {
    	FilterInfo filterInfo =  super.getDefaultFilterForQuery();
    	if (filterInfo == null) filterInfo = new FilterInfo();
    	AdminOrgUnitInfo adminOrgUnitInfo = SysContext.getSysContext().getCurrentAdminUnit();
    	
    	FilterInfo otherFilterInfo = new FilterInfo();
    	otherFilterInfo.getFilterItems().add(new FilterItemInfo("orgUnit.id",adminOrgUnitInfo.getString("id")));
    	try {
			filterInfo.mergeFilter(otherFilterInfo, "AND");
		} catch (BOSException e) {
			e.printStackTrace();
		}
    	return filterInfo;
    }

}