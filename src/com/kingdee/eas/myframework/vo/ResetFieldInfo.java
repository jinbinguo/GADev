package com.kingdee.eas.myframework.vo;

import java.io.Serializable;
import java.util.Vector;

public class ResetFieldInfo implements Serializable {

	//是否分录
	private boolean isEntry;
	//分录名
	private String entryName;
	//分录标记
	private String columnFlagName;	
	//默认值
	private Object defaultValue;
	//
	private String propertyName;
	
	public ResetFieldInfo(String entryName,String columnFlagName, Object defaultValue) {
		isEntry = true;
		this.entryName = entryName;
		this.columnFlagName = columnFlagName;
		this.defaultValue = defaultValue;
	}
	
	public ResetFieldInfo(String propertyName, Object defaultValue) {
		isEntry = false;
		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
	}

	public boolean isEntry() {
		return isEntry;
	}

	public String getEntryName() {
		return entryName;
	}

	public String getColumnFlagName() {
		return columnFlagName;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public String getPropertyName() {
		return propertyName;
	}
}
