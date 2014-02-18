package com.kingdee.eas.auto4s.arp.aar.app;

import java.util.HashMap;
import java.util.Map;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.common.EASBizException;

public class ReceivingBillControllerBeanEx extends ReceivingBillControllerBean {
	@Override
	protected Map _getRSEntryCollection(Context ctx, Map map)
			throws BOSException, EASBizException {
		return new HashMap(); 
	}
}
