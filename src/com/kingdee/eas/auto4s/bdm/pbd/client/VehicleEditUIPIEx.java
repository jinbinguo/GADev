/**
 * output package name
 */
package com.kingdee.eas.auto4s.bdm.pbd.client;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum;
import com.kingdee.eas.auto4s.commonutil.CommonUtils;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.common.SortTypeEnum;
import com.kingdee.eas.myframework.comparators.table.KDTableComparatorUtils;
import com.kingdee.eas.myframework.comparators.table.SortColumnCollection;
import com.kingdee.eas.myframework.comparators.table.SortColumnInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;

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
    public void onLoad() throws Exception {
    	super.onLoad();
    	sortForRepairRemark();
    	
    }
    @Override
    public void actionFirst_actionPerformed(ActionEvent e) throws Exception {
    	super.actionFirst_actionPerformed(e);
    	sortForRepairRemark();
    }
    
    @Override
    public void actionNext_actionPerformed(ActionEvent e) throws Exception {
    	super.actionNext_actionPerformed(e);
    	sortForRepairRemark();
    }
    @Override
    public void actionPre_actionPerformed(ActionEvent e) throws Exception {

    	super.actionPre_actionPerformed(e);
    	sortForRepairRemark();
    }
    @Override
    public void actionLast_actionPerformed(ActionEvent e) throws Exception {

    	super.actionLast_actionPerformed(e);
    	sortForRepairRemark();
    }
    
    private void sortForRepairRemark () throws Exception {
    	SortColumnCollection sortColumns = new SortColumnCollection();
		sortColumns.add(new SortColumnInfo("createTime",SortTypeEnum.DESC));
	
		KDTableComparatorUtils tblComparatorUtils = new KDTableComparatorUtils(kdtRepairRemark, sortColumns);
    	List<IRow> lstRow = tblComparatorUtils.sort();
    	loadFields();
    	kdtRepairRemark.removeRows();
    	for (int i = 0; i < lstRow.size(); i++) {
    		kdtRepairRemark.addRow(i, lstRow.get(i));
    	}
    }
    

    @Override
    public SelectorItemCollection getSelectors() {
    	SelectorItemCollection sic = super.getSelectors();
		sic.add(new SelectorItemInfo("RepairSender.*"));
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
            	MsgBox.showInfo("车辆来源为外来车时，底盘号、车牌号必须录入一个！");
                txtplateNum.requestFocus();
                SysUtil.abort();
            }
        } else if(source.getSelectedItem() != null && source.getSelectedItem().equals(VehicleSourceEnum.OWN) && UIRuleUtil.isNull(super.txtvIN.getText())) {
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
            MsgBox.showInfo("请录入车辆所属维修中心！");
            kDTabbedPane1.setSelectedComponent(kDPanel4);
            SysUtil.abort();
        }
     
       for(int i = 0; i < kdtBelong.getRowCount(); i++) {
    	   if(UIRuleUtil.isNull(kdtBelong.getCell(i, "orgUnit").getValue()))  {
               kdtBelong.getEditManager().editCellAt(i, kdtBelong.getColumn("orgUnit").getColumnIndex());
               throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {"维修公司"});
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
    	
    	for (int i=0,n=kdtRepairSender.getRowCount();i<n;i++) {
			if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(kdtRepairSender.getCell(i,"name").getValue())) {
				throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"送修人姓名"});
			}
		}
    }
    
   


}