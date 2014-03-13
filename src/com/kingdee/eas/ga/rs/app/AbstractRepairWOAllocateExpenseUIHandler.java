/**
 * output package name
 */
package com.kingdee.eas.ga.rs.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractRepairWOAllocateExpenseUIHandler extends com.kingdee.eas.framework.app.CoreUIObjectHandler

{
	public void handleActionOK(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionOK(request,response,context);
	}
	protected void _handleActionOK(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionCancel(request,response,context);
	}
	protected void _handleactionCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}