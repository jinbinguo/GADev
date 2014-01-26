package com.kingdee.eas.ga.syncdata.app;

import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
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
import com.kingdee.eas.framework.Result;
import com.kingdee.eas.framework.LineResult;
import com.kingdee.eas.framework.exception.EASMultiException;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;

import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.myframework.vo.ServerReturnInfo;



public abstract class AbstractSyncDataFacadeControllerBean extends AbstractBizControllerBean implements SyncDataFacadeController
{
    protected AbstractSyncDataFacadeControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("76F81EA6");
    }

    public ServerReturnInfo syncWipBill(Context ctx, ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsWipBillPk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a1c9d6de-771a-4b8b-8bc4-5b641a8984b5"), new Object[]{ctx, serviceOrgInfo, dmsWipBillPk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            ServerReturnInfo retValue = (ServerReturnInfo)_syncWipBill(ctx, serviceOrgInfo, dmsWipBillPk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (ServerReturnInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected ServerReturnInfo _syncWipBill(Context ctx, IObjectValue serviceOrgInfo, IObjectPK dmsWipBillPk) throws BOSException, EASBizException
    {    	
        return null;
    }

    public ServerReturnInfo syncTradeInquire(Context ctx, ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsTradeInqirePk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("558e94bd-2581-4c60-9b6f-d4e93d3c4789"), new Object[]{ctx, serviceOrgInfo, dmsTradeInqirePk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            ServerReturnInfo retValue = (ServerReturnInfo)_syncTradeInquire(ctx, serviceOrgInfo, dmsTradeInqirePk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (ServerReturnInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected ServerReturnInfo _syncTradeInquire(Context ctx, IObjectValue serviceOrgInfo, IObjectPK dmsTradeInqirePk) throws BOSException, EASBizException
    {    	
        return null;
    }

    public void autosync(Context ctx) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("8f51a682-c58e-4605-8319-5b3816ef884a"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _autosync(ctx);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _autosync(Context ctx) throws BOSException, EASBizException
    {    	
        return;
    }

}