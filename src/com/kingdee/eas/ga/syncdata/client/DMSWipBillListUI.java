/**
 * output package name
 */
package com.kingdee.eas.ga.syncdata.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ui.face.CoreUIObject;

/**
 * output class name
 */
public class DMSWipBillListUI extends AbstractDMSWipBillListUI
{
    private static final Logger logger = CoreUIObject.getLogger(DMSWipBillListUI.class);
    
    public DMSWipBillListUI() throws Exception  {
        super();
    }

    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception  {
        return com.kingdee.eas.ga.syncdata.DMSWipBillFactory.getRemoteInstance();
    }


    protected String getKeyFieldName() {
        return "id";
    }

    protected com.kingdee.bos.dao.IObjectValue createNewData() {
        com.kingdee.eas.ga.syncdata.DMSWipBillInfo objectValue = new com.kingdee.eas.ga.syncdata.DMSWipBillInfo();
		
        return objectValue;
    }

}