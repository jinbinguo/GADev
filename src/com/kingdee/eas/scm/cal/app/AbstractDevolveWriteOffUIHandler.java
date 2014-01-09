/**
 * output package name
 */
package com.kingdee.eas.scm.cal.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractDevolveWriteOffUIHandler extends com.kingdee.eas.scm.common.app.ManulWriteoffCommonUIHandler

{
	public void handleActionAllSelected(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionAllSelected(request,response,context);
	}
	protected void _handleActionAllSelected(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionNoSelected(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionNoSelected(request,response,context);
	}
	protected void _handleActionNoSelected(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionWriteOffView(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionWriteOffView(request,response,context);
	}
	protected void _handleActionWriteOffView(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionWriteOff(request,response,context);
	}
	protected void _handleActionWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionWriteOffQuery(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionWriteOffQuery(request,response,context);
	}
	protected void _handleActionWriteOffQuery(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionBill(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionBill(request,response,context);
	}
	protected void _handleActionBill(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionMaterialTotal(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionMaterialTotal(request,response,context);
	}
	protected void _handleActionMaterialTotal(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionMultiBillWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionMultiBillWriteOff(request,response,context);
	}
	protected void _handleActionMultiBillWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}