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
public abstract class AbstractMaterialReqBillListUIHandler extends com.kingdee.eas.scm.im.inv.app.InvBillListUIHandler

{
	public void handleActionGetMaterial(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionGetMaterial(request,response,context);
	}
	protected void _handleActionGetMaterial(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionCancelMaterial(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionCancelMaterial(request,response,context);
	}
	protected void _handleActionCancelMaterial(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handlekDelObj1(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handlekDelObj1(request,response,context);
	}
	protected void _handlekDelObj1(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleRelObj1(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleRelObj1(request,response,context);
	}
	protected void _handleRelObj1(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}