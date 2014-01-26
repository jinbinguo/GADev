package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.util.Date;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.util.SysUtil;

public class DBUtils implements Serializable {
	/**
	 * 获取服务器时间
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static Date getAppServerTime(Context ctx) throws Exception {
		return SysUtil.getAppServerTime(ctx);
	}
	/**
	 * 通过SQL方言或KSQL语句简易查询数据，并付值给对象
	 * @param ctx
	 * @param sql
	 * @param coreBaseInfo
	 * @return
	 */
	public static CoreBaseInfo getInfo(Context ctx, String sql, CoreBaseInfo coreBaseInfo) {
		//待实现
		return null;
	}
}
