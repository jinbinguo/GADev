package com.kingdee.eas.auto4s.bdm.pbd.client;

import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerTypeEnum;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupFactory;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupInfo;
import com.kingdee.eas.basedata.master.cssp.ICSSPGroup;
import com.kingdee.eas.basedata.master.cssp.ICustomerGroupDetail;
import com.kingdee.eas.common.client.SysContext;

public class CustomerEditUIPIEx extends CustomerEditUI {

	private ICSSPGroup csspGroup = null; 
	public CustomerEditUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		if("ADDNEW".equals(oprtState)) {
			 try {
				csspGroup = CSSPGroupFactory.getRemoteInstance();
				String orgNumber = SysContext.getSysContext().getCurrentOrgUnit().getNumber();
				CSSPGroupInfo csspGroupInfo =  csspGroup.getCSSPGroupInfo(String.format("where number='%s'", orgNumber));
				prmtStdCustomerGroup.setValue(csspGroupInfo);
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		} 
	}

	
}
