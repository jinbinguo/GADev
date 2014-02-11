package com.kingdee.eas.ga.rs.app;

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
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.ga.rs.WInfo;
import com.kingdee.eas.myframework.template.base.SimpleDatabaseCollection;
import com.kingdee.eas.myframework.template.base.app.SimpleDatabaseControllerBean;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.ga.rs.WCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.framework.DataBaseCollection;



public abstract class AbstractWControllerBean extends SimpleDatabaseControllerBean implements WController
{
    protected AbstractWControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("9871DC0C");
    }

    public WInfo getWInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a0b8f5f5-92f9-4d8f-97e9-fe4039a3aff1"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            WInfo retValue = (WInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (WInfo)svcCtx.getMethodReturnValue();
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

    public WInfo getWInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("28ee79f2-d886-4c50-b817-51d1613ee460"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            WInfo retValue = (WInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (WInfo)svcCtx.getMethodReturnValue();
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

    public WInfo getWInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e615faf4-eab0-4c00-a2a1-ad4c18cd8b97"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            WInfo retValue = (WInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (WInfo)svcCtx.getMethodReturnValue();
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

    public WCollection getWCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("085a4606-5e26-43dd-97d1-0b48d7f4f4d5"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            WCollection retValue = (WCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (WCollection)svcCtx.getMethodReturnValue();
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

    public WCollection getWCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("2eb9fecb-59c4-4ef4-9783-b0a648e2089a"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            WCollection retValue = (WCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (WCollection)svcCtx.getMethodReturnValue();
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

    public WCollection getWCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("b6f84f4d-3b24-4594-bde0-4c896fdedbfe"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            WCollection retValue = (WCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (WCollection)svcCtx.getMethodReturnValue();
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

    public SimpleDatabaseCollection getSimpleDatabaseCollection (Context ctx) throws BOSException
    {
    	return (SimpleDatabaseCollection)(getWCollection(ctx).cast(SimpleDatabaseCollection.class));
    }
    public SimpleDatabaseCollection getSimpleDatabaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (SimpleDatabaseCollection)(getWCollection(ctx, view).cast(SimpleDatabaseCollection.class));
    }
    public SimpleDatabaseCollection getSimpleDatabaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (SimpleDatabaseCollection)(getWCollection(ctx, oql).cast(SimpleDatabaseCollection.class));
    }
    public DataBaseCollection getDataBaseCollection (Context ctx) throws BOSException
    {
    	return (DataBaseCollection)(getWCollection(ctx).cast(DataBaseCollection.class));
    }
    public DataBaseCollection getDataBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (DataBaseCollection)(getWCollection(ctx, view).cast(DataBaseCollection.class));
    }
    public DataBaseCollection getDataBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (DataBaseCollection)(getWCollection(ctx, oql).cast(DataBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx) throws BOSException
    {
    	return (ObjectBaseCollection)(getWCollection(ctx).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (ObjectBaseCollection)(getWCollection(ctx, view).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (ObjectBaseCollection)(getWCollection(ctx, oql).cast(ObjectBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getWCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getWCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getWCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}