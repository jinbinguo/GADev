package com.kingdee.eas.scm.im.inv.client;

import java.awt.event.ActionEvent;

import com.kingdee.eas.framework.batchaction.BatchActionEnum;
import com.kingdee.eas.framework.batchaction.BatchSelectionEntries;
import com.kingdee.eas.myframework.util.PublicUtils;

public class OtherInWarehsBillEditUIPIEx extends OtherInWarehsBillEditUI {

	public OtherInWarehsBillEditUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		actionSave.setVisible(false);
	}
	
	@Override
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		super.actionSubmit_actionPerformed(e);
		getUIWindow().close();
	}

}
