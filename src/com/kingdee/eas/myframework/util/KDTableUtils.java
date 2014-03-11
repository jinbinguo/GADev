package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import javax.swing.event.EventListenerList;

import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.KDTDataRequestManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditListener;

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
	 * 根据当前单元格的值，自动向下复制，并触发editStopped事件
	 * @param tb
	 * @return
	 */
	public static boolean gridDownCopy(KDTable tb) {
		if (tb==null ) {
			return false;
		}
		int actRow=tb.getSelectManager().getActiveRowIndex();
		int actCol=tb.getSelectManager().getActiveColumnIndex();
		if (actRow<0 || actCol<0){
			return false;
		}
	    IColumn column=tb.getColumn(actCol);
	    if (column.getStyleAttributes().isLocked()) {
	    	return false;
	    }
	    Object val=tb.getCell(actRow, actCol).getValue();
	    try {
	    	int max=tb.getRowCount();
	    	for (int i=actRow;i<max;i++)
		    {
	    		tb.getCell(i, actCol).setValue(val);
	    		// setValue不会触发editStopped事件,所以手动调用
	    		KDTEditEvent e =new KDTEditEvent(tb,null, val, i, actCol, false, 0);
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
