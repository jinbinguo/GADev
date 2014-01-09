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
import com.kingdee.eas.train.PurRequestCollection;
import com.kingdee.eas.train.PurRequestEntryCollection;
import com.kingdee.eas.train.PurRequestEntryInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.train.PurRequestInfo;
import com.kingdee.eas.framework.SystemEnum;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.app.CoreBillBaseControllerBean;
import com.kingdee.eas.framework.ObjectBaseCollection;

public class PurRequestControllerBean extends AbstractPurRequestControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.train.app.PurRequestControllerBean");
    
	protected IObjectPK _save(Context ctx, IObjectValue model)
			throws BOSException, EASBizException {
		((PurRequestInfo)model).setStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
		PurRequestEntryCollection col = ((PurRequestInfo)model).getEntrys();
		for (int i = 0; i < col.size(); i++) {
			PurRequestEntryInfo entryInfo = col.get(i);
			entryInfo.setLineStatus(EntryBaseStatusEnum.TEMPORARILYSAVED);
		}
		return super._save(ctx, model);
	}

	protected IObjectPK _submit(Context ctx, IObjectValue model)
			throws BOSException, EASBizException {
		((PurRequestInfo)model).setStatus(BillBaseStatusEnum.SUBMITED);
		PurRequestEntryCollection col = ((PurRequestInfo)model).getEntrys();
		for (int i = 0; i < col.size(); i++) {
			PurRequestEntryInfo entryInfo = col.get(i);
			entryInfo.setLineStatus(EntryBaseStatusEnum.SUBMITED);

		}
		return super._submit(ctx, model);
	}
	protected void _audit(Context ctx, IObjectValue model) throws BOSException,EASBizException {
		IObjectPK pk = new ObjectUuidPK((BOSUuid)model.get("id"));
		((PurRequestInfo)model).setStatus(BillBaseStatusEnum.AUDITED);
		
		PurRequestEntryCollection col = ((PurRequestInfo)model).getEntrys();
		for (int i = 0; i < col.size(); i++) {
			PurRequestEntryInfo entryInfo = col.get(i);
			entryInfo.setLineStatus(EntryBaseStatusEnum.AUDITED);
			entryInfo.setUnOrderedQty(entryInfo.getRequestQty());
		}
		_update(ctx, pk, model);
		
	}
	protected void _unAudit(Context ctx, IObjectValue model)
			throws BOSException,EASBizException {
		IObjectPK pk = new ObjectUuidPK((BOSUuid)model.get("id"));
		((PurRequestInfo)model).setStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
		
		PurRequestEntryCollection col = ((PurRequestInfo)model).getEntrys();
		for (int i = 0; i < col.size(); i++) {
			PurRequestEntryInfo entryInfo = col.get(i);
			entryInfo.setLineStatus(EntryBaseStatusEnum.TEMPORARILYSAVED);
		}
		_update(ctx, pk, model);
	}
    
}