
package com.kingdee.eas.ga.syncdata.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.KDTSortManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.ui.face.CoreUIObject;


public class DMSWipBillEditUI extends AbstractDMSWipBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(DMSWipBillEditUI.class);
    

    public DMSWipBillEditUI() throws Exception {
        super();
    }

    public void onLoad() throws Exception {
    	super.onLoad();
    	btnMultiColumnSort.setVisible(false);
    }

    @Override
    protected void initListener() {
    	super.initListener();
    	for(int i = 0; i < kdtEntrys.getColumnCount(); i++)
    		kdtEntrys.getColumn(i).setSortable(false);
    }

 
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception {
        return com.kingdee.eas.ga.syncdata.DMSWipBillFactory.getRemoteInstance();
    }

 
    protected IObjectValue createNewDetailData(KDTable table) {
		
        return new com.kingdee.eas.ga.syncdata.DMSWipBillEntryInfo();
    }


    protected com.kingdee.bos.dao.IObjectValue createNewData() {
        com.kingdee.eas.ga.syncdata.DMSWipBillInfo objectValue = new com.kingdee.eas.ga.syncdata.DMSWipBillInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }

}