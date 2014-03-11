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
public abstract class AbstractRepairManListUIHandler extends com.kingdee.eas.myframework.template.base.app.SimpleBizBillListUIHandler

{
	public void handleActionSearch(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionSearch(request,response,context);
	}
	protected void _handleActionSearch(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}