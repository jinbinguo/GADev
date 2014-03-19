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
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.ga.rs.IRepairMan;
import com.kingdee.eas.ga.rs.IRepairManEntry;
import com.kingdee.eas.ga.rs.RepairManEntryCollection;
import com.kingdee.eas.ga.rs.RepairManEntryFactory;
import com.kingdee.eas.ga.rs.RepairManEntryInfo;
import com.kingdee.eas.ga.rs.RepairManFactory;
import com.kingdee.eas.ga.rs.RepairManInfo;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PermUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.rptclient.newrpt.util.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

/**
 * output class name
 */
public class RepairManF7UI extends AbstractRepairManF7UI implements KDPromptSelector {
    private static final Logger logger = CoreUIObject.getLogger(RepairManF7UI.class);

    private IRepairMan repairMan = RepairManFactory.getRemoteInstance();
    private IRepairManEntry repairManEntry = RepairManEntryFactory.getRemoteInstance();
    private RepairManInfo oldRepairManInfo = null; //当前维修人(用于回写及初始选中)
    private RepairManInfo newRepairManInfo = null; //选中维修人(用于编辑保存)
    private RepairManInfo returnRepairManInfo = null; //返回维修人(用于返回值)
    private OrgUnitInfo orgUnitInfo = null;
    
    private boolean isViewMode = true; //查看方式
    private int selRowIndex = 0;
    private VehicleInfo vehicleInfo = null;
    
    public RepairManF7UI() throws Exception {
        super();
    }

    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	txtName.setRequired(true);
    	txtSenderId.setVisible(false);
    	txtEntryId.setVisible(false);
    	Map uictx = getUIContext();
    	vehicleInfo = (VehicleInfo) uictx.get("vehicleInfo");
		String vehicleId = vehicleInfo.getString("id") ; 
		orgUnitInfo = (OrgUnitInfo) uictx.get("orgUnit");
		if (orgUnitInfo == null) orgUnitInfo = SysContext.getSysContext().getCurrentOrgUnit();
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
				
