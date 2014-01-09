/**
 * output package name
 */
package com.kingdee.eas.myframework.template.base.client;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectListener;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.BizEnumValueDTO;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.vo.MsgTableInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.NumericExceptionSubItem;

/**
 * output class name
 */
public class SimpleDatabaseListUI extends AbstractSimpleDatabaseListUI {
	private static final Logger logger = CoreUIObject
			.getLogger(SimpleDatabaseListUI.class);

	public SimpleDatabaseListUI() throws Exception {
		super();
	}
	  
    public void onLoad() throws Exception {
    	super.onLoad();
    	actionCancel.setVisible(true);
    	actionCancelCancel.setVisible(true);
    }
    /**
     * 禁用功能
     * 
     * PS:取消EAS框架的[禁用]功能，原框架内的[禁用]功能有点怪怪,时好时坏
     */
    public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
    	//super.actionCancel_actionPerformed(e);
    	checkSelected();
    	
    	boolean isPartlyCancel = false; //是否部分已禁用
    	
    	ArrayList<Integer> arySelRowIndex = getSelectList(); //选中行
    	boolean isSelOneRow = arySelRowIndex.size() == 1;
    	
    	String[] colNames = new String[] {"number","status"};
    	String[] titleNames = new String[] {"编码","状态"};
    	MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames);
    	KDTable msgTbl = msgTblInfo.getTable();
    	
    	for (int i = 0; i < arySelRowIndex.size(); i++) {
    		Integer rowIndex = arySelRowIndex.get(i);
    		IRow row = tblMain.getRow(rowIndex);
    		BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell("deletedStatus").getValue();
    		String deletedStatus = bizEnumDeletedStatus == null ? "" : (String) bizEnumDeletedStatus.getStateManager().getStateValue("value");
    		if ("2".equals(deletedStatus)) { //禁用
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("status").setValue(row.getCell("deletedStatus").getValue());
    			isPartlyCancel = true;
    		}
    	}
    	boolean isCanCancel = false;  	
    	if (isPartlyCancel) {
    		if (isSelOneRow) {
    			if (MsgBox.YES == MsgBox.showConfirm2(this,"是否禁用选中数据?")) {
    				isCanCancel = true;
        		}		
    		} else {
	    		String msg = "选中数据部分已是禁用状态,是否继续禁用?";
	    		if (MsgBox.YES == MsgBoxEx.showConfirmTable(this, msg, msgTblInfo)) {
	    			isCanCancel = true;
	    		}
    		}
    	} else {
    		if (MsgBox.OK == MsgBox.showConfirm2("是否禁用选中数据?")) {
    			isCanCancel = true;
    		}
    	}
    	
    	if (isCanCancel) {
    		CoreBaseCollection corebaseCol = new CoreBaseCollection();
    		for (int i = 0; i < arySelRowIndex.size(); i++) {
        		Integer rowIndex = arySelRowIndex.get(i);
        		String id = tblMain.getRow(rowIndex).getCell("id").getValue().toString();
        		IObjectPK pk = new ObjectUuidPK(id);
        		CoreBaseInfo corebaseInfo = getBizInterface().getValue(pk);
        		corebaseInfo.put("deletedStatus", 2);
        		corebaseCol.add(corebaseInfo);
    		}
    		getBizInterface().update(corebaseCol);
    		refreshList();
    	}
    	
    	
    }

    /**
     * 启用功能
     * 
     * PS:取消EAS框架的[启用]功能，原框架内的[启用]功能未实现逻辑
     */
    public void actionCancelCancel_actionPerformed(ActionEvent actionevent)
    		throws Exception {
    	//super.actionCancelCancel_actionPerformed(actionevent);
    	checkSelected();
    	
    	boolean isPartlyCancelCancel = false; //是否部分已启用
    	
    	ArrayList<Integer> arySelRowIndex = getSelectList(); //选中行
    	boolean isSelOneRow = arySelRowIndex.size() == 1;
    	
    	String[] colNames = new String[] {"number","status"};
    	String[] titleNames = new String[] {"编码","状态"};
    	MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames);
    	KDTable msgTbl = msgTblInfo.getTable();
    	
    	for (int i = 0; i < arySelRowIndex.size(); i++) {
    		Integer rowIndex = arySelRowIndex.get(i);
    		IRow row = tblMain.getRow(rowIndex);
    		BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell("deletedStatus").getValue();
    		String deletedStatus = bizEnumDeletedStatus == null ? "" : (String) bizEnumDeletedStatus.getStateManager().getStateValue("value");
    		if ("1".equals(deletedStatus)) { //启用
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("status").setValue(row.getCell("deletedStatus").getValue());
    			isPartlyCancelCancel = true;
    		}
    	}
    	boolean isCanCancelCancel = false;  	
    	if (isPartlyCancelCancel) {
    		if (isSelOneRow) {
    			if (MsgBox.YES == MsgBox.showConfirm2(this,"是否启用选中数据?")) {
    				isCanCancelCancel = true;
        		}		
    		} else {
	    		String msg = "选中数据部分已是启用状态,是否继续启用?";
	    		if (MsgBox.YES == MsgBoxEx.showConfirmTable(this, msg, msgTblInfo)) {
	    			isCanCancelCancel = true;
	    		}
    		}
    	} else {
    		if (MsgBox.OK == MsgBox.showConfirm2("是否启用选中数据?")) {
    			isCanCancelCancel = true;
    		}
    	}
    	
    	if (isCanCancelCancel) {
    		CoreBaseCollection corebaseCol = new CoreBaseCollection();
    		for (int i = 0; i < arySelRowIndex.size(); i++) {
        		Integer rowIndex = arySelRowIndex.get(i);
        		String id = tblMain.getRow(rowIndex).getCell("id").getValue().toString();
        		IObjectPK pk = new ObjectUuidPK(id);
        		CoreBaseInfo corebaseInfo = getBizInterface().getValue(pk);
        		corebaseInfo.put("deletedStatus", 1);
        		corebaseCol.add(corebaseInfo);
    		}
    		getBizInterface().update(corebaseCol);
    		refreshList();
    	}
    }
    
    public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
    	checkSelected();
    	ArrayList<Integer> arySelRowIndex = getSelectList(); 
    	int currentRowIndex = arySelRowIndex.get(0);
    	IRow row = tblMain.getRow(currentRowIndex);
    	//--系统预设数据不允许编辑
    	boolean isScheduled = (Boolean) row.getCell("scheduled").getValue();
    	if (isScheduled) {
    		throw new EASBizException(new NumericExceptionSubItem("","系统预设数据不允许编辑!"));
    	}
    	//--已启用数据不允许编辑
    	BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell("deletedStatus").getValue();
    	String deletedStatus = bizEnumDeletedStatus == null ? "" : (String) bizEnumDeletedStatus.getStateManager().getStateValue("value");
		if ("1".equals(deletedStatus)) { //启用
			throw new EASBizException(new NumericExceptionSubItem("","已启用数据不允许编辑!"));
		}
    	
    	super.actionEdit_actionPerformed(e);
    }
    

    public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
    	//--系统预设数据不允许删除
    	//--已启用状态数据不允许删除
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
    		BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell("deletedStatus").getValue();
    		String deletedStatus = bizEnumDeletedStatus == null ? "" : (String) bizEnumDeletedStatus.getStateManager().getStateValue("value");
    		boolean isScheduled = (Boolean) row.getCell("scheduled").getValue();
    		if (isScheduled) {   			
    			isCanRemove = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("reason").setValue("系统预设,不允许删除!");
    			continue;
    		}
    		if ("1".equals(deletedStatus)) { //启用状态
    			isCanRemove = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue()); 			
    			msgRow.getCell("reason").setValue("启用状态,不允许删除.");
    		}  		
    	}
    	if (!isCanRemove) {
    		MsgBoxEx.showInfoTable(this, "删除数据失败!", msgTblInfo);
    		SysUtil.abort();   		
    	}
    	
    	
    	
    	super.actionRemove_actionPerformed(e);
    }
    
    
    protected void initListener() {
    	super.initListener();
    	tblMain.addKDTSelectListener(new KDTSelectListener() {
			public void tableSelectChanged(KDTSelectEvent arg0) {
				if(isMultiViewEnabled())
					 return;
				ArrayList selectSet = getSelectedIdValues();
				if(selectSet.size() == 1)
					actionEdit.setEnabled(true);
				else actionEdit.setEnabled(false);
				
			 } 		
    	});
    	
    }

	@Override
	protected ICoreBase getBizInterface() throws Exception {
		return null;
	}
	
	@Override
	protected String getEditUIName() {
		return null;
	}


}