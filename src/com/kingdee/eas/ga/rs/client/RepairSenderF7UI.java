/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import java.awt.event.ActionEvent;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTView;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectListener;
import com.kingdee.bos.ctrl.swing.KDPromptSelector;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairSenderCollection;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairSenderFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairSenderInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.rptclient.newrpt.util.MsgBox;
import com.kingdee.eas.scm.common.client.KDTableUtils;
import com.kingdee.util.NumericExceptionSubItem;

/**
 * output class name
 */
public class RepairSenderF7UI extends AbstractRepairSenderF7UI implements KDPromptSelector {
    private static final Logger logger = CoreUIObject.getLogger(RepairSenderF7UI.class);

    private IVehicleRepairSender repairSender = VehicleRepairSenderFactory.getRemoteInstance();
    private VehicleRepairSenderInfo curRepairSenderInfo = null; //当前维修人(用于回写及初始选中)
    private VehicleRepairSenderInfo selRepairSenderInfo = null; //选中维修人(用于编辑保存)
    private boolean isViewMode = true; //查看方式
    private int selRowIndex = 0;
    
    public RepairSenderF7UI() throws Exception {
        super();
    }

    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	txtName.setRequired(true);
    	Map uictx = getUIContext();
    	VehicleInfo vehicleInfo = (VehicleInfo) uictx.get("vehicleInfo");
		String vehicleId = vehicleInfo.getString("id") ;
		curRepairSenderInfo = (VehicleRepairSenderInfo) uictx.get("curRepairSenderInfo");
		
    	tblMain.checkParsed(true);
    	Query(vehicleId);
    	
    	tblMain.getSelectManager().setSelectMode(KDTSelectManager.ROW_SELECT);
    	tblMain.addKDTSelectListener(new KDTSelectListener() {

			public void tableSelectChanged(KDTSelectEvent event) {
				if (!isViewMode) return;
				KDTSelectBlock kdtSB = event.getSelectBlock();
				if (kdtSB == null) return;
				int rowIndex = kdtSB.getBeginRow();
			
				IRow row = tblMain.getRow(rowIndex);
				selRepairSenderInfo = (VehicleRepairSenderInfo) row.getUserObject();
				txtName.setText(selRepairSenderInfo.getName());
				txtTel.setText(selRepairSenderInfo.getTel());
				txtAddr.setText(selRepairSenderInfo.getAddr());
				txtEmail.setText(selRepairSenderInfo.getEmail());
				txtIdNumber.setText(selRepairSenderInfo.getIdNumber());
				txtZipCode.setText(selRepairSenderInfo.getZipCode());
				
			}    		
    	});
    	tblMain.addKDTMouseListener(new KDTMouseListener() {
			public void tableClicked(KDTMouseEvent event) {
				if (!isViewMode) return;
				if (!PublicUtils.equals(event.getOriginView().getClass().getName(),
						KDTView.class.getName()))  return;
				if (event.getClickCount() == 2) {
					curRepairSenderInfo = selRepairSenderInfo;
					destroyWindow();
					
				}
				
				
			}
    		
    	});
 
    	
    	setUIEnable(false);
    	
