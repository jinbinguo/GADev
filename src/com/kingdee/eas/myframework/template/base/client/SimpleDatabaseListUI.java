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
     * ���ù���
     * 
     * PS:ȡ��EAS��ܵ�[����]���ܣ�ԭ����ڵ�[����]�����е�ֹ�,ʱ��ʱ��
     */
    public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
    	//super.actionCancel_actionPerformed(e);
    	checkSelected();
    	
    	boolean isPartlyCancel = false; //�Ƿ񲿷��ѽ���
    	
    	ArrayList<Integer> arySelRowIndex = getSelectList(); //ѡ����
    	boolean isSelOneRow = arySelRowIndex.size() == 1;
    	
    	String[] colNames = new String[] {"number","status"};
    	String[] titleNames = new String[] {"����","״̬"};
    	MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames);
    	KDTable msgTbl = msgTblInfo.getTable();
    	
    	for (int i = 0; i < arySelRowIndex.size(); i++) {
    		Integer rowIndex = arySelRowIndex.get(i);
    		IRow row = tblMain.getRow(rowIndex);
    		BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell("deletedStatus").getValue();
    		String deletedStatus = bizEnumDeletedStatus == null ? "" : (String) bizEnumDeletedStatus.getStateManager().getStateValue("value");
    		if ("2".equals(deletedStatus)) { //����
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("status").setValue(row.getCell("deletedStatus").getValue());
    			isPartlyCancel = true;
    		}
    	}
    	boolean isCanCancel = false;  	
    	if (isPartlyCancel) {
    		if (isSelOneRow) {
    			if (MsgBox.YES == MsgBox.showConfirm2(this,"�Ƿ����ѡ������?")) {
    				isCanCancel = true;
        		}		
    		} else {
	    		String msg = "ѡ�����ݲ������ǽ���״̬,�Ƿ��������?";
	    		if (MsgBox.YES == MsgBoxEx.showConfirmTable(this, msg, msgTblInfo)) {
	    			isCanCancel = true;
	    		}
    		}
    	} else {
    		if (MsgBox.OK == MsgBox.showConfirm2("�Ƿ����ѡ������?")) {
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
     * ���ù���
     * 
     * PS:ȡ��EAS��ܵ�[����]���ܣ�ԭ����ڵ�[����]����δʵ���߼�
     */
    public void actionCancelCancel_actionPerformed(ActionEvent actionevent)
    		throws Exception {
    	//super.actionCancelCancel_actionPerformed(actionevent);
    	checkSelected();
    	
    	boolean isPartlyCancelCancel = false; //�Ƿ񲿷�������
    	
    	ArrayList<Integer> arySelRowIndex = getSelectList(); //ѡ����
    	boolean isSelOneRow = arySelRowIndex.size() == 1;
    	
    	String[] colNames = new String[] {"number","status"};
    	String[] titleNames = new String[] {"����","״̬"};
    	MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames);
    	KDTable msgTbl = msgTblInfo.getTable();
    	
    	for (int i = 0; i < arySelRowIndex.size(); i++) {
    		Integer rowIndex = arySelRowIndex.get(i);
    		IRow row = tblMain.getRow(rowIndex);
    		BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell("deletedStatus").getValue();
    		String deletedStatus = bizEnumDeletedStatus == null ? "" : (String) bizEnumDeletedStatus.getStateManager().getStateValue("value");
    		if ("1".equals(deletedStatus)) { //����
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue());
    			msgRow.getCell("status").setValue(row.getCell("deletedStatus").getValue());
    			isPartlyCancelCancel = true;
    		}
    	}
    	boolean isCanCancelCancel = false;  	
    	if (isPartlyCancelCancel) {
    		if (isSelOneRow) {
    			if (MsgBox.YES == MsgBox.showConfirm2(this,"�Ƿ�����ѡ������?")) {
    				isCanCancelCancel = true;
        		}		
    		} else {
	    		String msg = "ѡ�����ݲ�����������״̬,�Ƿ��������?";
	    		if (MsgBox.YES == MsgBoxEx.showConfirmTable(this, msg, msgTblInfo)) {
	    			isCanCancelCancel = true;
	    		}
    		}
    	} else {
    		if (MsgBox.OK == MsgBox.showConfirm2("�Ƿ�����ѡ������?")) {
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
    	//--ϵͳԤ�����ݲ�����༭
    	boolean isScheduled = (Boolean) row.getCell("scheduled").getValue();
    	if (isScheduled) {
    		throw new EASBizException(new NumericExceptionSubItem("","ϵͳԤ�����ݲ�����༭!"));
    	}
    	//--���������ݲ�����༭
    	BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell("deletedStatus").getValue();
    	String deletedStatus = bizEnumDeletedStatus == null ? "" : (String) bizEnumDeletedStatus.getStateManager().getStateValue("value");
		if ("1".equals(deletedStatus)) { //����
			throw new EASBizException(new NumericExceptionSubItem("","���������ݲ�����༭!"));
		}
    	
    	super.actionEdit_actionPerformed(e);
    }
    

    public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
    	//--ϵͳԤ�����ݲ�����ɾ��
    	//--������״̬���ݲ�����ɾ��
    	checkSelected();
    	ArrayList<Integer> arySelRowIndex = getSelectList();
    	String[] colNames = new String[] {"number","reason"};
    	String[] titleNames = new String[] {"����","ԭ��"};
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
    			msgRow.getCell("reason").setValue("ϵͳԤ��,������ɾ��!");
    			continue;
    		}
    		if ("1".equals(deletedStatus)) { //����״̬
    			isCanRemove = false;
    			IRow msgRow = msgTbl.addRow();
    			msgRow.getCell("number").setValue(row.getCell("number").getValue()); 			
    			msgRow.getCell("reason").setValue("����״̬,������ɾ��.");
    		}  		
    	}
    	if (!isCanRemove) {
    		MsgBoxEx.showInfoTable(this, "ɾ������ʧ��!", msgTblInfo);
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