package com.kingdee.eas.myframework.comparators.table;

import java.io.Serializable;

import com.kingdee.eas.myframework.common.SortTypeEnum;

public class SortColumnInfo implements Serializable{

	private String columnIndex;
	private SortTypeEnum sortType;
	
	public SortColumnInfo(String columnIndex, SortTypeEnum sortType) {
		this.columnIndex = columnIndex;
		this.sortType = sortType;
	}
	public String getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(String columnIndex) {
		this.columnIndex = columnIndex;
	}
	public SortTypeEnum getSortType() {
		return sortType;
	}
	public void setSortType(SortTypeEnum sortType) {
		this.sortType = sortType;
	}
	
	
}
