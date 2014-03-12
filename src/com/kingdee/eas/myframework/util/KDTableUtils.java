package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import javax.swing.event.EventListenerList;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.KDTDataRequestManager;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditListener;
import com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.scm.common.client.helper.FormattedEditorFactory;

public class KDTableUtils implements Serializable {
	
	/**
	 * ListUI Table设置页取数最大行数
	 * @param table
	 * @param pageRowCount
	 */
	public static void setPageRowCount(KDTable table, int pageRowCount) {
		table.getDataRequestManager().setDataRequestMode(KDTDataRequestManager.VIRTUAL_MODE_PAGE);
		table.getDataRequestManager().setPageRowCount(pageRowCount);
	}
	
	/**
	 * 设置单元格融合
	 * @param table
	 * @param mergeColumnKeys
	 */
	public static void setColumnMerge(KDTable table, String[] mergeColumnKeys) {
		if (mergeColumnKeys != null && mergeColumnKeys.length > 0) {
			table.checkParsed();
			table.getGroupManager().setGroup(true);
			for (int i = 0; i < mergeColumnKeys.length; i++) {
				table.getColumn(mergeColumnKeys[i]).setGroup(true);
				table.getColumn(mergeColumnKeys[i]).setMergeable(true);
			}
		}
	}
	
    /**
     * 精度默认为2
     * @param tblMain
     * @param columnName
     * @param isNegatived 是否支持录入负数
     * @throws EASBizException
     * @throws BOSException
     */
    public static void formatDecimal(KDTable tblMain, String columnName,boolean isNegatived) {
    	KDTDefaultCellEditor editor = FormattedEditorFactory.getBigDecimalCellEditor(2, isNegatived);
		tblMain.getColumn(columnName).setEditor(editor);
		tblMain.getColumn(columnName).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		tblMain.getColumn(columnName).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
	}
    
	/**
	 * 根据当前单元格的值，自动向下复制，并触发editStopped事件
	 * @param tb
	 * @return
	 */
	public static boolean gridDownCopy(KDTable tb,boolean isIgnoreNotNull) {
		if (tb==null ) {
			return false;
		}
		int actRowIndex=tb.getSelectManager().getActiveRowIndex();
		int actColIndex=tb.getSelectManager().getActiveColumnIndex();
		if (actRowIndex < 0 || actColIndex < 0){
			return false;
		}
	    IColumn column=tb.getColumn(actColIndex);
	    if (column.getStyleAttributes().isLocked()) {
	    	return false;
	    }
	    Object val=tb.getCell(actRowIndex, actRowIndex).getValue();
	    try {
	    	int maxRowCount = tb.getRowCount();
	    	for (int i = actRowIndex; i < maxRowCount; i++)  {
	    		Object oldValue = tb.getRow(i).getCell(actColIndex).getValue();
	    		if (isIgnoreNotNull && oldValue != null) continue;
	    		//不可写列忽略
	    		if (tb.getRow(i).getCell(actColIndex).getStyleAttributes().isLocked()) continue;
	    		
	    		tb.getCell(i, actColIndex).setValue(val);
	    		// setValue不会触发editStopped事件,所以手动调用
	    		KDTEditEvent e =new KDTEditEvent(tb,null, val, i, actColIndex, false, 0);
	    		EventListenerList eventListenerList = tb.getListenerList();
	    		Object[] tbListeners = eventListenerList.getListenerList();
	    		for(Object l:tbListeners){
	    			if(l instanceof com.kingdee.bos.ctrl.kdf.table.event.KDTEditListener){
	    				KDTEditListener tbEditListener = (KDTEditListener)l;
	    				tbEditListener.editStopped(e);
	    				break;
	    			}
	    		}
		    }
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
