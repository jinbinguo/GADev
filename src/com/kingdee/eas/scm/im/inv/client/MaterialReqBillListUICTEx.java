package com.kingdee.eas.scm.im.inv.client;

import java.util.List;

import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.scm.cal.CostAdjustBillEntryCollection;
import com.kingdee.eas.scm.cal.CostAdjustBillInfo;
import com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryCollection;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryFactory;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryInfo;
import com.kingdee.eas.scm.im.inv.MaterialReqBillFactory;
import com.kingdee.eas.scm.im.inv.MaterialReqBillInfo;
import com.kingdee.eas.util.client.KDTableUtil;
import com.kingdee.eas.util.client.MsgBox;

public class MaterialReqBillListUICTEx extends MaterialReqBillListUI {

	public MaterialReqBillListUICTEx() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	    * output kDelObj_actionPerformed method
	    */
	public void kDelObj1_actionPerformed(java.awt.event.ActionEvent e) throws Exception
	   {
			int a = MsgBox.showConfirm2(this, "确认要清空选中的成本对象吗？");
			if (a == MsgBox.OK)
		 		{
		       	SelectorItemCollection selector = new SelectorItemCollection();
		       	selector.add(new SelectorItemInfo("*"));
		       	selector.add(new SelectorItemInfo("customer.*"));
//		       	selector.add(new SelectorItemInfo("entry.costObject.id"));
//		       	selector.add(new SelectorItemInfo("entry.id"));
		       	selector.add(new SelectorItemInfo("entry.costObject.number"));
		       	selector.add(new SelectorItemInfo("entry.costObject.name"));
		       	selector.add(new SelectorItemInfo("entry.amount"));
//		     	List idList = getSelectedIdValues();
		        int selectRows[] = KDTableUtil.getSelectedRows(tblMain);
		        for(int i=0;i<selectRows.length;i++)
		         {
		         String entryId = this.tblMain.getRow(selectRows[i]).getCell("entry.id").getValue().toString();
		         IMaterialReqBillEntry iMaterialReqBillEntry = MaterialReqBillEntryFactory.getRemoteInstance();
		     	MaterialReqBillEntryInfo materialReqBillEntryInfo = (MaterialReqBillEntryInfo)iMaterialReqBillEntry.getValue(new ObjectUuidPK(BOSUuid.read(entryId)),selector); 
       			if(materialReqBillEntryInfo.getCostObject()!=null)
       				materialReqBillEntryInfo.setCostObject2(materialReqBillEntryInfo.getCostObject());
       			materialReqBillEntryInfo.setCostObject(null);
       			MaterialReqBillEntryFactory.getRemoteInstance().update(new ObjectUuidPK(materialReqBillEntryInfo.getId()), materialReqBillEntryInfo); 
		         } 		
		        
				// 刷新列表界面
				actionRefresh_actionPerformed(null);
			}
	   }
	   
		/**
	    * output kDelObj_actionPerformed method
	    */
	   public void relObj1_actionPerformed(java.awt.event.ActionEvent e) throws Exception
	   {
			int a = MsgBox.showConfirm2(this, "恢复选中的成本对象？");
			if (a == MsgBox.OK)
		 		{
		       	SelectorItemCollection selector = new SelectorItemCollection();
		       	selector.add(new SelectorItemInfo("*"));
		       	selector.add(new SelectorItemInfo("customer.*"));
		       	selector.add(new SelectorItemInfo("entry.costObject2.number"));
		       	selector.add(new SelectorItemInfo("entry.costObject2.name"));
		       	selector.add(new SelectorItemInfo("entry.amount"));
//		       	List idList = (List) getSelectedIdValues();
		        int selectRows[] = KDTableUtil.getSelectedRows(tblMain);
		        for(int i=0;i<selectRows.length;i++)
		         {
		         String entryId = this.tblMain.getRow(selectRows[i]).getCell("entry.id").getValue().toString();
		         IMaterialReqBillEntry iMaterialReqBillEntry = MaterialReqBillEntryFactory.getRemoteInstance();
		     	MaterialReqBillEntryInfo materialReqBillEntryInfo = (MaterialReqBillEntryInfo)iMaterialReqBillEntry.getValue(new ObjectUuidPK(BOSUuid.read(entryId)),selector); 
      			if(materialReqBillEntryInfo.getCostObject2()!=null)
      				materialReqBillEntryInfo.setCostObject(materialReqBillEntryInfo.getCostObject2());
      			MaterialReqBillEntryFactory.getRemoteInstance().update(new ObjectUuidPK(materialReqBillEntryInfo.getId()), materialReqBillEntryInfo); 
		         } 		
		       	
		       	
//		       	for(int i=0;i<idList.size();i++)
//		       	{
//		       		String id= (String) idList.get(i);
////		     		System.out.println(id+"111111111111");
//		       		MaterialReqBillInfo materialReqBill = (MaterialReqBillInfo) getBizInterface().getValue(new com.kingdee.bos.dao.ormapping.ObjectUuidPK(BOSUuid.read((String) idList.get(i))),selector);
////		          System.out.println(costAdjustBill.getBizDate()+"5555555555555555555555555");
//		       		MaterialReqBillEntryCollection abc = materialReqBill.getEntry();
//		       		for(int j=0;j<abc.size();j++){
////		    			CostObjectInfo costObject = abc.get(j).getCostObject();
////		      		System.out.println(abc.get(j).getCostObject().getName()+"111111111111");
////		              BOSUuid costAdjustBillId=costAdjustBill.getEntry().get(j).getCostObject().getId();
////		               costAdjustBill.getEntry().get(j).getReasonCode().setId(costAdjustBillId);
////		               System.out.println(costAdjustBill.getEntry().get(j).getReasonCode().getId()+"2222");
////		      			costAdjustBill.getEntry().get(j).get
////		       			System.out.println(materialReqBill.getEntry().get(j).getCostObject2().getName()+"333333333"); 
////		       			System.out.println(materialReqBill.getEntry().get(j).getCostObject2().getName()+"2222");
//		       			materialReqBill.getEntry().get(j).setCostObject(materialReqBill.getEntry().get(j).getCostObject2());
////		                System.out.println(costAdjustBill.getEntry().get(j).getCostObject2().getName()+"2222");     			
//		                getBizInterface().update(new ObjectUuidPK(materialReqBill.getId()), materialReqBill);    
////		                execQuery();
//		       		}
//		       	}			
		       	
		       	
				// 刷新列表界面
				actionRefresh_actionPerformed(null);			
//				if(this.getUIContext().get("Owner") instanceof saleoutListUI){
//					SaleOutListUI listUI =(SaleOutListUI) this.getUIContext().get("Owner");
//					ListUI.actionRefresh_actionPerformed(e);
//					}
			}
	   }
}
