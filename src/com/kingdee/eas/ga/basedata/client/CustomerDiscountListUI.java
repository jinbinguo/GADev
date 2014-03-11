/**
 * output package name
 */
package com.kingdee.eas.ga.basedata.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ui.face.CoreUIObject;

/**
 * output class name
 */
public class CustomerDiscountListUI extends AbstractCustomerDiscountListUI
{
    private static final Logger logger = CoreUIObject.getLogger(CustomerDiscountListUI.class);
    

    public CustomerDiscountListUI() throws Exception  {
        super();
    }

   
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception  {
        return com.kingdee.eas.ga.basedata.CustomerDiscountFactory.getRemoteInstance();
    }


    protected String getKeyFieldName()  {
        return "id";
    }


    protected com.kingdee.bos.dao.IObjectValue createNewData() {
        com.kingdee.eas.ga.basedata.CustomerDiscountInfo objectValue = new com.kingdee.eas.ga.basedata.CustomerDiscountInfo();
		
        return objectValue;
    }

}