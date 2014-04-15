package com.kingdee.eas.auto4s.bdm.rsm.client;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.common.client.SysContext;

public class RepairItemListUICTEx extends RepairItemListUIPIEx {

	public RepairItemListUICTEx() throws Exception {
		super();
	}

	@Override
	protected FilterInfo getDefaultFilterForQuery() {
    	FilterInfo filterInfo =  super.getDefaultFilterForQuery();
    	if (filterInfo == null) filterInfo = new FilterInfo();
    	AdminOrgUnitInfo adminOrgUnitInfo = SysContext.getSysContext().getCurrentAdminUnit();
    	
    	FilterInfo otherFilterInfo = new FilterInfo();
    	otherFilterInfo.getFilterItems().add(new FilterItemInfo("orgUnit.id",adminOrgUnitInfo.getString("id")));
    	try {
			filterInfo.mergeFilter(otherFilterInfo, "AND");
		} catch (BOSException e) {
			e.printStackTrace();
		}
    	return filterInfo;
	}
}