    	for (int i = 0; curRepairSenderInfo != null && i < tblMain.getRowCount();i++) {
    		IRow row = tblMain.getRow(i);
    		if (PublicUtils.equals(curRepairSenderInfo,row.getUserObject())) {
    			tblMain.getSelectManager().select(i, 1, 2);
    			break;
    		}
    	}
    	
    
    }
    
    private void setUIEnable(boolean enabled) {
    	txtName.setEditable(enabled);
    	txtTel.setEnabled(enabled);
    	txtAddr.setEnabled(enabled);
    	txtEmail.setEnabled(enabled);
    	txtIdNumber.setEnabled(enabled);
    	txtZipCode.setEnabled(enabled);
    	
    	actionAddNew.setEnabled(!enabled);
    	actionSave.setEnabled(enabled);
    	actionEdit.setEnabled(!enabled);
    	actionDelete.setEnabled(!enabled);
    	actionCancel.setEnabled(enabled);
    	btnCancel.setVisible(enabled);
    	
    }
    

    private void Query(String vehicleId) throws Exception {
    	VehicleRepairSenderCollection repairSenderCol = 
    		repairSender.getVehicleRepairSenderCollection(String.format("where parent.id='%s' order by seq",vehicleId));
    	tblMain.removeRows();
    	for (int i = 0; repairSenderCol != null && i < repairSenderCol.size(); i++) {
    		VehicleRepairSenderInfo repairSenderInfo = repairSenderCol.get(i);
    		IRow row = tblMain.addRow();
    		row.setUserObject(repairSenderInfo);
    		row.getCell("id").setValue(repairSenderInfo.getId());
    		row.getCell("parentid").setValue(repairSenderInfo.getParent());
    		row.getCell("name").setValue(repairSenderInfo.getName());
    		row.getCell("tel").setValue(repairSenderInfo.getTel());
    		row.getCell("addr").setValue(repairSenderInfo.getAddr());
    		row.getCell("zipcode").setValue(repairSenderInfo.getZipCode());
    		row.getCell("email").setValue(repairSenderInfo.getEmail());
    		row.getCell("idNumber").setValue(repairSenderInfo.getIdNumber());
    	}
    	
    }

	public Object getData() {
		return curRepairSenderInfo;
	}

	public boolean isCanceled() {
		return false;
	}
	
	@Override
	public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		
		selRowIndex = getSelectRowIndex();
		
		isViewMode = false;
		selRepairSenderInfo = new VehicleRepairSenderInfo();
		txtName.setText("");
		txtTel.setText("");
		txtAddr.setText("");
		txtEmail.setText("");
		txtZipCode.setText("");
		txtIdNumber.setText("");
		setUIEnable(true);
		
	}
	@Override
	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		if (PublicUtils.isEmpty(txtName.getText())) {
			MsgBoxEx.showInfo("姓名不能为空！");
			return;
		}
		
		Map uictx = getUIContext();
		VehicleInfo vehicleInfo = (VehicleInfo) uictx.get("vehicleInfo");
		String vehicleId = vehicleInfo.getString("id") ;
    	
		
		selRepairSenderInfo.setName(txtName.getText());
		selRepairSenderInfo.setTel(txtTel.getText());
		selRepairSenderInfo.setAddr(txtAddr.getText());
		selRepairSenderInfo.setEmail(txtEmail.getText());
		selRepairSenderInfo.setZipCode(txtZipCode.getText());
		selRepairSenderInfo.setIdNumber(txtIdNumber.getText());
		selRepairSenderInfo.setParent(vehicleInfo);
		repairSender.save(selRepairSenderInfo);
		MsgBoxEx.showInfo("保存成功！");
    	setUIEnable(false);
    	isViewMode = true;
    	
    	Query(vehicleId);
    	if (selRowIndex > -1) {
			tblMain.getSelectManager().select(selRowIndex, 1, 2);
		} else {
			if (tblMain.getRowCount() > 0)
				tblMain.getSelectManager().select(0, 1, 2);
		}
		
	}
	@Override
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		checkSelectRow();
		isViewMode = false;
		selRowIndex = getSelectRowIndex();
		setUIEnable(true);
		
	}
	@Override
	public void actionDelete_actionPerformed(ActionEvent e) throws Exception {
		checkSelectRow();
		if (MsgBoxEx.showConfirm2("是否删除？") != MsgBox.YES) return;
		Map uictx = getUIContext();
		VehicleInfo vehicleInfo = (VehicleInfo) uictx.get("vehicleInfo");
		String vehicleId = vehicleInfo.getString("id") ;
		
		IObjectPK pk = new ObjectUuidPK(selRepairSenderInfo.getString("id"));
		repairSender.delete(pk);
		MsgBoxEx.showInfo("删除成功！");
		Query(vehicleId);
	}
	@Override
	public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
		if (MsgBoxEx.showConfirm2("是否撤销修改？") != MsgBox.YES) return;
		isViewMode = true;
		if (selRowIndex > -1) {
			tblMain.getSelectManager().select(selRowIndex, 1, 2);
		} else {
			if (tblMain.getRowCount() > 0)
				tblMain.getSelectManager().select(0, 1, 2);
		}
		txtName.setText(selRepairSenderInfo.getName());
		txtTel.setText(selRepairSenderInfo.getTel());
		txtAddr.setText(selRepairSenderInfo.getAddr());
		txtEmail.setText(selRepairSenderInfo.getEmail());
		txtZipCode.setText(selRepairSenderInfo.getZipCode());
		txtIdNumber.setText(selRepairSenderInfo.getIdNumber());
		setUIEnable(false);
	}
	
	private void checkSelectRow() throws Exception {	
		KDTSelectBlock kdtSB = tblMain.getSelectManager().get(0);
		if (kdtSB == null) throw new EASBizException(new NumericExceptionSubItem("","请先选择数据行"));
	}
	
	private int getSelectRowIndex() throws Exception {
		KDTSelectBlock kdtSB = tblMain.getSelectManager().get(0);
		if (kdtSB == null) return -1;
		return kdtSB.getBeginRow();
		
	}
	
	public boolean destroyWindow() {
		if (!isViewMode) {
			MsgBoxEx.showInfo("非查看状态，不能退出！");
			return false;
		}
		return super.destroyWindow();
	}

}