package com.kingdee.eas.auto4s.rsm.rs.client;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.ExceptionHandler;

public class VehicleRSMPromptBoxEx extends VehicleRSMPromptBox {

	public void show() {

		IUIFactory uiFactory = null;

		try {
			if (ctx != null)
				ctx.put("F7_EVI", getEntityViewInfo());

			uiFactory = UIFactory.createUIFactory(UIFactoryName.MODEL);
			vehicleUI = uiFactory.create(RsmVehicleF7UI.class.getName(), ctx);
			if (!((RsmVehicleF7UIPIEx)vehicleUI.getUIObject()).isNotNeedShow())
				vehicleUI.show();
		} catch (BOSException ex) {
			ExceptionHandler.handle(ex);
			SysUtil.abort();
		}
	}
}
