/**
 * output package name
 */
package com.kingdee.eas.myframework.common.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractMultiColumnSortUIHandler extends com.kingdee.eas.framework.app.CoreUIObjectHandler

{
	public void handleActionMoveTop(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionMoveTop(request,response,context);
	}
	protected void _handleActionMoveTop(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionMoveUp(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionMoveUp(request,response,context);
	}
	protected void _handleActionMoveUp(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionMoveDown(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionMoveDown(request,response,context);
	}
	protected void _handleActionMoveDown(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionMoveBottom(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionMoveBottom(request,response,context);
	}
	protected void _handleActionMoveBottom(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionOK(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionOK(request,response,context);
	}
	protected void _handleActionOK(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionCancel(request,response,context);
	}
	protected void _handleActionCancel(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionReset(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionReset(request,response,context);
	}
	protected void _handleActionReset(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionSearchField(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSearchField(request,response,context);
	}
	protected void _handleActionSearchField(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}