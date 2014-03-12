/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import java.awt.event.*;
import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.myframework.util.KDTableUtils;

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
       
    }
  

    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception  {
        return com.kingdee.eas.ga.rs.CustomerAccountFactory.getRemoteInstance();
    }


    protected com.kingdee.bos.dao.IObjectValue createNewData()  {
        com.kingdee.eas.ga.rs.CustomerAccountInfo objectValue = new com.kingdee.eas.ga.rs.CustomerAccountInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }

}