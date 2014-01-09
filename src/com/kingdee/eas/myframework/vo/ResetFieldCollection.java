package com.kingdee.eas.myframework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.util.StringUtils;

public class ResetFieldCollection implements Serializable {
	
	private Vector<ResetFieldInfo> vecProperty = new Vector<ResetFieldInfo>();
	private HashMap<String,Vector<ResetFieldInfo>> hashEntryColumn = new HashMap<String,Vector<ResetFieldInfo>>();
	
	public void add(ResetFieldInfo resetFieldInfo) {
		if (resetFieldInfo == null) return;
		if (resetFieldInfo.isEntry()) {
			String entryName = resetFieldInfo.getEntryName();
			Vector<ResetFieldInfo> vecEntry = hashEntryColumn.get(entryName);
			if (vecEntry==null) vecEntry = new Vector<ResetFieldInfo>();
			vecEntry.add(resetFieldInfo);
			
			hashEntryColumn.put(entryName, vecEntry);
		} else {			
			vecProperty.add(resetFieldInfo);
			
		}
	}
	
	public Vector<ResetFieldInfo> getVecProperty() {
		return vecProperty;
	}
	public String[] getEntryNames() {
		return PublicUtils.hashKeyToArray(hashEntryColumn);
	}
	
	public Vector<ResetFieldInfo> getEntryColumn(String key) {
		if (StringUtils.isEmpty(key)) return null;
		return hashEntryColumn.get(key);
	}
	
	

}
