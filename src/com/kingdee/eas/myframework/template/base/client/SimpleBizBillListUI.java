/**
 * output package name
 */
package com.kingdee.eas.myframework.template.base.client;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent;
import com.kingdee.bos.ctrl.swing.StringUtils;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.BizEnumValueDTO;
import com.kingdee.bos.framework.batch.BatchExecuteParamsEntry;
import com.kingdee.bos.framework.batch.BatchExecuteResult;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.framework.client.mutex.DataObjectMutex;
import com.kingdee.eas.framework.client.mutex.IDataObjectMutex;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;
import com.kingdee.eas.myframework.util.BotpUtils;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.eas.myframework.util.MutexUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.vo.MsgTableInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.util.NumericExceptionSubItem;


public class SimpleBizBillListUI extends AbstractSimpleBizBillListUI {
	private static final Logger logger = CoreUIObject.getLogger(SimpleBizBillListUI.class);


	public SimpleBizBillListUI() throws Exception {
		super();
	}

	public void storeFields() {
		super.storeFields();
	}

    protected void initListener() {
    	super.initListener();
    }
    
	protected void tblMain_tableSelectChanged(KDTSelectEvent kdtselectevent) throws Exception {
		super.tblMain_tableSelectChanged(kdtselectevent);
		if(isMultiViewEnabled())
			 return;
		ArrayList selectSet = getSelectedIdValues();
		if(selectSet.size() == 1) {
			actionEdit.setEnabled(true);
		} else {
			actionEdit.setEnabled(false);
		}
	}
	
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception {
      	checkSelected();
    	ArrayList<Integer> arySelRowIndex = getSelectList();
    	String[] colNames = new String[] {"number","reason"};
    	String[] titleNames = new String[] {"编码","原因"};
    	int[] colWidths = new int[] {130,250};
    	MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames,colWidths);
    	boolean isCanAudit = true;
    	KDTable msgTbl = msgTblInfo.getTable();
    	for (int i = 0; i < arySelRowIndex.size(); i++) {
    		Integer rowIndex = arySelRowIndex.get(i);
    		IRow row = tblMain.getRow(rowIndex);
    		BizEnumValueDTO bizEnumStatus = (BizEnumValueDTO) row.getCell("status").getValue();
    		String status = bizEnumStatus == null ? "" : (String) bizEnumStatus.getStateManager().getStateValue("value");
    		
    		String id = (String) row.getCell("id").getValue(); 		
    		if (!"2".equals(status)) { //非提交状态 
    			isCanAudit = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("reason").setValue("非提交状态的单据,不允许审核！");
    			continue;
    			
    		}
    		
    		try {
    			/** 检查是否被锁*/
    			MutexUtils.checkDataObjectLock(id);
    		} catch (Throwable ee) {
    			isCanAudit = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("reason").setValue(ee.getMessage());
    			continue;
    			
    		}
    	}	
    	if (!isCanAudit) {
    		MsgBoxEx.showInfoTable(this, "审核单据失败!", msgTblInfo);
    		SysUtil.abort();   		
    	}
    	
    	
    	//---批量审核
    	
    	List<String> ids = getSelectedIdValues();
    	Map<String,String> mapIdNumber = getSelectedIdNumber();
    	
