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
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;
import com.kingdee.eas.scm.common.SCMBillBaseCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.scm.im.inv.MaterialReqBillInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.scm.im.inv.MaterialReqBillCollection;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.scm.im.inv.InvBillBaseCollection;
import com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection;



public abstract class AbstractMaterialReqBillControllerBean extends InvBillBaseControllerBean implements MaterialReqBillController
{
    protected AbstractMaterialReqBillControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("500AB75E");
    }

    public MaterialReqBillInfo getMaterialReqBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1a9bc47a-0106-1000-e000-002bc0a812c5"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillInfo retValue = (MaterialReqBillInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillInfo)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillInfo getMaterialReqBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1a9bc47a-0106-1000-e000-002cc0a812c5"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillInfo retValue = (MaterialReqBillInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillInfo)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillInfo getMaterialReqBillInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1a9bc47a-0106-1000-e000-002dc0a812c5"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillInfo retValue = (MaterialReqBillInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillInfo)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillCollection getMaterialReqBillCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1a9bc47a-0106-1000-e000-002ec0a812c5"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillCollection retValue = (MaterialReqBillCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillCollection)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillCollection getMaterialReqBillCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1a9bc47a-0106-1000-e000-002fc0a812c5"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillCollection retValue = (MaterialReqBillCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillCollection)svcCtx.getMethodReturnValue();
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

    public MaterialReqBillCollection getMaterialReqBillCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1a9bc47a-0106-1000-e000-0030c0a812c5"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillCollection retValue = (MaterialReqBillCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (MaterialReqBillCollection)svcCtx.getMethodReturnValue();
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

    public CostCenterOrgUnitInfo getDefaultCostCenter(Context ctx, String userID) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("6202cdb3-0116-1000-e000-0032c0a812c5"), new Object[]{ctx, userID});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            CostCenterOrgUnitInfo retValue = (CostCenterOrgUnitInfo)_getDefaultCostCenter(ctx, userID);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (CostCenterOrgUnitInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract IObjectValue _getDefaultCostCenter(Context ctx, String userID) throws BOSException, EASBizException;

    public Set getAdminOrgsHashSet(Context ctx, String userID) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("6202cdb3-0116-1000-e000-004ac0a812c5"), new Object[]{ctx, userID});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            Set retValue = (Set)_getAdminOrgsHashSet(ctx, userID);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (Set)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract Set _getAdminOrgsHashSet(Context ctx, String userID) throws BOSException, EASBizException;

    public boolean deleteable(Context ctx, String billId) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("bf708d20-0cb0-4657-bb28-0bc624d4b665"), new Object[]{ctx, billId});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_deleteable(ctx, billId);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }

    public String getLotsByMaterial(Context ctx, String storageOrgUnitID, String materialID) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("bf3a4b9f-38f2-46ac-aa83-d1834017e894"), new Object[]{ctx, storageOrgUnitID, materialID});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            String retValue = (String)_getLotsByMaterial(ctx, storageOrgUnitID, materialID);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (String)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract String _getLotsByMaterial(Context ctx, String storageOrgUnitID, String materialID) throws BOSException, EASBizException;

    public void bachLoopAduit(Context ctx, IObjectPK[] pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("685547dc-51a5-4cc4-b58a-c7c2384855a1"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _bachLoopAduit(ctx, pk);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _bachLoopAduit(Context ctx, IObjectPK[] pk) throws BOSException, EASBizException;

    public MaterialReqBillPriceInfoEntryCollection getPriceInfo(Context ctx, MaterialReqBillPriceInfoEntryCollection priceInfos, Map orgUnits) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("002655c0-7552-4da1-8e4b-e621443498ca"), new Object[]{ctx, priceInfos, orgUnits});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            MaterialReqBillPriceInfoEntryCollection retValue = (MaterialReqBillPriceInfoEntryCollection)_getPriceInfo(ctx, priceInfos, orgUnits);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (MaterialReqBillPriceInfoEntryCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract MaterialReqBillPriceInfoEntryCollection _getPriceInfo(Context ctx, MaterialReqBillPriceInfoEntryCollection priceInfos, Map orgUnits) throws BOSException, EASBizException;

    public InvBillBaseCollection getInvBillBaseCollection (Context ctx) throws BOSException
    {
    	return (InvBillBaseCollection)(getMaterialReqBillCollection(ctx).cast(InvBillBaseCollection.class));
    }
    public InvBillBaseCollection getInvBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (InvBillBaseCollection)(getMaterialReqBillCollection(ctx, view).cast(InvBillBaseCollection.class));
    }
    public InvBillBaseCollection getInvBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (InvBillBaseCollection)(getMaterialReqBillCollection(ctx, oql).cast(InvBillBaseCollection.class));
    }
    public SCMBillBaseCollection getSCMBillBaseCollection (Context ctx) throws BOSException
    {
    	return (SCMBillBaseCollection)(getMaterialReqBillCollection(ctx).cast(SCMBillBaseCollection.class));
    }
    public SCMBillBaseCollection getSCMBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (SCMBillBaseCollection)(getMaterialReqBillCollection(ctx, view).cast(SCMBillBaseCollection.class));
    }
    public SCMBillBaseCollection getSCMBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (SCMBillBaseCollection)(getMaterialReqBillCollection(ctx, oql).cast(SCMBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBillBaseCollection)(getMaterialReqBillCollection(ctx).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBillBaseCollection)(getMaterialReqBillCollection(ctx, view).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBillBaseCollection)(getMaterialReqBillCollection(ctx, oql).cast(CoreBillBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx) throws BOSException
    {
    	return (ObjectBaseCollection)(getMaterialReqBillCollection(ctx).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (ObjectBaseCollection)(getMaterialReqBillCollection(ctx, view).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (ObjectBaseCollection)(getMaterialReqBillCollection(ctx, oql).cast(ObjectBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getMaterialReqBillCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getMaterialReqBillCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getMaterialReqBillCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}