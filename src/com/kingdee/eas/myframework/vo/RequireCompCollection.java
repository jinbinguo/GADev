package com.kingdee.eas.myframework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.UIManager;

import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.IKDTextComponent;
import com.kingdee.bos.ctrl.swing.KDPasswordField;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.util.NumericExceptionSubItem;

public class RequireCompCollection implements Serializable {

	private HashMap<String, RequireCompInfo> requireComp = new HashMap<String, RequireCompInfo>();
	
	public void add(KDPasswordField pwd) {
		RequireCompInfo requireCompInfo = new RequireCompInfo(pwd);
		pwd.setBackground(UIManager.getColor("TextField.requiredBackground"));
		requireComp.put(String.valueOf(requireCompInfo.hashCode()),requireCompInfo);
	}
	public void add(IKDTextComponent txt) {
		RequireCompInfo requireCompInfo = new RequireCompInfo(txt);
		txt.setRequired(true);
		requireComp.put(String.valueOf(requireCompInfo.hashCode()),requireCompInfo);
	}
	public void add(KDTable tbl, String columnName) throws Exception {
		if (tbl == null) {
			throw new EASBizException(new NumericExceptionSubItem("","RequireCompCollection.add(table,columnName),参数table不能为空."));
		}
		if (tbl.getColumnIndex(columnName) == -1) {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("RequireCompCollection.add(table,columnName),表格列[%s]不存在.",columnName)));
		}
		RequireCompInfo requireCompInfo = new RequireCompInfo(tbl, columnName);
		tbl.getColumn(columnName).setRequired(true);
		String key = tbl.getName() + "-" + columnName;
		requireComp.put(key,requireCompInfo);
	}
	
	public int size() {
		return requireComp.size();
	}
	
	public Set<String> key() {
		return requireComp.keySet();
	}
	public RequireCompInfo get(String key) {
		return requireComp.get(key);
	}
	
	public void remove(IKDTextComponent txt) {
		String key = String.valueOf(txt.hashCode());
		requireComp.remove(key);
	}
	public void remove(KDTable tbl, String columnName) throws Exception {
		if (tbl == null) {
			throw new EASBizException(new NumericExceptionSubItem("","RequireCompCollection.add(table,columnName),参数table不能为空."));
		}
		if (tbl.getColumnIndex(columnName) == -1) {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("RequireCompCollection.add(table,columnName),表格列[%s]不存在.",columnName)));
		}
		String key = tbl.getName() + "-" + columnName;
		requireComp.remove(key);
	}
}
