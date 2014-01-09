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
public abstract class AbstractDevolveWriteOffReportUIHandler extends com.kingdee.eas.framework.app.CoreBillListUIHandler

{
	public void handleActionWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionWriteOff(request,response,context);
	}
	protected void _handleActionWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionInverseWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionInverseWriteOff(request,response,context);
	}
	protected void _handleActionInverseWriteOff(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleActionIsShowSumRow(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionIsShowSumRow(request,response,context);
	}
	protected void _handleActionIsShowSumRow(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}