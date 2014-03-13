/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Vector;

import javax.swing.KeyStroke;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.KDTableHelper;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.foot.KDTFootManager;
import com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUIPIEx;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.myframework.util.KDTableUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;

/**
 * output class name
 */
public class RepairWOAllocateExpenseUI extends AbstractRepairWOAllocateExpenseUI {
    private static final Logger logger = CoreUIObject.getLogger(RepairWOAllocateExpenseUI.class);
    
    private BigDecimal uiAmount= null; //拆分前金额
    private BigDecimal uiPercent = null; //拆分前费用百分比
    private IRow originalRow = null; //拆分原始行
    private RepairWOEditUIPIEx parentUI = null; //来源UI
    private KDTable rwoTable = null; //拆分来源Table

    public RepairWOAllocateExpenseUI() throws Exception {
        super();
    }

    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	tblMain.checkParsed();
    	for (int i = 0; i < 5; i++) {
    		IRow row = tblMain.addRow();
    		if (i != 0) row.getStyleAttributes().setLocked(true);
    	}
    	
    	
    	KDTableUtils.formatDecimal(tblMain, "percent", false);
    	KDTableUtils.formatDecimal(tblMain, "amount", false);
    	
    	Map uictx = getUIContext();
    	parentUI = (RepairWOEditUIPIEx) uictx.get("ui");
    	rwoTable = (KDTable) uictx.get("kdtable");
    	originalRow = (IRow) uictx.get("originalRow");
    	uiAmount = PublicUtils.getBigDecimal(originalRow.getCell("amount").getValue());
    	uiPercent = PublicUtils.getBigDecimal(originalRow.getCell("allocateExenseRate").getValue());
    	if (uiPercent == null || uiPercent.compareTo(BigDecimal.ZERO) == 0) uiPercent = new BigDecimal(100.00);
    	uiAmount = uiAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
    	actionCancel.setEnabled(true);
    	
    	appendFoot(BigDecimal.ZERO,BigDecimal.ZERO);
    	tblMain.getViewManager().setFreezeView(5, 2);
    	tblMain.setAutoscrolls(false);
    	
    	tblMain.getInputMap().remove(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
    	//tblMain.getInputMap(1).remove(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,64));
    	//KDTableHelper.releaseEnter(tblMain);
    }
    @Override
    protected void initListener() {
    	super.initListener();
    	tblMain.addKDTEditListener(new KDTEditAdapter() {
    		@Override
    		public void editStopped(KDTEditEvent e) {
    			try {
    				tblMain_editStopped(e);
    			} catch (Exception exc) {UIUtils.handUIException(exc);}
    		}
    	});
    	
    }
   
