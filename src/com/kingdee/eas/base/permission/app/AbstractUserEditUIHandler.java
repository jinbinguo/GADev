/**
 * output package name
 */
package com.kingdee.eas.base.permission.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractUserEditUIHandler extends com.kingdee.eas.framework.app.CoreUIHandler

{
	public void handleActionAddNew(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionAddNew(request,response,context);
	}
	protected void _handleActionAddNew(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionSubmit(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSubmit(request,response,context);
	}
	protected void _handleActionSubmit(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionPassword(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionPassword(request,response,context);
	}
	protected void _handleActionPassword(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}