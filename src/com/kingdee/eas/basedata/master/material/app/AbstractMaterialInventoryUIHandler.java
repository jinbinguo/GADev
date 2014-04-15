/**
 * output package name
 */
package com.kingdee.eas.basedata.master.material.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractMaterialInventoryUIHandler extends com.kingdee.eas.framework.app.EditUIHandler

{
	public void handleActionApprove(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionApprove(request,response,context);
	}
	protected void _handleActionApprove(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionUnApprove(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionUnApprove(request,response,context);
	}
	protected void _handleActionUnApprove(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}