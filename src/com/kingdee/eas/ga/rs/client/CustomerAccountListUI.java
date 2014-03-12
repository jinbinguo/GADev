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

}