package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.ctrl.kdf.table.KDTDataRequestManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;

public class KDTableUtils implements Serializable {
	
	public static void setPageRowCount(KDTable table, int pageRowCount) {
		table.getDataRequestManager().setDataRequestMode(KDTDataRequestManager.VIRTUAL_MODE_PAGE);
		table.getDataRequestManager().setPageRowCount(pageRowCount);
	}
	
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
}
