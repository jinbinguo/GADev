package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.base.permission.IPermission;
import com.kingdee.eas.base.permission.PermissionFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.OrgUnitInfo;

public class PermUtils implements Serializable {
	
	/**
	 * ���η���˲���Ȩ��
	 * @param ctx
	 */
	public static void disablePermissionForKScript(Context ctx) {
		ctx.put("disablePermissionForKScript",true);
	}
	/**
	 * �Ƿ��й���Ȩ��
	 * @param ctx
	 * @param userInfo
	 * @param orgUnitInfo
	 * @param permItemName
	 * @return
	 * @throws Exception
	 */
	public static boolean hasFunctionPermission(Context ctx,UserInfo userInfo, OrgUnitInfo orgUnitInfo, String permItemName) throws Exception {
		if (userInfo == null || PublicUtils.isEmpty(userInfo.getString("id"))) {
			throw new Exception("PermUtils.hasFunctionPermission����userInfo����Ϊ��");
		}
		if (orgUnitInfo == null || PublicUtils.isEmpty(orgUnitInfo.getString("id"))) {
			throw new Exception("PermUtils.hasFunctionPermission����orgUnitInfo����Ϊ��");
		}
		String userId = userInfo.getString("id");
		String orgId = orgUnitInfo.getString("id");
		return hasFunctionPermission(ctx, userId, orgId, permItemName);

	}
	
	/**
	 * �Ƿ��й���Ȩ��
	 * @param ctx
	 * @param userId
	 * @param orgId
	 * @param permItemName Ȩ������
	 * @return
	 * @throws Exception
	 */
	public static boolean hasFunctionPermission(Context ctx,String userId, String orgId, String permItemName) throws Exception {
		if (PublicUtils.isEmpty(userId)) {
			throw new Exception("PermUtils.hasFunctionPermission����userId����Ϊ��");
		}
		if (PublicUtils.isEmpty(orgId)) {
			throw new Exception("PermUtils.hasFunctionPermission����orgId����Ϊ��");
		}
		if (PublicUtils.isEmpty(permItemName)) {
			throw new Exception("PermUtils.hasFunctionPermission����permItemName����Ϊ��");
		}
		IObjectPK userPK = new ObjectUuidPK(userId);
		IObjectPK orgPK = new ObjectUuidPK(orgId);
		IPermission permission = null;
		if (ctx == null) permission = PermissionFactory.getRemoteInstance();
		else permission = PermissionFactory.getLocalInstance(ctx);
		
		return permission.hasFunctionPermission(userPK, orgPK, permItemName);
	}
	
}