    private void tblMain_editStopped(KDTEditEvent e) throws Exception {
    	int rowIndex = e.getRowIndex();
    	int colIndex = e.getColIndex();
    	int percentIndex = tblMain.getColumnIndex("percent");
    	int amountIndex = tblMain.getColumnIndex("amount");
    	
    	Object oldValue = e.getOldValue();
    	Object newValue = e.getValue();
    	if (PublicUtils.equals(oldValue, newValue)) return;
    	if (BigDecimal.ZERO.compareTo(PublicUtils.getBigDecimal(newValue)) == 0) 
    		tblMain.getRow(rowIndex).getCell(colIndex).setValue(null);
    	
    	BigDecimal curPercent = PublicUtils.getBigDecimal(tblMain.getRow(rowIndex).getCell("percent").getValue());
    	BigDecimal curAmount = PublicUtils.getBigDecimal(tblMain.getRow(rowIndex).getCell("amount").getValue());
    	
    	BigDecimal totalPercent = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (int i = 0; i < tblMain.getRowCount(); i++) {
			BigDecimal percent = PublicUtils.getBigDecimal(tblMain.getRow(i).getCell("percent").getValue());
			BigDecimal amount = PublicUtils.getBigDecimal(tblMain.getRow(i).getCell("amount").getValue());
			totalPercent = totalPercent.add(percent);
			totalAmount = totalAmount.add(amount);	
		}
		
    	
    	if (colIndex == percentIndex) {
    		//计算当前行金额
    		totalAmount = totalAmount.subtract(curAmount);
    		curAmount = uiAmount.multiply(curPercent.divide(uiPercent,10,BigDecimal.ROUND_HALF_UP)).setScale(2,BigDecimal.ROUND_HALF_UP);
    		if (totalPercent.compareTo(uiPercent) > 0) {
    			actionOK.setEnabled(false);
    			tblMain.getEditManager().editCellAt(rowIndex, colIndex);
    			totalAmount = totalAmount.add(curAmount);
    			tblMain.getRow(rowIndex).getCell("amount").setValue(curAmount.compareTo(BigDecimal.ZERO) == 0 ? null : curAmount);
    			appendFoot(totalPercent,totalAmount);
    			e.setCancel(true);
    			return;
    		}  else if (totalPercent.compareTo(uiPercent) < 0) {
    			actionOK.setEnabled(false);
    		} else if (totalPercent.compareTo(uiPercent) == 0) {
    			curAmount = uiAmount.subtract(totalAmount);
    		}
    		tblMain.getRow(rowIndex).getCell("amount").setValue(curAmount.compareTo(BigDecimal.ZERO) == 0 ? null : curAmount);
    		totalAmount = totalAmount.add(curAmount);
    		
    	} else if (colIndex == amountIndex) {
    		//计算当前行百分比
    		totalPercent = totalPercent.subtract(curPercent);
    		curPercent = curAmount.divide(uiAmount,10,BigDecimal.ROUND_HALF_UP).multiply(uiPercent).setScale(2,BigDecimal.ROUND_HALF_UP);
    		if (totalAmount.compareTo(uiAmount) > 0) {
    			actionOK.setEnabled(false);
    			tblMain.getEditManager().editCellAt(rowIndex, colIndex);
    			totalPercent = totalPercent.add(curPercent);
    			tblMain.getRow(rowIndex).getCell("percent").setValue(curPercent.compareTo(BigDecimal.ZERO) == 0 ? null : curPercent);
    			appendFoot(totalPercent,totalAmount);
    			e.setCancel(true);
    			return;
    		}  else if (totalAmount.compareTo(uiAmount) < 0) {
    			actionOK.setEnabled(false);
    		} else if (totalAmount.compareTo(uiAmount) == 0) {
    			curPercent = uiPercent.subtract(totalPercent);
    		}
    		totalPercent = totalPercent.add(curPercent);
    		tblMain.getRow(rowIndex).getCell("percent").setValue(curPercent.compareTo(BigDecimal.ZERO) == 0 ? null : curPercent);
    		
    	}
    	if (totalAmount.compareTo(uiAmount) == 0 && totalPercent.compareTo(uiPercent) == 0) {
    		actionOK.setEnabled(true);
    	}
    	
    	if (curPercent.compareTo(BigDecimal.ZERO) == 0 && curAmount.compareTo(BigDecimal.ZERO) == 0) {
    		
    		if (rowIndex < 4) {
    			Object percent = tblMain.getRow(rowIndex+1).getCell("percent").getValue();
    	    	Object amount = tblMain.getRow(rowIndex+1).getCell("amount").getValue();
    	    	if (percent == null && amount == null)
    	    		setRowLocked(rowIndex+1,true);
    		}
    		appendFoot(totalPercent,totalAmount);
    		return;
    	}
  	
    	appendFoot(totalPercent,totalAmount);
    	if (rowIndex < 4) {
    		if (!actionOK.isEnabled()) setRowLocked(rowIndex+1,false);
    		else setRowLocked(rowIndex+1,true);	
    	}
    }
    
    private void setRowLocked(int rowIndex,boolean isLocked) {
    	Object percent = tblMain.getRow(rowIndex).getCell("percent").getValue();
    	Object amount = tblMain.getRow(rowIndex).getCell("amount").getValue();
    	if (isLocked) {
    		if (percent != null || amount != null) {
        		tblMain.getRow(rowIndex).getStyleAttributes().setLocked(isLocked);
        	}
    	} else {
    		tblMain.getRow(rowIndex).getStyleAttributes().setLocked(isLocked);
    	}
    	
    	
    		
    		
    		
    }
    
    
    
