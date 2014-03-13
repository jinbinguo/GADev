package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import javax.swing.event.EventListenerList;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.KDTDataRequestManager;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditListener;
import com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.scm.common.client.helper.FormattedEditorFactory;
import com.kingdee.util.NumericExceptionSubItem;


public class KDTableUtils implements Serializable {
	
	/**
	 * ListUI Table����ҳȡ���������
	 * @param table
	 * @param pageRowCount
	 */
	public static void setPageRowCount(KDTable table, int pageRowCount) {
		table.getDataRequestManager().setDataRequestMode(KDTDataRequestManager.VIRTUAL_MODE_PAGE);
		table.getDataRequestManager().setPageRowCount(pageRowCount);
	}
	
	/**
	 * ���õ�Ԫ���ں�
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
     * ����Ĭ��Ϊ2
     * @param tblMain
     * @param columnName
     * @param isNegatived �Ƿ�֧��¼�븺��
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
	 * ���ݵ�ǰ��Ԫ���ֵ���Զ����¸��ƣ�������editStopped�¼�
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
	    		//����д�к���
	    		if (tb.getRow(i).getCell(actColIndex).getStyleAttributes().isLocked()) continue;
	    		
	    		tb.getCell(i, actColIndex).setValue(val);
	    		// setValue���ᴥ��editStopped�¼�,�����ֶ�����
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
	
	public static Integer[] getSelectedRowIndex(KDTable table,boolean isMultiRowSelected) throws Exception {
		KDTSelectManager sm = table.getSelectManager();
    	if (sm == null) {
    		throw new EASBizException(new NumericExceptionSubItem("","����ѡ��������!"));    		
    	}
    	HashMap<Integer,Integer> hashSelRow = new HashMap<Integer, Integer>();
    	ArrayList<KDTSelectBlock> lstBlocks = sm.getBlocks();
    	if (lstBlocks == null || lstBlocks.isEmpty()) {
    		throw new EASBizException(new NumericExceptionSubItem("","����ѡ��������!"));
    		
    	}
    	for (int i = 0; i < lstBlocks.size(); i++) {
    		KDTSelectBlock sb = lstBlocks.get(i);
    		for (int j = sb.getBeginRow(); j <= sb.getEndRow(); j++) {
    			hashSelRow.put(j, j);
    		}
    	}
    	if (!isMultiRowSelected && hashSelRow.size() > 1) {
    		throw new EASBizException(new NumericExceptionSubItem("","��ѡ��һ��������!"));
    	}
    	Integer[] iSel = new Integer[hashSelRow.size()];
    	Set<Integer> setSel = hashSelRow.keySet();
    	iSel = PublicUtils.setToInteger(setSel);
    	
    	return iSel;
    	
    	
    	
		
		
	}
}
