package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.util.Date;

import com.kingdee.bos.Context;
import com.kingdee.bos.dao.query.ISQLExecutor;
import com.kingdee.bos.dao.query.SQLExecutor;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class DBUtils implements Serializable {
	/**
	 * ��ȡ������ʱ��
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static Date getAppServerTime(Context ctx) throws Exception {
		return SysUtil.getAppServerTime(ctx);
	}
	/**
	 * ͨ��SQL���Ի�KSQL�����ײ�ѯ���ݣ�����ֵ������
	 * @param ctx
	 * @param sql
	 * @param coreBaseInfo
	 * @return
	 */
	public static CoreBaseInfo getInfo(Context ctx, String sql, CoreBaseInfo coreBaseInfo) {
		//��ʵ��
		return null;
	}
	
	public static IRowSet executeQuery(Context ctx, String sql) throws Exception {
		IRowSet rs = null;
		if (ctx != null)
		 rs = DbUtil.executeQuery(ctx,sql);
		else {
			ISQLExecutor sqlexec = new SQLExecutor(sql);
			rs = sqlexec.executeSQL();
		}
		return rs;
	}
}
