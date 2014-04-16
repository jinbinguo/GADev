package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectStringPK;
import com.kingdee.eas.base.param.util.ParamManager;

public class ParamUtils implements Serializable{
	
	public static String getParamValue(Context ctx, String paramName, String orgId) throws Exception {
		String paramValue = ParamManager.getParamValueIgnoreGroupControl(null, paramName, new ObjectStringPK(orgId), true);
		if (paramValue == null) return "";
		return paramValue;
	}
	
	public static boolean getParamValueForBoolean(Context ctx, String paramName, String orgId) throws Exception {
		String paramValue = getParamValue(ctx, paramName, orgId);
		if (PublicUtils.isEmpty(paramValue)) return false;
		return PublicUtils.equals("true", paramValue.toLowerCase());
	}
}
