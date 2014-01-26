package com.kingdee.eas.myframework.util;

import java.awt.Color;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.vo.BaseCollectionWithSort;
import com.kingdee.eas.myframework.vo.MsgTableInfo;
import com.kingdee.eas.util.SysUtil;

public class PublicUtils implements Serializable {

	private static final long serialVersionUID = 4879628438694911002L;
	
	//--已打印行颜色
	public static final Color PRINTED_COLOR = new Color(151, 203, 255);
	//--已审核行颜色
	public static final Color AUDITED_COLOR = new Color(235, 171, 181);
	//--已关闭行颜色
	public static final Color CLOSED_COLOR = new Color(254,163,197);
	//--已禁用行颜色
	public static final Color CANCEL_COLOR = new Color(235, 171, 181);
	//--已启用行颜色
	public static final Color CANCELCANCEL_COLOR = new Color(151, 203, 255);
	
	public static final String CRYPTO_KEY = "I'M JINBIN_GUO";
	
	public static final BigDecimal BIGDECIMAL0 = BigDecimal.ZERO;
	public static final BigDecimal BIGDECIMAL100 = new BigDecimal(100.00);
	public static final BigDecimal BIGDECIMAL1 = BigDecimal.ONE;
	
	/**
	 * 比较两个对象是否一样
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean equals(Object obj1, Object obj2) {
		try {
			if (obj1 == null && obj2 == null) return true;
			else if ((obj1 == null && obj2 != null) || (obj1 != null && obj2 == null)) return false;
			else if (obj1 != null && obj2 != null){
				String className1 = obj1.getClass().getName();
				String className2 = obj2.getClass().getName();
				if (!className1.equals(className2)) {
					String msg = "比较函数使用错误,比较对象不是同一种类型！";
					String[] colNames = new String[] {"param","className","value"};
					String[] titleNames = new String[] {"参数","对象类名","值"};				
					MsgTableInfo msgTblInfo = new MsgTableInfo(colNames,titleNames);
					
					msgTblInfo.addRow(new Object[] {"参数1",className1,obj1.toString()});
					msgTblInfo.addRow(new Object[] {"参数2",className2,obj2.toString()});				
					MsgBoxEx.showInfoTable(null, msg, msgTblInfo);
					SysUtil.abort();
				}
				return obj1.equals(obj2);
			}
			return false;
		} catch (Exception e) {
			SysUtil.abort();
			return false;
		}
	}
	public static boolean isEmpty(BaseCollectionWithSort cols) {
		return cols == null || cols.isEmpty();
	}
	public static boolean isEmpty(List lst) {
		return lst == null || lst.isEmpty();
	}
	public static boolean isEmpty(Set set) {
		return set == null || set.isEmpty();
	}
	public static boolean isEmpty(Map map) {
		return map == null || map.isEmpty();
	}
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}
	
	public static String[] setToString(Set<String> set) {
		if (set == null || set.isEmpty()) return null;
		String[] str = new String[set.size()];
		set.toArray(str);
		return str;
	}
	public static String[] vectorToString(Vector<String> vec) {
		if (vec == null || vec.isEmpty()) return null;
		String[] str = new String[vec.size()];
		vec.toArray(str);
		return str;
	}
	
	public static String[] hashKeyToArray(Map<String,?> hash) {
		if (hash == null || hash.isEmpty()) return null;
		Set<String> set = hash.keySet();
		return setToString(set);
	}
	
	public static IObjectPK[] ArrayToPKs(String[] ids) {
		if(ids == null) return null;
		IObjectPK[] pks = new IObjectPK[ids.length];
		for (int i = 0; i < ids.length; i++) {
			pks[i] = new ObjectUuidPK(ids[i]);
		}
		return pks;
	}
	
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
