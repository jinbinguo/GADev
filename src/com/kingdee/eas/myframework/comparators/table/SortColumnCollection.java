package com.kingdee.eas.myframework.comparators.table;

import java.io.Serializable;
import java.util.Vector;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.eas.myframework.vo.BaseCollectionWithOrder;

public class SortColumnCollection  extends BaseCollectionWithOrder implements Serializable {

	public void add(SortColumnInfo sortColumnInfo) throws Exception {
		addObject(sortColumnInfo.getColumnIndex(), sortColumnInfo);
	}
	
	public SortColumnInfo get(int index) throws Exception {
		return (SortColumnInfo) getObject(index);
	}
	public SortColumnInfo get(String key) throws Exception {
		return (SortColumnInfo) getObject(key);
	}
	
	
	
}
