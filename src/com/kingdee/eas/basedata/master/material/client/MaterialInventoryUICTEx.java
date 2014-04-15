package com.kingdee.eas.basedata.master.material.client;

import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.DataChangeListener;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.eas.basedata.master.material.IMaterialInventory;
import com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.myframework.util.PublicUtils;

public class MaterialInventoryUICTEx extends MaterialInventoryUI {
	
	public MaterialInventoryUICTEx() throws Exception  {
		super();
	}

	public void onLoad() throws Exception {
       super.onLoad();
       prmtDefaultlocation.setEnabled(false);
       prmtDefaultWare.addDataChangeListener(new DataChangeListener() {

           public void dataChanged(DataChangeEvent e)  {
               prmtDefaultWare_dataChanged(e);
           }

       });
    /*   if (!PublicUtils.equals(getOprtState(),OprtState.ADDNEW)) {
    	   if(editData != null && editData.getId() != null && !"".equals(editData.getId().toString())) {
               IMaterialInventory iMaterialInv = (IMaterialInventory)getBizInterface();
               com.kingdee.bos.dao.IObjectPK pk = new ObjectUuidPK(editData.getId());
               IObjectValue newValue = getValue(pk);
               getUIContext().put("InitDataObject", newValue);
               setDataObject(newValue);
               loadFields();
           }
       }*/
       
	}
	
	@Override
	public SelectorItemCollection getSelectors() {
		SelectorItemCollection sc =  super.getSelectors();
		sc.add(new SelectorItemInfo("CostQty"));
		sc.add(new SelectorItemInfo("CostPrice"));
		sc.add(new SelectorItemInfo("*"));
		sc.add(new SelectorItemInfo("MaterialLoc.*"));
		return sc;
	}

	protected void prmtDefaultWare_dataChanged(DataChangeEvent e) {
       if(e.getNewValue() == null) {
           prmtDefaultlocation.setValue(null);
           prmtDefaultlocation.setEnabled(false);
       } else  {
           WarehouseInfo warehouseInfo = (WarehouseInfo)e.getNewValue();
           if(e.getOldValue() != null && null != e.getNewValue() && !((WarehouseInfo)e.getOldValue()).getId().toString().equals(((WarehouseInfo)e.getNewValue()).getId().toString()))
               prmtDefaultlocation.setValue(null);
           if(warehouseInfo.isHasLocation()) {
               EntityViewInfo ev = new EntityViewInfo();
               FilterInfo filter = new FilterInfo();
               ev.setFilter(filter);
               filter.getFilterItems().add(new FilterItemInfo("State", "1"));
               filter.getFilterItems().add(new FilterItemInfo("TransState", "1"));
               filter.getFilterItems().add(new FilterItemInfo("Warehouse.id", warehouseInfo.getId().toString()));
               prmtDefaultlocation.setEntityViewInfo(ev);
               prmtDefaultlocation.setEnabled(true);
           } else  {
               prmtDefaultlocation.setValue(null);
               prmtDefaultlocation.setEnabled(false);
           }
       }
	}

}
