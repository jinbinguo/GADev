/**
 * output package name
 */
package com.kingdee.eas.ga.basedata.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.dao.query.IQueryExecutor;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.myframework.util.KDTableUtils;

/**
 * output class name
 */
public class CustomerDiscountListUI extends AbstractCustomerDiscountListUI
{
    private static final Logger logger = CoreUIObject.getLogger(CustomerDiscountListUI.class);
    

    public CustomerDiscountListUI() throws Exception  {
        super();
    }
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	KDTableUtils.formatDecimal(tblMain, "entrys.repairDiscountRate", false);
    	KDTableUtils.formatDecimal(tblMain, "entrys.retailDiscountRate", false);

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
    
    @Override
    public boolean isAutoIgnoreZero() {
    	return false;
    }

}