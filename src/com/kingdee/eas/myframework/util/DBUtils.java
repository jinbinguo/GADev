package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.util.Date;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.util.SysUtil;

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
}
