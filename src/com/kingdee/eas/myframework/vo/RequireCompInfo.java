package com.kingdee.eas.myframework.vo;

import java.io.Serializable;

import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.IKDTextComponent;
import com.kingdee.bos.ctrl.swing.KDPasswordField;

/**
 * ±ØÂ¼Ïî¿Ø¼þ
 * @author aben
 *
 */
public class RequireCompInfo implements Serializable {
	
	private KDTable table = null;
	private String colName = null;
	private IKDTextComponent comp = null;
	private KDPasswordField txtPwd = null;
	
	public RequireCompInfo(KDTable table, String colName) {
		this.table = table;
		this.colName = colName;
	}
	public RequireCompInfo(IKDTextComponent comp) {
		this.comp = comp;
	}
	public RequireCompInfo(KDPasswordField comp) {
		this.txtPwd = comp;
	}
	
	public boolean isTable() {
		return table != null;
	}
	public boolean isPwd() {
		return txtPwd != null;
	}
	public KDTable getTable() {
		return table;
	}
	public String getColName() {
		return colName;
	}
	public IKDTextComponent getComp() {
		return comp;
	} 
	
	public KDPasswordField getPwdComp() {
		return txtPwd;
	}
	
}
