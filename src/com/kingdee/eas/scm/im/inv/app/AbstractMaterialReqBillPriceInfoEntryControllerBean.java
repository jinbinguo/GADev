package com.kingdee.eas.scm.im.inv.app;

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
import com.kingdee.eas.scm.common.SCMBillEntryBaseCollection;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.scm.common.app.SCMBillEntryBaseControllerBean;
import com.kingdee.eas.framework.CoreBillEntryBaseCollection;
import com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection;



public abstract class AbstractMaterialReqBillPriceInfoEntryControllerBean extends SCMBillEntryBaseControllerBean implements MaterialReqBillPriceInfoEntryController
{
    protected AbstractMaterialReqBillPriceInfoEntryControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("97A53E9B");
    }

    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("3b77352d-040e-4936-9cb5-3d89443d8b5c"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillPriceInfoEntryInfo retValue = (MaterialReqBillPriceInfoEntryInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillPriceInfoEntryInfo)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("3b1aabe4-d7ea-492a-bbff-c3845e9df5e3"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillPriceInfoEntryInfo retValue = (MaterialReqBillPriceInfoEntryInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillPriceInfoEntryInfo)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("037dbeeb-2baa-4b75-9328-cb2d70f3e5c2"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillPriceInfoEntryInfo retValue = (MaterialReqBillPriceInfoEntryInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillPriceInfoEntryInfo)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("5bed94fc-b3b9-44b9-80dd-444b02f6ca4c"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillPriceInfoEntryCollection retValue = (MaterialReqBillPriceInfoEntryCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillPriceInfoEntryCollection)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("71580376-1209-4672-a582-d03d1d4bfe5a"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillPriceInfoEntryCollection retValue = (MaterialReqBillPriceInfoEntryCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillPriceInfoEntryCollection)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a0f604eb-87c0-443f-afa8-ff96d3d67fcf"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillPriceInfoEntryCollection retValue = (MaterialReqBillPriceInfoEntryCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillPriceInfoEntryCollection)svcCtx.getMethodReturnValue();
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

    public SCMBillEntryBaseCollection getSCMBillEntryBaseCollection (Context ctx) throws BOSException
    {
    	return (SCMBillEntryBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx).cast(SCMBillEntryBaseCollection.class));
    }
    public SCMBillEntryBaseCollection getSCMBillEntryBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (SCMBillEntryBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx, view).cast(SCMBillEntryBaseCollection.class));
    }
    public SCMBillEntryBaseCollection getSCMBillEntryBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (SCMBillEntryBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx, oql).cast(SCMBillEntryBaseCollection.class));
    }
    public CoreBillEntryBaseCollection getCoreBillEntryBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBillEntryBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx).cast(CoreBillEntryBaseCollection.class));
    }
    public CoreBillEntryBaseCollection getCoreBillEntryBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBillEntryBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx, view).cast(CoreBillEntryBaseCollection.class));
    }
    public CoreBillEntryBaseCollection getCoreBillEntryBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBillEntryBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx, oql).cast(CoreBillEntryBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getMaterialReqBillPriceInfoEntryCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}