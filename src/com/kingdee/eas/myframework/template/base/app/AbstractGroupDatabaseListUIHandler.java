/**
 * output package name
 */
package com.kingdee.eas.myframework.template.base.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractGroupDatabaseListUIHandler extends com.kingdee.eas.framework.app.TreeDetailListUIHandler

{
	public void handleActionGroupCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionGroupCancel(request,response,context);
	}
	protected void _handleActionGroupCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionGroupCancelCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionGroupCancelCancel(request,response,context);
	}
	protected void _handleActionGroupCancelCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}