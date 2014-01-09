package com.kingdee.eas.myframework.vo;

import java.io.Serializable;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTSortManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.util.NumericExceptionSubItem;

public class MsgTableInfo implements Serializable{
	
	private static final long serialVersionUID = -745012981765014163L;
	private String[] colNames;
	private String[] titleNames;
	private int[] colWidths;
	
	private KDTable tblMain = new KDTable();
	
	public MsgTableInfo(String[] colNames,String[] titleNames, int[] colWidths) throws BOSException,EASBizException{
		if (colNames == null)
			throw new EASBizException(new NumericExceptionSubItem("","���Ի���[�б�ʶ]����Ϊ��!"));
		if (titleNames == null)
			throw new EASBizException(new NumericExceptionSubItem("","���Ի���[�б���]����Ϊ��!"));
		if (colNames.length != titleNames.length)
			throw new EASBizException(new NumericExceptionSubItem("","���Ի����б�ʶ���б����������һ��!"));
		if (colWidths != null && colWidths.length != colNames.length)
			throw new EASBizException(new NumericExceptionSubItem("","���Ի����п�����б����������һ��!"));
		
		this.colNames = colNames;
		this.titleNames = titleNames;
		this.colWidths = colWidths;
		parseTable();
	} 
	
	public MsgTableInfo(KDTable tbl) {
		this.tblMain = tbl;
	}
	
	public MsgTableInfo(String[] colNames, String[] titleNames) throws BOSException,EASBizException{
		this(colNames, titleNames,null);
	}
	
	private void parseTable() {
		tblMain.getSelectManager().setSelectMode(KDTSelectManager.ROW_SELECT);
		tblMain.setAutoscrolls(true);
		KDTSortManager sm = new KDTSortManager(tblMain);
		sm.setSortAuto(true);
		tblMain.setEditable(false);
		IRow headRow = tblMain.addHeadRow();
		for (int i = 0; i < colNames.length; i++) {
			IColumn column = tblMain.addColumn();
			column.setKey(colNames[i]);
			column.setSortable(true);
			headRow.getCell(colNames[i]).setValue(titleNames[i]);
		}
		if (colWidths != null) {
			for (int i = 0; i < colNames.length; i++) {
				tblMain.getColumn(i).setWidth(colWidths[i]);
			}
		}
	}
	
	public KDTable getTable() {
		return this.tblMain;
	}
	
	public void addRow(Object[] row) throws BOSException,EASBizException{
		if (row == null) 
			throw new EASBizException(new NumericExceptionSubItem("","���Ի�����������в���Ϊ��!"));
		if (row.length != colNames.length)
			throw new EASBizException(new NumericExceptionSubItem("","���Ի�����������е������������б�������һ��!"));
		IRow newrow = tblMain.addRow();
		for (int i = 0; i < row.length; i++) {
			newrow.getCell(colNames[i]).setValue(row[i]);
		}
	}

	public String[] getColNames() {
		return colNames;
	}

	public void setColNames(String[] colNames) {
		this.colNames = colNames;
	}

	public String[] getTitleNames() {
		return titleNames;
	}

	public void setTitleNames(String[] titleNames) {
		this.titleNames = titleNames;
	}

	public int[] getColWidths() {
		return colWidths;
	}

	public void setColWidths(int[] colWidths) {
		this.colWidths = colWidths;
	}
	
	
}
