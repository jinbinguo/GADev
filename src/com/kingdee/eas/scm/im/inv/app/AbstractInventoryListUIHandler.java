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
public abstract class AbstractInventoryListUIHandler extends com.kingdee.eas.framework.app.ListUIHandler

{
	public void handleActionRefreshByMat(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionRefreshByMat(request,response,context);
	}
	protected void _handleActionRefreshByMat(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionRefreshByOrg(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionRefreshByOrg(request,response,context);
	}
	protected void _handleActionRefreshByOrg(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionShowRefreshInv(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionShowRefreshInv(request,response,context);
	}
	protected void _handleActionShowRefreshInv(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionAdjustInwarehs(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionAdjustInwarehs(request,response,context);
	}
	protected void _handleactionAdjustInwarehs(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionAdjustOutwarehs(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionAdjustOutwarehs(request,response,context);
	}
	protected void _handleactionAdjustOutwarehs(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}