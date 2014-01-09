package com.kingdee.eas.scm.cal.app;

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

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.scm.cal.CostAdjustBillInfo;
import com.kingdee.eas.scm.im.inv.app.InvBillBaseControllerBean;
import com.kingdee.eas.scm.common.SCMBillBaseCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.scm.cal.CostAdjustBillCollection;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.scm.im.inv.InvBillBaseCollection;



public abstract class AbstractCostAdjustBillControllerBean extends InvBillBaseControllerBean implements CostAdjustBillController
{
    protected AbstractCostAdjustBillControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("8FA62986");
    }

    public CostAdjustBillInfo getCostAdjustBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("cc9871bd-0105-1000-e000-001ac0a8121e"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            CostAdjustBillInfo retValue = (CostAdjustBillInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (CostAdjustBillInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        return super._getValue(ctx, pk);
    }

    public CostAdjustBillInfo getCostAdjustBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("cc9871bd-0105-1000-e000-001bc0a8121e"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            CostAdjustBillInfo retValue = (CostAdjustBillInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (CostAdjustBillInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        return super._getValue(ctx, pk, selector);
    }

    public CostAdjustBillInfo getCostAdjustBillInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("cc9871bd-0105-1000-e000-001cc0a8121e"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            CostAdjustBillInfo retValue = (CostAdjustBillInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (CostAdjustBillInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, String oql) throws BOSException, EASBizException
    {
        return super._getValue(ctx, oql);
    }

    public CostAdjustBillCollection getCostAdjustBillCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("cc9871bd-0105-1000-e000-001dc0a8121e"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            CostAdjustBillCollection retValue = (CostAdjustBillCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (CostAdjustBillCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx) throws BOSException
    {
        return super._getCollection(ctx, svcCtx);
    }

    public CostAdjustBillCollection getCostAdjustBillCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("cc9871bd-0105-1000-e000-001ec0a8121e"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            CostAdjustBillCollection retValue = (CostAdjustBillCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (CostAdjustBillCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx, EntityViewInfo view) throws BOSException
    {
        return super._getCollection(ctx, svcCtx, view);
    }

    public CostAdjustBillCollection getCostAdjustBillCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("cc9871bd-0105-1000-e000-001fc0a8121e"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            CostAdjustBillCollection retValue = (CostAdjustBillCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (CostAdjustBillCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx, String oql) throws BOSException
    {
        return super._getCollection(ctx, svcCtx, oql);
    }

    public boolean IsJoinWithOtherBill(Context ctx, String[] billIDs) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7acf259d-dd11-4e0d-b944-68c566f3c393"), new Object[]{ctx, billIDs});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_IsJoinWithOtherBill(ctx, billIDs);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _IsJoinWithOtherBill(Context ctx, String[] billIDs) throws BOSException;

    public InvBillBaseCollection getInvBillBaseCollection (Context ctx) throws BOSException
    {
    	return (InvBillBaseCollection)(getCostAdjustBillCollection(ctx).cast(InvBillBaseCollection.class));
    }
    public InvBillBaseCollection getInvBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (InvBillBaseCollection)(getCostAdjustBillCollection(ctx, view).cast(InvBillBaseCollection.class));
    }
    public InvBillBaseCollection getInvBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (InvBillBaseCollection)(getCostAdjustBillCollection(ctx, oql).cast(InvBillBaseCollection.class));
    }
    public SCMBillBaseCollection getSCMBillBaseCollection (Context ctx) throws BOSException
    {
    	return (SCMBillBaseCollection)(getCostAdjustBillCollection(ctx).cast(SCMBillBaseCollection.class));
    }
    public SCMBillBaseCollection getSCMBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (SCMBillBaseCollection)(getCostAdjustBillCollection(ctx, view).cast(SCMBillBaseCollection.class));
    }
    public SCMBillBaseCollection getSCMBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (SCMBillBaseCollection)(getCostAdjustBillCollection(ctx, oql).cast(SCMBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBillBaseCollection)(getCostAdjustBillCollection(ctx).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBillBaseCollection)(getCostAdjustBillCollection(ctx, view).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBillBaseCollection)(getCostAdjustBillCollection(ctx, oql).cast(CoreBillBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx) throws BOSException
    {
    	return (ObjectBaseCollection)(getCostAdjustBillCollection(ctx).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (ObjectBaseCollection)(getCostAdjustBillCollection(ctx, view).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (ObjectBaseCollection)(getCostAdjustBillCollection(ctx, oql).cast(ObjectBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getCostAdjustBillCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getCostAdjustBillCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getCostAdjustBillCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}