    private void appendFoot(BigDecimal totalPercent,BigDecimal totalAmount) {
  
    	KDTFootManager footManager = tblMain.getFootManager();
		if (footManager == null) {
			footManager = new KDTFootManager(tblMain);
			tblMain.setFootManager(footManager);
			footManager.addFootView();
		} 

		IRow footRow = footManager.getFootRow(0);
		if (footRow == null) footRow =footManager.addFootRow(0);
		footRow.getCell("percent").getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell("amount").getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell("percent").getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		footRow.getCell("amount").getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		
		footRow.getCell("percent").setValue(totalPercent);
		footRow.getCell("amount").setValue(totalAmount);
		footManager.addIndexText(0,"合");
		
		
		IRow footRow1 = footManager.getFootRow(1);
		if (footRow1 == null) footRow1 =footManager.addFootRow(1);
		footManager.addIndexText(1,"余");
		footRow1.getCell("percent").getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow1.getCell("percent").getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		footRow1.getCell("percent").getStyleAttributes().setFontColor(Color.RED);
		
		footRow1.getCell("amount").getStyleAttributes().setNumberFormat("%r{#,##0.00}f");		
		footRow1.getCell("amount").getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		footRow1.getCell("amount").getStyleAttributes().setFontColor(Color.RED);
		
		BigDecimal remainingPercent = uiPercent.subtract(totalPercent);
		BigDecimal remainingAmount = uiAmount.subtract(totalAmount);
		if (remainingPercent.compareTo(BigDecimal.ZERO) == 0) {
			footManager.getFoot().removeRow(1);
		} else {
			footRow1.getCell("percent").setValue(remainingPercent);
			footRow1.getCell("amount").setValue(remainingAmount);
		}
		

    }
    
    @Override
    public void actionOK_actionPerformed(ActionEvent e) throws Exception {
    	Vector<Integer> vec = new Vector<Integer>();
    	for (int i = 0; i < tblMain.getRowCount(); i++) {
    		IRow row = tblMain.getRow(i);
    		if (row.getCell("percent").getValue() != null)
    			vec.add(i);
    	}
    	Integer[] selRows = PublicUtils.vectorToInteger(vec);
    	Arrays.sort(selRows);
    //	IRow newRow = (IRow) originalRow.clone();
    	
    	BigDecimal qty = PublicUtils.getBigDecimal(originalRow.getCell("qty").getValue());
		String id = ((RepairWORWOItemSpEntryInfo)originalRow.getUserObject()).getString("id");
    	for (int i = 0; i < selRows.length; i++) {
    		int rowIndex = selRows[i];
    		BigDecimal percent = PublicUtils.getBigDecimal(tblMain.getRow(rowIndex).getCell("percent").getValue());
    		BigDecimal amount = PublicUtils.getBigDecimal(tblMain.getRow(rowIndex).getCell("amount").getValue());
    		
    		
			
    		if (i == 0) { //第一行
    			originalRow.getCell("originalQty").setValue(qty);
    			originalRow.getCell("originalEntryId").setValue(id);
    			originalRow.getCell("qty").setValue(qty.multiply(percent.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    			parentUI.calItemSpEntryAmount(originalRow);
    		} else if (i == selRows.length -1) { //最后一行
    			IRow row = (IRow) originalRow.clone();
    			rwoTable.addRow(originalRow.getRowIndex()+i, row);
    			((RepairWORWOItemSpEntryInfo)row.getUserObject()).setId(BOSUuid.create("FF1F0E1A"));
    			row.getCell("originalQty").setValue(qty);
    			row.getCell("originalEntryId").setValue(id);
    			row.getCell("qty").setValue(qty.multiply(percent.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    			parentUI.calItemSpEntryAmount(row);
    			
    			
    		} else { //其他行
    			IRow row = (IRow) originalRow.clone();
    			rwoTable.addRow(originalRow.getRowIndex()+i, row);
    			((RepairWORWOItemSpEntryInfo)row.getUserObject()).setId(BOSUuid.create("FF1F0E1A"));
    			row.getCell("originalQty").setValue(qty);
    			row.getCell("originalEntryId").setValue(id);
    			row.getCell("qty").setValue(qty.multiply(percent.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    			parentUI.calItemSpEntryAmount(row);
    		}
    		
    	}
    	
    	
    //	destroyWindow();
    }
    
    @Override
    public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
    	destroyWindow();
    }

}