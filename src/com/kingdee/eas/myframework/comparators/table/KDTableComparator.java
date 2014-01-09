package com.kingdee.eas.myframework.comparators.table;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.collections.comparators.ComparableComparator;

import com.kingdee.bos.ctrl.extendcontrols.IFormatter;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.util.editor.ICellEditor;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.util.StringUtils;

public class KDTableComparator implements Comparator, Serializable {
	
	private String columnIndex;
	private Comparator comparator;
	private KDTable tbl;


	public KDTableComparator(KDTable tbl, String columnIndex) {
		this.columnIndex = columnIndex;
		this.tbl = tbl;
		comparator = (Comparator) (ComparableComparator.getInstance());
	}
	public KDTableComparator(String columnIndex, Comparator comparator) {
		this.columnIndex = columnIndex;
		this.comparator = comparator;
	}
	public KDTableComparator(KDTable tbl, String columnIndex, Comparator comparator) {
		this.tbl = tbl;
		this.columnIndex = columnIndex;
		this.comparator = comparator;
	}
	
	public String getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(String columnIndex) {
		this.columnIndex = columnIndex;
	}


	public Comparator getComparator() {
		return comparator;
	}


	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}

	@Override
	public int hashCode() {
		return comparator.hashCode();
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof KDTableComparator))
			return false;

		KDTableComparator tblComparator = (KDTableComparator) o;
		
		if (!comparator.equals(tblComparator.comparator))
			return false;
		if (StringUtils.isEmpty(columnIndex))
			return columnIndex.equals(tblComparator.columnIndex);
		else
			return StringUtils.isEmpty(tblComparator.columnIndex);
	}

	public int compare(Object o1, Object o2) {
		if (StringUtils.isEmpty(columnIndex))
			return comparator.compare(o1, o2);		
		Object value1 = o1;
		Object value2 = o2;
		if (o1 instanceof IRow) {
			if (o2 instanceof IRow) {
				try {
					value1 = ((IRow)o1).getCell(columnIndex).getValue();
					value2 =  ((IRow)o2).getCell(columnIndex).getValue();
				} catch (Exception e) {			
					MsgBoxEx.showInfo(String.format("列标识[%s]不存在", columnIndex));
					SysUtil.abort();
				} 
			}
		}
		if (value1 == value2)
			return 0;
		if (value1 == null && value2 != null)
			return -1;
		if (value1 != null && value2 == null)
			return 1;
		
        //数字
		if (value1 instanceof Number){
			if (value2 instanceof Number) {
				double d1 = ((Number) value1).doubleValue();
				double d2 = ((Number) value2).doubleValue();
				if (d1 == d2)
					return 0;
				return d1 >= d2 ? 1 : -1;
			} else {
				return value1.toString().compareTo(value2.toString());
			}
		}
        //字符串
		if (value1 instanceof String){
			if (value2 instanceof String)
				return comparator.compare(value1, value2);
			else
				return ((String) value1).compareTo(value2.toString());
		}
		//date
		if (value1 instanceof Date){
			if (value2 instanceof Date)
				return ((Date) value1).compareTo((Date) value2);
			else
				return value1.toString().compareTo(value2.toString());
		}
		//Calendar
		if (value1 instanceof Calendar){
			if (value2 instanceof Calendar) {
				long d1 = ((Calendar) value1).getTimeInMillis();
				long d2 = ((Calendar) value2).getTimeInMillis();
				if (d1 == d2)
					return 0;
				return d1 >= d2 ? 1 : -1;
			} else {
				return value1.toString().compareTo(value2.toString());
			}
		}
		//CoreBaseInfo
		if (value1 instanceof CoreBaseInfo) {
			if (value2 instanceof CoreBaseInfo) {
				ICellEditor cellEditor = tbl.getColumn(columnIndex).getEditor();
				if (cellEditor != null && cellEditor.getComponent() != null && cellEditor.getComponent() instanceof KDBizPromptBox) {
						IFormatter displayFormatter=((KDBizPromptBox)cellEditor.getComponent()).getDisplayFormatter();
						String displayFormat = displayFormatter.toString().replace("$","");
						Object ctrlValue1=((CoreBaseInfo)value1).get(displayFormat);
						Object ctrlValue2=((CoreBaseInfo)value2).get(displayFormat);
						if (ctrlValue1 == ctrlValue2)
							return 0;
						if (ctrlValue1 == null && ctrlValue2 != null)
							return -1;
						if (ctrlValue1 != null && ctrlValue2 == null)
							return 1;
						if (ctrlValue1 instanceof String){
							if (ctrlValue2 instanceof String)
								return comparator.compare(ctrlValue1, ctrlValue2);
							else
								return ctrlValue1.toString().compareTo(ctrlValue2.toString());
						}
				}
			}				
			} else {
				return value1.toString().compareTo(value2.toString());
		}
		//Comparable
		if (value1 instanceof Comparable) {
			if (value2 instanceof Comparable)
				return ((Comparable) value1).compareTo((Comparable) value2);
			else
				return value1.toString().compareTo(value2.toString());
		} else {
			return value1.toString().compareTo(value2.toString());
		}
	}
}
