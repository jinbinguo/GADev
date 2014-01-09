package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.workflow.ProcessDefInfo;
import com.kingdee.bos.workflow.define.ProcessDef;
import com.kingdee.bos.workflow.service.ormrpc.EnactmentServiceFactory;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.framework.client.workflow.EASWfServiceClient;

public class WfrUtils implements Serializable {
	
	/**
	 * 判断是否从工作流中打开单据的
	 * @param ui
	 * @return
	 */
	public static boolean isFromWF(CoreUIObject ui) {
		Boolean isFromWF = (Boolean) ui.getUIContext().get("isFromWorkflow");
		if (isFromWF == null)
			isFromWF = Boolean.FALSE;
		return isFromWF.booleanValue();
	}
	
	/**
	 * 显示默认的工作流
	 * @param ui
	 * @param procDefId
	 * @throws Exception
	 */
	public static void showWorkFlowDef(CoreUIObject ui, String procDefId)
			throws Exception {
		EASWfServiceClient ormClient = new EASWfServiceClient();
		ProcessDefInfo procDefInfo = ormClient.getProcessDefInfo(procDefId);
		java.util.Locale currentLocale = SysContext.getSysContext().getLocale();
		if (procDefInfo == null)
			return;
		ProcessDef processDef = EnactmentServiceFactory.createRemoteEnactService().getProcessDefByDefineHashValue(procDefInfo.getMd5HashValue());
		if (processDef == null) {
			return;
		} else {
			String procDefDiagramTitle = processDef.getName(currentLocale);
			UIContext uiContext = new UIContext(ui);
			uiContext.put("define", processDef);
			uiContext.put("title", procDefDiagramTitle);
			String className = (com.kingdee.bos.workflow.monitor.client.BasicShowWfDefinePanel.class)
					.getName();
			IUIFactory uiFactory = UIFactory
					.createUIFactory(UIFactoryName.MODEL);
			IUIWindow uiWindow = uiFactory.create(className, uiContext);
			uiWindow.show();
			return;
		}
	}

}