				txtNumber.setText((String)row.getCell("number").getValue());
				txtName.setText((String)row.getCell("name").getValue());
				txtTel.setText((String)row.getCell("tel").getValue());
				txtAddr.setText((String)row.getCell("addr").getValue());
				txtEmail.setText((String)row.getCell("email").getValue());
				txtIdNumber.setText((String)row.getCell("idNumber").getValue());
				txtZipCode.setText((String)row.getCell("zipcode").getValue());
				txtSenderId.setText((String)row.getCell("id").getValue());
				txtEntryId.setText((String)row.getCell("entryId").getValue());
				newRepairManInfo = getSelRepairManInfo();
				
				
			}    		
    	});
    	tblMain.addKDTMouseListener(new KDTMouseListener() {
    		
			public void tableClicked(KDTMouseEvent event) {
				if (!isViewMode) return;
				if (!PublicUtils.equals(event.getOriginView().getClass().getName(),
						KDTView.class.getName()))  return;
				if (event.getClickCount() == 2) {
					Map uictx = getUIContext();
			    	boolean isFromVehicle =  uictx.get("isFromVehicleEdit") == null ? false : (Boolean) uictx.get("isFromVehicle");
			    	if (isFromVehicle) return;
					newRepairManInfo = getSelRepairManInfo();
					returnRepairManInfo = getSelRepairManInfo();
					destroyWindow();
					
				}
			}
    		
    	});
 
    	
    	setUIEnable(false);

    
    }
    
    private RepairManInfo getSelRepairManInfo() {
    	RepairManInfo vInfo = new RepairManInfo();
    	vInfo.setNumber(txtNumber.getText());
    	vInfo.setName(txtName.getText());
    	vInfo.setTel(txtTel.getText());
    	vInfo.setAddr(txtAddr.getText());
    	vInfo.setEmail(txtEmail.getText());
    	vInfo.setIdNumber(txtIdNumber.getText());
    	vInfo.setZipCode(txtZipCode.getText());
    	vInfo.put("id", txtSenderId.getText());
    	vInfo.put("entryId", txtEntryId.getText());
    	return vInfo;
    }
    
    private void setUIEnable(boolean enabled) {
    	txtNumber.setEditable(enabled);
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
    	StringBuilder sql = new StringBuilder();
    	
    	tblMain.removeRows();
    	sql.append("select b.FNumber, b.FName_l2, b.FPhone, b.FEmail,b.FPapersNum,b.FZipCode,b.FAddress from T_ATS_Vehicle a ")
    		.append("left join T_ATS_Customer b on a.FCustomerID=b.FID ")
    		.append("where  a.FID='").append(vehicleId).append("'");
    	IRowSet rs  = DBUtils.executeQuery(null, sql.toString());
    	while ( rs!= null && rs.next()) {
    		IRow row  = tblMain.addRow();
    		//row.getCell("id").setValue(rs.getString("fid"));
    		//row.getCell("entryId").setValue(rs.getString("fentryId"));
    		String name = rs.getString("FName_l2");
    		if (PublicUtils.equals("无名称", name)) name = "现结客户";
    		String tel = rs.getString("FPhone");
			if (PublicUtils.equals("无号码", tel)) tel = ""; 
			
    		row.getCell("number").setValue(rs.getString("FNumber"));
    		row.getCell("name").setValue(name);
    		row.getCell("tel").setValue(tel);
    		row.getCell("addr").setValue(rs.getString("FAddress"));
    		row.getCell("zipcode").setValue(rs.getString("FZipCode"));
    		row.getCell("email").setValue(rs.getString("FEmail"));
    		row.getCell("idNumber").setValue(rs.getString("FPapersNum"));
    	}
    	
    	StringBuilder sql1 = new StringBuilder();
    	sql1.append("SELECT b.fid, b.fnumber, b.cfname, b.cftel, b.cfemail,b.cfidnumber,b.cfzipcode,b.cfaddr, a.fid fentryId")
    	.append(" FROM ct_rs_repairmanentry a")
		.append(" LEFT JOIN ct_rs_repairman b ON a.fparentid = b.fid")
		.append(String.format(" where a.cfvehicleid='%s'",vehicleId));
    	rs = DBUtils.executeQuery(null, sql1.toString());
    	while (rs != null && rs.next()) {
    		IRow row = tblMain.addRow();
    		row.getCell("id").setValue(rs.getString("fid"));
    		row.getCell("entryId").setValue(rs.getString("fentryId"));
    		row.getCell("number").setValue(rs.getString("fnumber"));
    		row.getCell("name").setValue(rs.getString("cfname"));
    		row.getCell("tel").setValue(rs.getString("cftel"));
    		row.getCell("addr").setValue(rs.getString("cfaddr"));
    		row.getCell("zipcode").setValue(rs.getString("cfzipcode"));
    		row.getCell("email").setValue(rs.getString("cfemail"));
    		row.getCell("idNumber").setValue(rs.getString("cfidnumber"));
    	}	
    }

	public Object getData() {
		return returnRepairManInfo;
	}

	public boolean isCanceled() {
		return false;
	}
	
	@Override
	public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		
		UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
		boolean hasPermission_RepairManAddnew = PermUtils.hasFunctionPermission(null, userInfo, orgUnitInfo, "repairMan_addnew");
		if (!hasPermission_RepairManAddnew) {
			if (e != null)
				MsgBoxEx.showInfo("无送修人新增权限！");
			return;
		}
		
		selRowIndex = getSelectRowIndex();
		isViewMode = false;
		if (newRepairManInfo != null)
			oldRepairManInfo = (RepairManInfo) newRepairManInfo.clone();
		newRepairManInfo = new RepairManInfo();
		txtNumber.setText("");
		txtName.setText("");
		txtTel.setText("");
		txtAddr.setText("");
		txtEmail.setText("");
		txtZipCode.setText("");
		txtIdNumber.setText("");
		txtSenderId.setText("");
		txtEntryId.setText("");
		setUIEnable(true);
		
	}
	@Override
	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		
		if (PublicUtils.isEmpty(txtNumber.getText())) {
			MsgBoxEx.showInfo("编号不能为空！");
			return;
		}
		
		if (PublicUtils.isEmpty(txtName.getText())) {
			MsgBoxEx.showInfo("姓名不能为空！");
			return;
		}
		
		Map uictx = getUIContext();
		String vehicleId = vehicleInfo.getString("id") ;
    	
		
		String senderId = txtSenderId.getText();
		String entryId = txtEntryId.getText();
		
		
		RepairManInfo repairManInfo = null;
		RepairManEntryCollection repairManEntryCol = null;
		//分录保存
		if (PublicUtils.isEmpty(senderId)) {
			repairManInfo = new RepairManInfo();
			repairManEntryCol = new RepairManEntryCollection();
			RepairManEntryInfo repairManEntryInfo = new RepairManEntryInfo();
			repairManEntryInfo.setVehicle(vehicleInfo);
			repairManEntryCol.add(repairManEntryInfo);
			repairManInfo.put("entrys", repairManEntryCol);
		} else {
			repairManInfo = repairMan.getRepairManInfo(new ObjectUuidPK(senderId));
		//	repairManEntryCol = repairManEntry.getRepairManEntryCollection(String.format("where parent.id='%s'",senderId));
		}
		repairManInfo.setNumber(txtNumber.getText());
		repairManInfo.setName(txtName.getText());
		repairManInfo.setTel(txtTel.getText());
		repairManInfo.setAddr(txtAddr.getText());
		repairManInfo.setEmail(txtEmail.getText());
		repairManInfo.setIdNumber(txtIdNumber.getText());
		repairManInfo.setZipCode(txtZipCode.getText());
		
		
		repairMan.save(repairManInfo);
	
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
		UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
		boolean hasPermission_RepairManEdit = PermUtils.hasFunctionPermission(null, userInfo, orgUnitInfo, "repairMan_Edit");
		if (!hasPermission_RepairManEdit) {
			if (e != null)
				MsgBoxEx.showInfo("无送修人新增权限！");
			return;
		}
		if (PublicUtils.isEmpty(txtSenderId.getText())) {
			MsgBoxEx.showInfo("车主信息不能通过送修人修改！");
			return;
		}
		
		isViewMode = false;
		selRowIndex = getSelectRowIndex();
		oldRepairManInfo = (RepairManInfo) newRepairManInfo.clone();
		setUIEnable(true);
		
	}
	@Override
	public void actionDelete_actionPerformed(ActionEvent e) throws Exception {
		checkSelectRow();
		if (PublicUtils.isEmpty(txtSenderId.getText())) {
			MsgBoxEx.showInfo("车主信息不能通过送修人修改！");
			return;
		}
		if (MsgBoxEx.showConfirm2("是否删除？") != MsgBox.YES) return;
		UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
		boolean hasPermission_RepairManRemove = PermUtils.hasFunctionPermission(null, userInfo, orgUnitInfo, "repairMan_Remove");
		if (!hasPermission_RepairManRemove) {
			if (e != null)
				MsgBoxEx.showInfo("无送修人新增权限！");
			return;
		}
		Map uictx = getUIContext();
		VehicleInfo vehicleInfo = (VehicleInfo) uictx.get("vehicleInfo");
		String vehicleId = vehicleInfo.getString("id") ;
		
		int rowIndex = getSelectRowIndex();
		IRow row = tblMain.getRow(rowIndex);
		String senderId = (String) row.getCell("id").getValue();
		String entryId = (String) row.getCell("entryId").getValue();
		boolean isRemoveEntry = false;
		IRowSet rs = DBUtils.executeQueryForDialect(null, String.format("select count(1) as cnt from CT_RS_RepairManEntry where fparentid='%s'", senderId));
		if (rs != null && rs.next()) {
			int cnt = rs.getInt("cnt");
			if (cnt > 1) {
				isRemoveEntry = true;
			}
		}
		if (isRemoveEntry) {
			repairManEntry.delete(new ObjectUuidPK(entryId));
		} else {
			repairMan.delete(new ObjectUuidPK(senderId));
		}
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
		if (oldRepairManInfo != null) {
			txtName.setText(oldRepairManInfo.getName());
			txtTel.setText(oldRepairManInfo.getTel());
			txtAddr.setText(oldRepairManInfo.getAddr());
			txtEmail.setText(oldRepairManInfo.getEmail());
			txtZipCode.setText(oldRepairManInfo.getZipCode());
			txtIdNumber.setText(oldRepairManInfo.getIdNumber());
			txtSenderId.setText(oldRepairManInfo.getString("id"));
			txtEntryId.setText(oldRepairManInfo.getString("entryId"));
		}
		
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