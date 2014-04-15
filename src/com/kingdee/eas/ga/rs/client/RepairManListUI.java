/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import java.awt.event.*;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ctrl.kdf.data.event.RequestRowSetEvent;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsQueryF7Utils;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.myframework.util.KDTableUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;

/**
 * output class name
 */
public class RepairManListUI extends AbstractRepairManListUI {
	private static final Logger logger = CoreUIObject.getLogger(RepairManListUI.class);

	public RepairManListUI() throws Exception {
		super();
	}
	
  public void onLoad() throws Exception {
    	super.onLoad();
    	RsQueryF7Utils.makeVehicleF7(prmtVehicle);
    
    	KDTableUtils.setColumnMerge(tblMain, getMergeColumnKeys());
    	//KDTableUtils.setPageRowCount(tblMain, 150);
    	logger.info("=============onload===================");
    	actionAudit.setVisible(false);
    	actionUnAudit.setVisible(false);
    	
    }

	protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception {
		return com.kingdee.eas.ga.rs.RepairManFactory.getRemoteInstance();
	}

	protected String getKeyFieldName() {
		return "id";
	}


	protected com.kingdee.bos.dao.IObjectValue createNewData() {
		com.kingdee.eas.ga.rs.RepairManInfo objectValue = new com.kingdee.eas.ga.rs.RepairManInfo();

		return objectValue;
	}
	
	public String[] getMergeColumnKeys() {
		return new String[] {
			"id","number","name","tel","email","addr","zipCode","idNumber"
			//,"entrys.vehicle.number",
			//"plateNum","vIN","engineNum","seriesName",
			//"modelName","brandName"
		};

	}
	@Override
	public void actionSearch_actionPerformed(ActionEvent e) throws Exception {
		execQuery();
	}
	
	@Override
	protected void tblMain_doRequestRowSet(RequestRowSetEvent e) {
		try {
			FilterInfo filterInfo =  super.getDefaultFilterForQuery();
			FilterInfo myFilterInfo = new FilterInfo();
			VehicleInfo vehicleInfo = (VehicleInfo) prmtVehicle.getValue();
			if (vehicleInfo != null) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("entrys_vehicle.id",vehicleInfo.getString("id")));
			}
			String plateNum = txtPlateNum.getText();
			if (!PublicUtils.isEmpty(plateNum)) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("entrys_vehicle.plateNum","%" + plateNum + "%",CompareType.LIKE));
			}
			
			String senderName = txtSender.getText();
			if (!PublicUtils.isEmpty(senderName)) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("name","%" + senderName + "%",CompareType.LIKE));
			}
			String tel = txtTel.getText();
			if (!PublicUtils.isEmpty(tel)) {
				myFilterInfo.getFilterItems().add(new FilterItemInfo("tel","%" + tel + "%",CompareType.LIKE));
			}
			
			AdminOrgUnitInfo adminOrgUnitInfo = SysContext.getSysContext().getCurrentAdminUnit();
	    	myFilterInfo.getFilterItems().add(new FilterItemInfo("orgUnit.id",adminOrgUnitInfo.getString("id")));
	
			

			filterInfo.mergeFilter(myFilterInfo, "and");
			mainQuery.setFilter(filterInfo);
		} catch (Exception exc) {
			UIUtils.handUIExceptionAndAbort(exc);
		}
		super.tblMain_doRequestRowSet(e);

	}
	
    @Override
    protected FilterInfo getDefaultFilterForQuery() {
    	return super.getDefaultFilterForQuery();
    	
    }

}