    	BatchExecuteParamsEntry paramEntries[] = new BatchExecuteParamsEntry[ids.size()];
    	for (int i = 0; i < ids.size(); i++) {
    		String id = ids.get(i);
    		IObjectPK pk = new ObjectUuidPK(id);
    		BatchExecuteParamsEntry entry = new BatchExecuteParamsEntry(new Class[] {IObjectPK.class}, new Object[] {pk});
    		paramEntries[i] = entry;
    	}
    	BatchExecuteResult result = ((ISimpleBizBill)getBizInterface()).batchExecuteWithTrans("audit", paramEntries);
    	Throwable exs[] = result.getExceptions();
    	if (exs != null) {
    		for (int i = 0; i < exs.length; i++) {
    			Throwable ex = exs[i];
    			if (ex == null) continue;
    			String number = mapIdNumber.get(ids.get(i));
    			isCanAudit = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(number);
    			
    			String causeClssName = (String) InvokeUtils.getFieldValue(ex, "causeClassName");
    			if (PublicUtils.equals(causeClssName, "javax.ejb.TransactionRolledbackLocalException")) {
    				msgRow.getCell("reason").setValue("事务交易已经回滚，未执行！");
    			} else {
    				msgRow.getCell("reason").setValue(ex.getMessage());
    			}
    			
    			
    		}
    		
    	} else {
    		MsgBoxEx.showInfo("审核单据失败！");
    		
    	}
    	if (!isCanAudit) {
        	MsgBoxEx.showInfoTable(this, "部分审核单据失败!", msgTblInfo);
        	SysUtil.abort();   		
        }
    	MsgBoxEx.showInfo("审核成功！");
    	refreshList();
    }
    

    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception {
    	checkSelected();
    	ArrayList<Integer> arySelRowIndex = getSelectList();
    	String[] colNames = new String[] {"number","reason"};
    	String[] titleNames = new String[] {"编码","原因"};
    	int[] colWidths = new int[] {130,250};
    	MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames,colWidths);
    	boolean isCanUnAudit = true;
    	KDTable msgTbl = msgTblInfo.getTable();
    	for (int i = 0; i < arySelRowIndex.size(); i++) {
    		Integer rowIndex = arySelRowIndex.get(i);
    		IRow row = tblMain.getRow(rowIndex);
    		BizEnumValueDTO bizEnumStatus = (BizEnumValueDTO) row.getCell("status").getValue();
    		String status = bizEnumStatus == null ? "" : (String) bizEnumStatus.getStateManager().getStateValue("value");
    		
    		String id = (String) row.getCell("id").getValue(); 		
    		if (!"4".equals(status)) { //非审核状态 
    			isCanUnAudit = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("reason").setValue("非审核状态的单据,不允许反审核！");
    			continue;
    			
    		}
    		/** 检查是否存在下游单据*/
    		boolean hasDestBill = BotpUtils.hasDestBill(id);
    		
    		if (hasDestBill) {
    			isCanUnAudit = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("reason").setValue("存在下游单据，不允许反审核！");
    			continue;
    		}
    	}	
    	if (!isCanUnAudit) {
    		MsgBoxEx.showInfoTable(this, "反审核单据失败!", msgTblInfo);
    		SysUtil.abort();   		
    	}
    	
    	
    	//---批量反审核
    	
    	List<String> ids = getSelectedIdValues();
    	Map<String,String> mapIdNumber = getSelectedIdNumber();
    	
    	BatchExecuteParamsEntry paramEntries[] = new BatchExecuteParamsEntry[ids.size()];
    	for (int i = 0; i < ids.size(); i++) {
    		String id = ids.get(i);
    		IObjectPK pk = new ObjectUuidPK(id);
    		BatchExecuteParamsEntry entry = new BatchExecuteParamsEntry(new Class[] {IObjectPK.class}, new Object[] {pk});
    		paramEntries[i] = entry;
    	}
    	BatchExecuteResult result = ((ISimpleBizBill)getBizInterface()).batchExecuteWithTrans("unAudit", paramEntries);
    	Throwable exs[] = result.getExceptions();
    	if (exs != null) {
    		for (int i = 0; i < exs.length; i++) {
    			Throwable ex = exs[i];
    			if (ex == null) continue;
    			String number = mapIdNumber.get(ids.get(i));
    			isCanUnAudit = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(number);
    			String causeClssName = (String) InvokeUtils.getFieldValue(ex, "causeClassName");
    			if (PublicUtils.equals(causeClssName, "javax.ejb.TransactionRolledbackLocalException")) {
    				msgRow.getCell("reason").setValue("事务交易已经回滚，未执行！");
    			} else {
    				msgRow.getCell("reason").setValue(ex.getMessage());
    			}
    		}
    		
    	} else {
    		MsgBoxEx.showInfo("反审核单据失败！");
    		
    	}
    	if (!isCanUnAudit) {
        	MsgBoxEx.showInfoTable(this, "部分反审核单据失败!", msgTblInfo);
        	SysUtil.abort();   		
        }
    	MsgBoxEx.showInfo("反审核成功！");
    	refreshList();
    }
    
    @Override
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
    	
    	checkSelected();
    	ArrayList<Integer> arySelRowIndex = getSelectList();
    	String[] colNames = new String[] {"number","reason"};
    	String[] titleNames = new String[] {"编码","原因"};
    	int[] colWidths = new int[] {130,250};
    	MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames,colWidths);
    	boolean isCanRemove = true;
    	KDTable msgTbl = msgTblInfo.getTable();
    	for (int i = 0; i < arySelRowIndex.size(); i++) {
    		Integer rowIndex = arySelRowIndex.get(i);
    		IRow row = tblMain.getRow(rowIndex);
    		BizEnumValueDTO bizEnumStatus = (BizEnumValueDTO) row.getCell("status").getValue();
    		String status = bizEnumStatus == null ? "" : (String) bizEnumStatus.getStateManager().getStateValue("value");
    		String statusName =  bizEnumStatus == null ? "" : bizEnumStatus.getAlias();
    		String id = (String) row.getCell("id").getValue();
    		try {
    			IDataObjectMutex mutex = new DataObjectMutex(); //检查数据锁
    			mutex.requestDataObjectLock(id);
    		} catch (Throwable ee) {
    			isCanRemove = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("reason").setValue(ee.getMessage());
    			continue;
    			
    		}
    		
    		if ("1".equals(status)) { //保存状态
    			continue;
    		} 
    		
    		if ("2".equals(status)) { //提交状态 
    			
    			isCanRemove = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("reason").setValue(String.format("已%s的单据,不允许删除!",statusName));
    			continue;
    			
    		} 
    		
    		//其他状态
			isCanRemove = false;
			IRow msgRow = msgTbl.addRow();
			msgRow.getCell("number").setValue(row.getCell("number").getValue());
			msgRow.getCell("reason").setValue(String.format("已%s的单据,不允许删除!",statusName));
			continue;
    	}	
    	if (!isCanRemove) {
    		MsgBoxEx.showInfoTable(this, "删除数据失败!", msgTblInfo);
    		SysUtil.abort();   		
    	}
    	
    	super.actionRemove_actionPerformed(e);
    }
    
    @Override
    public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
    	checkSelected();

    	ArrayList<Integer> arySelRowIndex = getSelectList(); 
    	int currentRowIndex = arySelRowIndex.get(0);
    	IRow row = tblMain.getRow(currentRowIndex);
    	
    	//--非保存、提交数据不允许编辑
    	BizEnumValueDTO bizEnumStatus = (BizEnumValueDTO) row.getCell("status").getValue();
    	String status = bizEnumStatus == null ? "0" : (String) bizEnumStatus.getStateManager().getStateValue("value");
    	String statusName =  bizEnumStatus == null ? "" : bizEnumStatus.getAlias();
    	if (!"1".equals(status) && !"2".equals(status)) {
    		throw new EASBizException(new NumericExceptionSubItem("",String.format("%s的数据不允许编辑!",statusName)));
    	}

		
    	super.actionEdit_actionPerformed(e);
    }
}
