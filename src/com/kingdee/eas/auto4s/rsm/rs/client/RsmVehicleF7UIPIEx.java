package com.kingdee.eas.auto4s.rsm.rs.client;

import java.awt.event.ActionEvent;
import java.util.Map;

import com.ibm.db2.jcc.am.de;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.auto4s.bdm.pbd.IVehicle;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.util.client.KDTableUtil;

public class RsmVehicleF7UIPIEx extends RsmVehicleF7UI {
	
	private boolean isAutoReturn = false;
	private boolean isNotNeedShow = false; 
	public RsmVehicleF7UIPIEx() throws Exception {
		super();
	}

	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		Map ctx = getUIContext();
		Integer selIndex = (Integer) ctx.get("selIndex");
		if (selIndex != null) kDSel.setSelectedIndex(selIndex);
		String value = (String)ctx.get("value");
		isAutoReturn = ctx.get("isAutoReturn") == null ? false : (Boolean)ctx.get("isAutoReturn");
		if (value != null) {
			txtValue.setText(value);
			btnFastQuery_actionPerformed(null);
		}
		
	}
	
	@Override
	protected void btnFastQuery_actionPerformed(ActionEvent e) throws Exception {
		txtValue.setText(txtValue.getText().trim());
		super.btnFastQuery_actionPerformed(e);
		if (isAutoReturn && !PublicUtils.isEmpty(txtValue.getText()) && tblMain.getRowCount() == 1) {
			tblMain.getSelectManager().select(0, 0);
			int rowID[] = KDTableUtil.getSelectedRows(tblMain);
			 if(rowID.length > 0) {	 
				IVehicle iVehicle = VehicleFactory.getRemoteInstance();
				String strID = tblMain.getRow(rowID[0]).getCell("id").getValue().toString();
				vehicleInfo = iVehicle.getVehicleInfo(new ObjectUuidPK(BOSUuid.read(strID)));
				setCanceled(false);
				isNotNeedShow = true;
			 }
		}
		isAutoReturn = false;
	}
	
	public boolean isNotNeedShow() {
		return isNotNeedShow;
	}
	

}
