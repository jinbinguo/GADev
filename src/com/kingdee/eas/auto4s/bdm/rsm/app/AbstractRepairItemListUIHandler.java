/**
 * output package name
 */
package com.kingdee.eas.auto4s.bdm.rsm.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractRepairItemListUIHandler extends com.kingdee.eas.framework.app.TreeDetailListUIHandler

{
	public void handleactionExportExcelTemplate(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionExportExcelTemplate(request,response,context);
	}
	protected void _handleactionExportExcelTemplate(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
	public void handleactionImportExcel(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleactionImportExcel(request,response,context);
	}
	protected void _handleactionImportExcel(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}