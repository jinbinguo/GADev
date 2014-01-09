package com.kingdee.eas.myframework.comparators.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.comparators.ComparatorChain;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.eas.myframework.common.SortTypeEnum;

public class KDTableComparatorUtils {
	
	private KDTable tbl;
	private SortColumnCollection sortCols;
	
	public KDTableComparatorUtils(KDTable tbl, SortColumnCollection sortCols) {
		this.tbl = tbl;
		this.sortCols = sortCols;
	}
	
	public List<IRow> sort() throws Exception {
		ComparatorChain cc = new ComparatorChain();
		for (int i = 0; i < sortCols.size(); i++) {
			SortColumnInfo sortInfo = sortCols.get(i);
			String columnIndex = sortInfo.getColumnIndex();
			SortTypeEnum sortType = sortInfo.getSortType();
			boolean sort = sortType == null || sortType.getValue() == SortTypeEnum.DESC_VALUE;
			cc.addComparator(new KDTableComparator(tbl,columnIndex),sort);
		}
		List<IRow> lstRow = new ArrayList<IRow>();
		for (int i = 0; i < tbl.getRowCount(); i++) {
			lstRow.add(tbl.getRow(i));
		}
		Collections.sort(lstRow, cc);
		return lstRow;
	}
	
}
