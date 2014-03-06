/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import java.awt.event.*;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.kingdee.bos.metadata.data.SortType;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.ItemAction;
import com.kingdee.bos.ctrl.kdf.data.event.RequestRowSetEvent;
import com.kingdee.bos.ctrl.kdf.table.KDTDataRequestManager;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.actions.superman.ActionAddDistribution;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairSenderFactory;
import com.kingdee.eas.auto4s.bdm.pbd.client.VehicleEditUI;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsQueryF7Utils;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.eas.myframework.util.KDTableUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;

/**
 * output class name
 */
public class RepairSenderListUI extends AbstractRepairSenderListUI
{
    private static final Logger logger = CoreUIObject.getLogger(RepairSenderListUI.class);

    public RepairSenderListUI() throws Exception  {
        super();
        
    }
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	actionAddNew.setVisible(false);
    	actionEdit.setVisible(false);
    	actionRemove.setVisible(false);
    	actionRefresh.setVisible(false);
    	actionQuery.setVisible(false);
    	actionLocate.setVisible(false);
    	actionPrint.setVisible(false);
    	actionPrintPreview.setVisible(false);
    	
    	RsQueryF7Utils.makeVehicleF7(prmtVehicle);
    	UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
    	RsQueryF7Utils.makeOrgUnitF7(userInfo, prmtOrgUnit);
    	
    	KDTableUtils.setColumnMerge(tblMain, getMergeColumnKeys());
    	KDTableUtils.setPageRowCount(tblMain, 150);
    	
    }
    public void storeFields()   {
        super.storeFields();
    }

	protected ICoreBase getBizInterface() throws Exception {
		return VehicleFactory.getRemoteInstance();
	}

	protected String getEditUIName() {
		return VehicleEditUI.class.getName();
	} 
	
	@Override
	public void actionSearch_actionPerformed(ActionEvent e) throws Exception {
		execQuery();
	}
	
	@Override
	protected void tblMain_tableClicked(KDTMouseEvent e) throws Exception {
        if(e.getType() == 0 && tblMain.getColumn(e.getColIndex()).getKey() != null 
        		&& tblMain.getColumn(e.getColIndex()).getKey().endsWith("attacheMentId"))  {
        	tblMain.getHeadRow(0).getCell(e.getColIndex()).setRenderer(null);
        	return;
        }
        if(isOrderForClickTableHead() && e.getType() == 0 && e.getButton() == 1 && e.getClickCount() == 1) {
        	Method mOrderByForTable = InvokeUtils.getMethod(this, "OrderByForTable", new Class[] {KDTMouseEvent.class});
        	InvokeUtils.invokeMethod(this, mOrderByForTable, new Object[] {e});
        	return;
        }
	}
	
	@Override
	protected void tblMain_doRequestRowSet(RequestRowSetEvent e) {
		try {
			FilterInfo filterInfo =  super.getDefaultFilterForQuery();
			FilterInfo myFilterInfo = new FilterInfo();
			VehicleInfo vehicleInfo = (VehicleInfo) prmtVehicle.getValue();
			if (vehicleInfo != null) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("id",vehicleInfo.getString("id")));
			}
			String plateNum = txtPlateNum.getText();
			if (!PublicUtils.isEmpty(plateNum)) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("plateNum","%" + plateNum + "%",CompareType.LIKE));
			}
			
			String senderName = txtSender.getText();
			if (!PublicUtils.isEmpty(senderName)) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("RepairSender.name","%" + senderName + "%",CompareType.LIKE));
			}
			String tel = txtTel.getText();
			if (!PublicUtils.isEmpty(tel)) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("RepairSender.tel","%" + tel + "%",CompareType.LIKE));
			}
			

			filterInfo.mergeFilter(myFilterInfo, "and");
			mainQuery.setFilter(filterInfo);
		} catch (Exception exc) {
			UIUtils.handUIExceptionAndAbort(exc);
		}
		super.tblMain_doRequestRowSet(e);
	}
	
	 
	private String[] getMergeColumnKeys() {
		return new String[] {
			"id","number","plateNum","vIN","engineNum","series.name",
			"model.name","brand.name","orgUnit.name"
		};
	}
}