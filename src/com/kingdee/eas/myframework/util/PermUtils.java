package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.Context;

public class PermUtils implements Serializable {
	
	/**
	 * 屏蔽服务端操作权限
	 * @param ctx
	 */
	public static void disablePermissionForKScript(Context ctx) {
		ctx.put("disablePermissionForKScript",true);
	}
	
}
