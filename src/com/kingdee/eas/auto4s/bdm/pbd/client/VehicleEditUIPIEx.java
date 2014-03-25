/**
 * output package name
 */
package com.kingdee.eas.auto4s.bdm.pbd.client;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum;
import com.kingdee.eas.auto4s.commonutil.CommonUtils;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.ga.rs.RepairManInfo;
import com.kingdee.eas.ga.rs.client.RepairManF7UI;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.common.SortTypeEnum;
import com.kingdee.eas.myframework.comparators.table.KDTableComparatorUtils;
import com.kingdee.eas.myframework.comparators.table.SortColumnCollection;
import com.kingdee.eas.myframework.comparators.table.SortColumnInfo;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PermUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.StringUtils;

/**
 * output class name
 */
public class VehicleEditUIPIEx extends VehicleEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(VehicleEditUIPIEx.class);
    

    public VehicleEditUIPIEx() throws Exception  {
        super();
    }
    
 
    @Override
    protected void initOldData(IObjectValue dataObject) {
    	super.initOldData(dataObject);
    	try {
			sortForRepairRemark();
			getRepairMan();
		} catch (Exception e) {
			UIUtils.handUIException(e);
		}
    }
    
    private void sortForRepairRemark () throws Exception {
    	SortColumnCollection sortColumns = new SortColumnCollection();
		sortColumns.add(new SortColumnInfo("createTime",SortTypeEnum.DESC));
	
		KDTableComparatorUtils tblComparatorUtils = new KDTableComparatorUtils(kdtRepairRemark, sortColumns);
    	List<IRow> lstRow = tblComparatorUtils.sort();
    	//loadFields();
    	kdtRepairRemark.removeRows();
    	for (int i = 0; i < lstRow.size(); i++) {
    		kdtRepairRemark.addRow(i, lstRow.get(i));
    	}
    }
    private void  getRepairMan() throws Exception {
    	String vehicleId = editData.getString("id");
    	kdtRepairMan.removeRows();
    	if (PublicUtils.isEmpty(vehicleId)) return;
      	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT b.fid, b.fnumber, b.cfname, b.cftel, b.cfemail,b.cfidnumber,b.cfzipcode,b.cfaddr, a.fid fentryId")
    	.append(" FROM ct_rs_repairmanentry a")
		.append(" LEFT JOIN ct_rs_repairman b ON a.fparentid = b.fid")
		.append(String.format(" where a.cfvehicleid='%s'",vehicleId));
    	IRowSet rs = DBUtils.executeQuery(null, sql.toString());
    	
    	while (rs != null && rs.next()) {
    		IRow row = kdtRepairMan.addRow();
 
    		row.getCell("number").setValue(rs.getString("fnumber"));
    		row.getCell("name").setValue(rs.getString("cfname"));
    		row.getCell("tel").setValue(rs.getString("cftel"));
    		row.getCell("addr").setValue(rs.getString("cfaddr"));
    		row.getCell("zipCode").setValue(rs.getString("cfzipcode"));
    		row.getCell("email").setValue(rs.getString("cfemail"));
    		row.getCell("idNumber").setValue(rs.getString("cfidnumber"));
    	}	
    }
    

    @Override
    public SelectorItemCollection getSelectors() {
    	SelectorItemCollection sic = super.getSelectors();
		sic.add(new SelectorItemInfo("RepairRemark.*"));
    	return sic;
    }
    
    @Override
    protected void beforeStoreFields(ActionEvent arg0) throws Exception {
        int orgUnitRowCount = 0;
        int isDefaultOrgUnitCount = 0;
        Set orgUnitSet = new HashSet();
        if(source.getSelectedItem() != null && source.getSelectedItem().equals(VehicleSourceEnum.OUT))  {
            if(UIRuleUtil.isNull(super.txtplateNum.getText()) && UIRuleUtil.isNull(super.txtvIN.getText()))  {
            	MsgBox.showInfo("������ԴΪ������ʱ�����̺š����ƺű���¼��һ����");
                txtplateNum.requestFocus();
                SysUtil.abort();
            }
        }
        else if(source.getSelectedItem() != null && source.getSelectedItem().equals(VehicleSourceEnum.OWN) && UIRuleUtil.isNull(super.txtvIN.getText())) {
            MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "VinNotNull"));
            txtvIN.requestFocus();
            SysUtil.abort();
        }

        if(chkotherBrandVehicle.getSelected() == 16)  {        
        	if(UIRuleUtil.isNull(super.prmtmodel.getValue()))  {
                MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "modelfirst"));
                prmtmodel.requestFocus();
                SysUtil.abort();
            }

	        if(UIRuleUtil.isNull(super.prmtseries.getValue())) {
	                MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "seriesFirst"));
	                prmtseries.requestFocus();
	                SysUtil.abort();
	        }
	
	        if(UIRuleUtil.isNull(super.prmtbrand.getValue())) {
	                MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "brandCanntNull"));
	
	                prmtbrand.requestFocus();
	                SysUtil.abort();
	        }
         } else if(UIRuleUtil.isNull(super.txtvehicleRemark.getText())) {
            MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "vehicleInfoCanntNull"));
            txtvehicleRemark.requestFocus();
            SysUtil.abort();
         }


        if(chkinitVehicle.isSelected()) {
        	if(UIRuleUtil.isNull(prmtorgUnit.getValue())) {
                MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "OrgUnitCanntNull"));

                prmtorgUnit.requestFocus();
                SysUtil.abort();
             }

            if(UIRuleUtil.isNull(prmtwarehouse.getValue())) {
                MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "WarehouseCanntNull"));
                prmtwarehouse.requestFocus();
                SysUtil.abort();
            }
        }

      //  if(super.txtvIN.getText().length() > 0 && !CommonUtils.isVin(txtvIN.getText()))  {
      //     MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "VINLength"));
      //    txtvIN.requestFocus();
      //      SysUtil.abort();
      //  }
        if(super.txtvIN.getText().length() > 0)
            txtvIN.setText(txtvIN.getText().trim().toUpperCase());

        if(super.txtplateNum.getText().length() > 0)
            txtplateNum.setText(txtplateNum.getText().trim().toUpperCase());

        if(source.getSelectedItem() != null && source.getSelectedItem().equals(VehicleSourceEnum.OUT) && kdtBelong.getRowCount() <= 0) {
            MsgBox.showInfo("��¼�복������ά�����ģ�");
            kDTabbedPane1.setSelectedComponent(kDPanel4);
            SysUtil.abort();
        }
     
       for(int i = 0; i < kdtBelong.getRowCount(); i++) {
    	   if(UIRuleUtil.isNull(kdtBelong.getCell(i, "orgUnit").getValue()))  {
               kdtBelong.getEditManager().editCellAt(i, kdtBelong.getColumn("orgUnit").getColumnIndex());
               throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {"ά�޹�˾"});
    	   }

           Object orgUnit = kdtBelong.getCell(i, "orgUnit").getValue();
           if(orgUnit != null && !"".equals(orgUnit.toString().trim())) {
        	   orgUnitSet.add(orgUnit.toString());
               orgUnitRowCount++;
           }
           Object isDefaultOrgUnit = kdtBelong.getCell(i, "isDefault").getValue();

           if(isDefaultOrgUnit.equals(new Boolean(true)))
               isDefaultOrgUnitCount++;
       }

       if(orgUnitRowCount != orgUnitSet.size())  {
    	   MsgBox.showWarning(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "idIsSame"));
           SysUtil.abort();
        }
       if(isDefaultOrgUnitCount > 1) { 
    	   MsgBox.showWarning(EASResource.getString("com.kingdee.eas.auto4s.bdm.pbd.client.PbdResource", "DefaultOrgUnitCantSame"));
           SysUtil.abort();
       }
    	
    }
    
    @Override
    public void actionEditRepairMan_actionPerformed(ActionEvent e)
    		throws Exception {
    	String vehicleId = editData.getString("id");
    	if (PublicUtils.isEmpty(vehicleId)) {
    		MsgBoxEx.showInfo("�������������ȱ���!");
    		return;
    	}
    	UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
		AdminOrgUnitInfo adminOrgInfo = SysContext.getSysContext().getCurrentAdminUnit();
		boolean hasPermission_RepairManView = PermUtils.hasFunctionPermission(null, userInfo, adminOrgInfo, "repairMan_View");
		if (!hasPermission_RepairManView) {
			if (e != null)
				MsgBoxEx.showInfo("�������˲鿴Ȩ�ޣ�");
			return;
		}
		
    	Map uictx = new HashMap();
		uictx.put("vehicleInfo", editData);
		uictx.put("isFromVehicleEdit", true);
		IUIFactory iUIFactory = UIFactory.createUIFactory(UIFactoryName.MODEL);
		IUIWindow iUIWindowSizesHEdit = iUIFactory.create(RepairManF7UI.class.getName(),uictx); // ��ȡFeedRecListUI��IUIWindow
		RepairManF7UI f7 = (RepairManF7UI) iUIWindowSizesHEdit.getUIObject();
		f7.setUITitle("������");
		iUIWindowSizesHEdit.show();
		getRepairMan();
		
    }
    
   


}