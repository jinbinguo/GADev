package com.kingdee.eas.basedata.master.cssp.client;

import com.kingdee.bos.dao.IObjectValue;

public class SupplierBaseUIPIEx extends SupplierBaseUI {

	public SupplierBaseUIPIEx() throws Exception {
		super();
	}
	
	@Override
	protected IObjectValue createNewData() {
		IObjectValue value = super.createNewData();
		value.put("taxRate",17.00);
		return value;
	}
	
	

}
