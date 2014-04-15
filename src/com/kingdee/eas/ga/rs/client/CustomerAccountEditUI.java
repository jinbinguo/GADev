/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import java.awt.event.*;
import org.apache.log4j.Logger;

import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsUtils;
import com.kingdee.eas.basedata.org.AdminOrgUnit;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.myframework.util.KDTableUtils;
import com.kingdee.eas.myframework.util.OrgUtils;

/**
 * output class name
 */
public class CustomerAccountEditUI extends AbstractCustomerAccountEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(CustomerAccountEditUI.class);
    

    public CustomerAccountEditUI() throws Exception {
        super();
        
        
    }
    
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	txtretailDiscountRate.setMaximumValue(100);
        txtretailDiscountRate.setMinimumValue(0);
        
        txtrepairDiscountRate.setMaximumValue(100);
        txtrepairDiscountRate.setMinimumValue(0);
        
      
        AdminOrgUnitInfo orgUnitInfo = (AdminOrgUnitInfo) prmtorgUnit.getValue();
        if (orgUnitInfo != null) {
        
	        EntityViewInfo entityATSCustomer = new EntityViewInfo();
	        FilterInfo filterATSCustomer = new FilterInfo();
	        filterATSCustomer.getFilterItems().add(new FilterItemInfo("orgUnit.id",orgUnitInfo.getString("id")));
	        entityATSCustomer.setFilter(filterATSCustomer);
	        prmtatsCustomer.setEntityViewInfo(entityATSCustomer);
	        
	        
	        EntityViewInfo entityFinCustomer = new EntityViewInfo();
	        FilterInfo filterFinCustomer = new FilterInfo();
	        filterFinCustomer.getFilterItems().add(new FilterItemInfo("companyOrgUnit.id",orgUnitInfo.getString("id")));
	        prmtfinCustomer.setEntityViewInfo(entityFinCustomer);
        }
        
    }
  

    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception  {
        return com.kingdee.eas.ga.rs.CustomerAccountFactory.getRemoteInstance();
    }


    protected com.kingdee.bos.dao.IObjectValue createNewData()  {
        com.kingdee.eas.ga.rs.CustomerAccountInfo objectValue = new com.kingdee.eas.ga.rs.CustomerAccountInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        ServiceOrgUnitInfo orgUnitInfo = RsUtils.getServiceOrgUnitInfo();
		if (orgUnitInfo.isIsBizUnit())
			objectValue.setOrgUnit(OrgUtils.castToAmin(orgUnitInfo));
        
        return objectValue;
    }

}