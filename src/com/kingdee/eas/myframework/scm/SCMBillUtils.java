package com.kingdee.eas.myframework.scm;

import java.io.Serializable;

import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.scm.common.ISCMBillParam;
import com.kingdee.eas.scm.common.SCMBillParamFactory;

public class SCMBillUtils implements Serializable {

	/**
	 * 是否提交即审核
	 * @param ctx
	 * @param orgUnitId
	 * @param billTypeId 单据类型ID
	 * @return
	 * @throws Exception
	 */
	public static boolean isSubmitAutoAudit(Context ctx,String orgUnitId,String billTypeId) throws Exception {
		ISCMBillParam iSCMBillParam;
		boolean isAutoAudit = false;
		if (ctx == null) iSCMBillParam = SCMBillParamFactory.getRemoteInstance();
		else iSCMBillParam = SCMBillParamFactory.getLocalInstance(ctx);
		String strAutoAudit = iSCMBillParam.getParamByBillTypeID("SCM_Submit_01", 4, orgUnitId, billTypeId);
		if(!PublicUtils.isEmpty(strAutoAudit) && "1".equals(strAutoAudit.trim()))
	          isAutoAudit = true;
		return isAutoAudit;
	}
	
	
	/**
	 * 更新库存
	 * 
	 *  IInventoryUpdate iInventoryUpdate = InventoryUpdateFactory.getLocalInstance(ctx);

String transTypeID = null;
 if(aInvBillBaseInfo.getTransactionType() != null)
            {       transTypeID = aInvBillBaseInfo.getTransactionType().getId().toString();
     iInventoryUpdate.updateInventory(pk.toString(), transTypeID);
            }
            
	 */

}
