/**
 * output package name
 */
package com.kingdee.eas.ga.basedata.client;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.swing.KDFormattedTextField;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.DataChangeListener;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.ga.basedata.CustomerDiscountEntryInfo;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.KDTableUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.myframework.vo.RequireCompCollection;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.util.NumericExceptionSubItem;


public class CustomerDiscountEditUI extends AbstractCustomerDiscountEditUI {
    private static final Logger logger = CoreUIObject.getLogger(CustomerDiscountEditUI.class);
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    
    
    public CustomerDiscountEditUI() throws Exception {
        super();
    }
 
	public RequireCompCollection registerRequireComp() throws Exception {
		txtNumber.setRequired(true);
		RequireCompCollection requireCompCol = new RequireCompCollection();
		requireCompCol.add(pkeffectiveDate);
		requireCompCol.add(pkexpirationDate);
		requireCompCol.add(prmtsaleOrgUnit);
		requireCompCol.add(kdtEntrys,"atsCustomer");
		requireCompCol.add(kdtEntrys,"repairDiscountRate");
		requireCompCol.add(kdtEntrys,"retailDiscountRate");
		return requireCompCol;
	}
    
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	
    	initCustPromptBox();
    	SaleOrgUnitInfo saleOrgUnitInfo =  SysContext.getSysContext().getCurrentSaleUnit();
    	if (saleOrgUnitInfo == null) {
    		throw new EASBizException(new NumericExceptionSubItem("","当前登录组织非销售组织,不能查看客户折扣！"));
    	}
    	prmtsaleOrgUnit.setValue(saleOrgUnitInfo);
    	kdtEntrys.addKDTEditListener(new KDTEditAdapter() {
    		private Object oldValue = null;
    		public void editStarting(KDTEditEvent e) {
    			oldValue = e.getValue();
    		}
    		@Override
    		public void editStopped(KDTEditEvent e) {
    			Object newValue = e.getValue();
    			if (!PublicUtils.equals(newValue, oldValue)) {
    				kdtEntrys_editStopped(e);
    			}
    		}
    	});
    	KDTableUtils.formatDecimal(kdtEntrys, "repairDiscountRate", false);
    	KDTableUtils.formatDecimal(kdtEntrys, "retailDiscountRate", false);
    	KDFormattedTextField txtRepairDiscountRate = (KDFormattedTextField)kdtEntrys.getColumn("repairDiscountRate").getEditor().getComponent();
    	KDFormattedTextField txtRetailDiscountRate = (KDFormattedTextField)kdtEntrys.getColumn("retailDiscountRate").getEditor().getComponent();
    	txtRepairDiscountRate.setMinimumValue(BigDecimal.ZERO);
    	txtRepairDiscountRate.setMaximumValue(new BigDecimal(100));
    	txtRetailDiscountRate.setMinimumValue(BigDecimal.ZERO);
    	txtRetailDiscountRate.setMaximumValue(new BigDecimal(100));

    	
    	
    }
    
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception {
        return com.kingdee.eas.ga.basedata.CustomerDiscountFactory.getRemoteInstance();
    }


    protected IObjectValue createNewDetailData(KDTable table) {	
    	CustomerDiscountEntryInfo entryInfo =  new CustomerDiscountEntryInfo();
    	if (kdtEntrys.getRowCount() > 0) {
    		entryInfo.put("repairDiscountRate", kdtEntrys.getRow(0).getCell("repairDiscountRate").getValue());
    		entryInfo.put("retailDiscountRate", kdtEntrys.getRow(0).getCell("retailDiscountRate").getValue());
    	}
    	return entryInfo;
    }

    protected com.kingdee.bos.dao.IObjectValue createNewData() {
        com.kingdee.eas.ga.basedata.CustomerDiscountInfo objectValue = new com.kingdee.eas.ga.basedata.CustomerDiscountInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }
    
    
    private void initCustPromptBox() {
		KDBizPromptBox kdtEntrys_cust_PromptBox = new KDBizPromptBox();
		kdtEntrys_cust_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.Customer_F7_Query");
		kdtEntrys_cust_PromptBox.setVisible(true);
		kdtEntrys_cust_PromptBox.setEditable(true);
		kdtEntrys_cust_PromptBox.setDisplayFormat("$number$");
		kdtEntrys_cust_PromptBox.setEditFormat("$number$");
		kdtEntrys_cust_PromptBox.setCommitFormat("$number$");
		kdtEntrys_cust_PromptBox.setEnabledMultiSelection(true);
		kdtEntrys_cust_PromptBox.addDataChangeListener(new DataChangeListener() {
			public void dataChanged(DataChangeEvent arg0) {
				try {
					prmtAtsCustomer_DataChanged(arg0);
				} catch (Exception e) {
					UIUtils.handUIException(e);
				}
			}
			
		});
		KDTDefaultCellEditor kdtEntrys_cust_CellEditor = new KDTDefaultCellEditor(kdtEntrys_cust_PromptBox);
		kdtEntrys.getColumn("atsCustomer").setEditor(kdtEntrys_cust_CellEditor);
		kdtEntrys.getColumn("atsCustomer").getEditor().getComponent();
		ObjectValueRender kdtEntrys_cust_OVR = new ObjectValueRender();
		kdtEntrys_cust_OVR.setFormat(new BizDataFormat("$number$"));
		kdtEntrys.getColumn("atsCustomer").setRenderer(kdtEntrys_cust_OVR);
	}
    
    private void prmtAtsCustomer_DataChanged(DataChangeEvent event) throws Exception{
    	Object newObject = event.getNewValue();
    	KDTSelectManager selectManager = kdtEntrys.getSelectManager();
        KDTSelectBlock selectBlock = selectManager.get(0);
        int rowIndex = selectBlock.getBeginRow();
        int astCustomerIndex = kdtEntrys.getColumnIndex("atsCustomer");
        CustomerInfo customerInfos[] = null;
        if(newObject instanceof Object[]) {
			  Object objs[] = (Object[])(Object[])newObject;
			  customerInfos = new CustomerInfo[objs.length];
			  System.arraycopy(((Object) (objs)), 0, customerInfos, 0, objs.length);
        } else {
         	  return;
        }
   		for (int i = 0; i < customerInfos.length; i++) {
			if (i > 0) insertLine(kdtEntrys,rowIndex+i);
			kdtEntrys.getRow(rowIndex+i).getCell(astCustomerIndex).setValue(customerInfos[i]);
			kdtEntrys_Changed(rowIndex+i,astCustomerIndex);
		}
    }
    
    protected void insertLine(KDTable kdTable, int rowIndex)  {
    	if(null == kdTable) {
           return;
        } else {
	        IRow irow = kdTable.addRow(rowIndex);
	        IObjectValue detailData = createNewDetailData(kdTable);
	        loadLineFields(kdTable, irow, detailData);
	        return;
        }
    }
    
    private void kdtEntrys_editStopped(KDTEditEvent e) {
    	int rowIndex = e.getRowIndex();
    	int colIndex = e.getColIndex();
    	int repairDiscountRateIndex = kdtEntrys.getColumnIndex("repairDiscountRate");
    	int retailDiscountRateIndex = kdtEntrys.getColumnIndex("retailDiscountRate");
    	BigDecimal repairDiscountRate = (BigDecimal) kdtEntrys.getRow(rowIndex).getCell(repairDiscountRateIndex).getValue();
    	BigDecimal retailDiscountRate = (BigDecimal) kdtEntrys.getRow(rowIndex).getCell(retailDiscountRateIndex).getValue();
    	
    	if (colIndex == repairDiscountRateIndex && (repairDiscountRate != null && repairDiscountRate.compareTo(BigDecimal.ZERO) != 0)) {
    		for (int i = rowIndex+1; rowIndex == 0 && i < kdtEntrys.getRowCount(); i++) {
    			if (kdtEntrys.getRow(i).getCell(repairDiscountRateIndex).getValue() != null) continue;
				kdtEntrys.getRow(i).getCell(repairDiscountRateIndex).setValue(repairDiscountRate);
    		}
    		
    	} if (colIndex == retailDiscountRateIndex && (retailDiscountRate != null && retailDiscountRate.compareTo(BigDecimal.ZERO) != 0)) {
    		for (int i = rowIndex+1; rowIndex == 0 && i < kdtEntrys.getRowCount(); i++) {
    			if (kdtEntrys.getRow(i).getCell(retailDiscountRateIndex).getValue() != null) continue;
				kdtEntrys.getRow(i).getCell(retailDiscountRateIndex).setValue(retailDiscountRate);
    		}
    		
    	}
    }
    
   @Override
	protected void verifyInput(ActionEvent e) throws Exception {
		super.verifyInput(e);
		Date effectiveDate = (Date) pkeffectiveDate.getValue();
		Date expirationDate = (Date) pkexpirationDate.getValue();
		
		if (effectiveDate == null) {
			MsgBoxEx.showInfo("生效日期不能为空!");
			pkeffectiveDate.requestFocus();
			SysUtil.abort();
		} 
		if (expirationDate == null) {
			MsgBoxEx.showInfo("失效日期不能为空!");
			pkexpirationDate.requestFocus();
			SysUtil.abort();
		}
		if (effectiveDate.after(expirationDate)) {
			MsgBoxEx.showInfo("失效日期必须大于生效日期!");
			pkexpirationDate.requestFocus();
			SysUtil.abort();
		}
		
		
	}

}