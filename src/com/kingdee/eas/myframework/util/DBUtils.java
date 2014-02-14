package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.util.Date;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.myframework.common.ISQLExecutor;
import com.kingdee.eas.myframework.common.SQLExecutorFactory;
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
	
	public static IRowSet executeQueryForDialect(Context ctx, String sql) throws Exception {
		return executeQuery(ctx,"/*dialect*/"+sql);

	}
	public static IRowSet executeQuery(Context ctx, String sql) throws Exception {
		IRowSet rs = null;
		ISQLExecutor sqlexec = null;
		if (ctx == null) sqlexec = SQLExecutorFactory.getRemoteInstance();
		else sqlexec = SQLExecutorFactory.getLocalInstance(ctx);
		rs = sqlexec.executeQuery(sql);
		return rs;
	}
	
	public static void execute(Context ctx, String sql) throws Exception {
		ISQLExecutor sqlexec = null;
		if (ctx == null) sqlexec = SQLExecutorFactory.getRemoteInstance();
		else sqlexec = SQLExecutorFactory.getLocalInstance(ctx);
		sqlexec.execute(sql);
	}
}
