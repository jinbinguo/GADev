package com.kingdee.eas.train.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.eas.train.PurOrderCollection;
import com.kingdee.eas.train.PurOrderEntryCollection;
import com.kingdee.eas.train.PurOrderEntryInfo;
import com.kingdee.eas.train.PurOrderInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.SystemEnum;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.train.PurOrderInfo;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.app.CoreBillBaseControllerBean;
import com.kingdee.eas.framework.ObjectBaseCollection;

public class PurOrderControllerBean extends AbstractPurOrderControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.train.app.PurOrderControllerBean");
    
    
	protected IObjectPK _save(Context ctx, IObjectValue model)
			throws BOSException, EASBizException {
		((PurOrderInfo)model).setStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
		PurOrderEntryCollection col = ((PurOrderInfo)model).getEntrys();
		return super._save(ctx, model);
	}

	protected IObjectPK _submit(Context ctx, IObjectValue model)
			throws BOSException, EASBizException {
		((PurOrderInfo)model).setStatus(BillBaseStatusEnum.SUBMITED);
		PurOrderEntryCollection col = ((PurOrderInfo)model).getEntrys();
		
		return super._submit(ctx, model);
	}
	protected void _audit(Context ctx, IObjectValue model) throws BOSException,EASBizException {
		IObjectPK pk = new ObjectUuidPK((BOSUuid)model.get("id"));
		((PurOrderInfo)model).setStatus(BillBaseStatusEnum.AUDITED);
		_update(ctx, pk, model);
		
		writebackPurRequest(ctx, pk);
		
	}
	protected void _unAudit(Context ctx, IObjectValue model)
			throws BOSException,EASBizException {
		IObjectPK pk = new ObjectUuidPK((BOSUuid)model.get("id"));
		((PurOrderInfo)model).setStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
		_update(ctx, pk, model);
		
		writebackPurRequest(ctx, pk);
	}
	
	public void writebackPurRequest(Context ctx, IObjectPK pk) throws BOSException,EASBizException {
		StringBuilder sql = new StringBuilder();
		sql.append("update CT_TRA_PurRequestEntry aa ")
		.append("set CFOrderedQty=(select isnull(sum(a.cfqty),0) from CT_TRA_PurOrderEntry a ")
		.append("left join CT_TRA_PurOrder b on a.FParentID=b.FID ")
		.append("where a.CFSourceBillEntryId=aa.fid and b.CFStatus>=4), ")
		.append("CFUnOrderedQty=CFRequestQty-(select isnull(sum(a.cfqty),0) from CT_TRA_PurOrderEntry a ")
		.append("left join CT_TRA_PurOrder b on a.FParentID=b.FID ")
		.append("where a.CFSourceBillEntryId=aa.fid and b.CFStatus>=4) ")
		
		.append("where exists (select 1 from CT_TRA_PurOrderEntry a where a.CFSourceBillEntryId=aa.FID ")
		.append("and a.FParentID='").append(pk.toString()).append("')");
		
		DbUtil.execute(ctx, sql.toString());
		
	}
}