package com.kingdee.eas.scm.cal.app;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.scm.cal.CostAdjustBillFactory;
import com.kingdee.eas.scm.cal.CostAdjustBillInfo;
import com.kingdee.eas.scm.im.inv.MaterialReqBillFactory;
import com.kingdee.eas.scm.im.inv.MaterialReqBillInfo;
import com.kingdee.eas.util.app.DbUtil;

public class CostAdjustBillControllerBeanEx extends
		CostAdjustBillControllerBean {
	protected void _delete(Context ctx, IObjectPK pk) throws BOSException,
			EASBizException {
		CostAdjustBillInfo costAdjustBillInfo = CostAdjustBillFactory.getLocalInstance(ctx)
		.getCostAdjustBillInfo(pk);
		String costAdjustBillId= costAdjustBillInfo.getNumber();
		super._delete(ctx, pk);
		String sql = "update T_IM_MaterialReqBillEntry set CFAdjustNum= '',CFIsAdjust=0 " +
				"where CFAdjustNum=" +"'"+costAdjustBillId+"'";
		DbUtil.execute(ctx, sql);
//		MaterialReqBillInfo materialReqBillInfo = MaterialReqBillFactory
//				.getLocalInstance(ctx).getMaterialReqBillInfo(pk);
//		materialReqBillInfo.setIsAdjust(false);// 核销成本调整
//		materialReqBillInfo.setAdjustNum(null);
//		MaterialReqBillFactory.getLocalInstance(ctx).update(
//				new ObjectUuidPK(materialReqBillInfo.getId()),
//				materialReqBillInfo);
	}
}
