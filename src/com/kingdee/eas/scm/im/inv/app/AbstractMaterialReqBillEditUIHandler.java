/**
 * output package name
 */
package com.kingdee.eas.scm.im.inv.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractMaterialReqBillEditUIHandler extends com.kingdee.eas.scm.im.inv.app.InvBillEditUIHandler

{
	public void handleActionOffSet(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionOffSet(request,response,context);
	}
	protected void _handleActionOffSet(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionQuickAddLine(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionQuickAddLine(request,response,context);
	}
	protected void _handleActionQuickAddLine(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionCalculateLot(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionCalculateLot(request,response,context);
	}
	protected void _handleActionCalculateLot(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionCostObjectSuite(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionCostObjectSuite(request,response,context);
	}
	protected void _handleActionCostObjectSuite(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionCalculateDynQty(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionCalculateDynQty(request,response,context);
	}
	protected void _handleActionCalculateDynQty(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}