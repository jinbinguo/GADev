package com.kingdee.eas.scm.im.inv.client;

import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;

public class MaterialReqBillEditUICTEx extends MaterialReqBillEditUI {

	public MaterialReqBillEditUICTEx() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

    public SelectorItemCollection getSelectors()
    {
    	SelectorItemCollection sic = super.getSelectors();
        sic.add(new SelectorItemInfo("entry.isAdjust"));
        sic.add(new SelectorItemInfo("entry.adjustNum"));
		return sic;
    }
}
