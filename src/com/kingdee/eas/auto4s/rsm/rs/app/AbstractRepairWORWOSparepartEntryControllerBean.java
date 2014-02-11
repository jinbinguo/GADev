package com.kingdee.eas.auto4s.rsm.rs.app;

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
import java.math.BigDecimal;
import com.kingdee.eas.framework.CoreBillEntryBaseCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.eas.framework.app.CoreBillEntryBaseControllerBean;
import com.kingdee.eas.auto4s.rsm.rs.RsmRsException;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection;



public abstract class AbstractRepairWORWOSparepartEntryControllerBean extends CoreBillEntryBaseControllerBean implements RepairWORWOSparepartEntryController
{
    protected AbstractRepairWORWOSparepartEntryControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("1BEE6B08");
    }

    public boolean exists(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7a4ab314-9fba-4fb9-972a-e9e2e752ce49"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_exists(ctx, pk);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
        
          return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected boolean _exists(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        return super._exists(ctx, pk);
    }

    public boolean exists(Context ctx, FilterInfo filter) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("ba637b52-ea69-40a9-abef-da3130a2ed4b"), new Object[]{ctx, filter});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_exists(ctx, filter);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
        
          return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected boolean _exists(Context ctx, FilterInfo filter) throws BOSException, EASBizException
    {
        return super._exists(ctx, filter);
    }

    public boolean exists(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("8bbb3bda-f8fa-4556-9c52-c24912bd4729"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_exists(ctx, oql);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
        
          return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected boolean _exists(Context ctx, String oql) throws BOSException, EASBizException
    {
        return super._exists(ctx, oql);
    }

    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("21a2eaf2-89fc-48e4-9f94-1723afde10e5"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWORWOSparepartEntryInfo retValue = (RepairWORWOSparepartEntryInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWORWOSparepartEntryInfo)svcCtx.getMethodReturnValue();
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

    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7e7eeb42-34f0-40a4-a36b-726e039efe2e"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWORWOSparepartEntryInfo retValue = (RepairWORWOSparepartEntryInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWORWOSparepartEntryInfo)svcCtx.getMethodReturnValue();
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

    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("43189f03-c7b4-42b9-9234-1629317f9248"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWORWOSparepartEntryInfo retValue = (RepairWORWOSparepartEntryInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWORWOSparepartEntryInfo)svcCtx.getMethodReturnValue();
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

    public IObjectPK addnew(Context ctx, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("9006a080-9093-47ef-b119-1201bda5da69"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IObjectPK retValue = (IObjectPK)_addnew(ctx, model);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (IObjectPK)svcCtx.getMethodReturnValue();
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
    protected IObjectPK _addnew(Context ctx, IObjectValue model) throws BOSException, EASBizException
    {
        return super._addnew(ctx, model);
    }

    public void addnew(Context ctx, IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("5facc8a8-c306-4e79-bab0-f3a888be7ea5"), new Object[]{ctx, pk, model});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()){
            _addnew(ctx, pk, model);
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
    protected void _addnew(Context ctx, IObjectPK pk, IObjectValue model) throws BOSException, EASBizException
    {
        super._addnew(ctx, pk, model);
    }

    public void update(Context ctx, IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("26feb224-713a-4c33-9e77-b81b6035d084"), new Object[]{ctx, pk, model});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()){
            _update(ctx, pk, model);
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
    protected void _update(Context ctx, IObjectPK pk, IObjectValue model) throws BOSException, EASBizException
    {
        super._update(ctx, pk, model);
    }

    public void updatePartial(Context ctx, RepairWORWOSparepartEntryInfo model, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("29a39297-b09c-4975-aee6-6d13840397ed"), new Object[]{ctx, model, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()){
            _updatePartial(ctx, model, selector);
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
    protected void _updatePartial(Context ctx, IObjectValue model, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        super._updatePartial(ctx, model, selector);
    }

    public void updateBigObject(Context ctx, IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("48d9339c-63c2-48ba-8230-6935920c2fd2"), new Object[]{ctx, pk, model});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()){
            _updateBigObject(ctx, pk, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _updateBigObject(Context ctx, IObjectPK pk, IObjectValue model) throws BOSException
    {
        innerUpdateBigObject(ctx, pk, model);
    }

    public void delete(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("399f4088-09e9-46ad-9684-cea6e66f7449"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()){
            _delete(ctx, pk);
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
    protected void _delete(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        super._delete(ctx, pk);
    }

    public IObjectPK[] getPKList(Context ctx) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("794eb8e4-bd64-4b3f-835b-95f3dff229e2"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IObjectPK[] retValue = (IObjectPK[])_getPKList(ctx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (IObjectPK[])svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectPK[] _getPKList(Context ctx) throws BOSException, EASBizException
    {
        return super._getPKList(ctx);
    }

    public IObjectPK[] getPKList(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("fab3867f-6bc9-462e-b572-76d1ff71e865"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IObjectPK[] retValue = (IObjectPK[])_getPKList(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (IObjectPK[])svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectPK[] _getPKList(Context ctx, String oql) throws BOSException, EASBizException
    {
        return super._getPKList(ctx, oql);
    }

    public IObjectPK[] getPKList(Context ctx, FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1bcdb156-1a0d-4f6f-b99d-0b3e8038a308"), new Object[]{ctx, filter, sorter});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IObjectPK[] retValue = (IObjectPK[])_getPKList(ctx, filter, sorter);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (IObjectPK[])svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectPK[] _getPKList(Context ctx, FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException
    {
        return super._getPKList(ctx, filter, sorter);
    }

    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("c4a13e28-dffb-4165-9b13-6c675989589b"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWORWOSparepartEntryCollection retValue = (RepairWORWOSparepartEntryCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWORWOSparepartEntryCollection)svcCtx.getMethodReturnValue();
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

    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("46d0dd72-5bd0-45ac-9769-f08ea8d42977"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWORWOSparepartEntryCollection retValue = (RepairWORWOSparepartEntryCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWORWOSparepartEntryCollection)svcCtx.getMethodReturnValue();
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

    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("d6001f2f-459a-4d5f-ab3f-cdd3f4385d8f"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWORWOSparepartEntryCollection retValue = (RepairWORWOSparepartEntryCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWORWOSparepartEntryCollection)svcCtx.getMethodReturnValue();
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

    public IObjectPK[] delete(Context ctx, FilterInfo filter) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a2dae72e-7ce0-4d13-aeed-2f6345ac9288"), new Object[]{ctx, filter});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IObjectPK[] retValue = (IObjectPK[])_delete(ctx, filter);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (IObjectPK[])svcCtx.getMethodReturnValue();
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
    protected IObjectPK[] _delete(Context ctx, FilterInfo filter) throws BOSException, EASBizException
    {
        return super._delete(ctx, filter);
    }

    public IObjectPK[] delete(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("3b48f0e9-7eea-486e-80ab-a6063cd2baa4"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IObjectPK[] retValue = (IObjectPK[])_delete(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (IObjectPK[])svcCtx.getMethodReturnValue();
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
    protected IObjectPK[] _delete(Context ctx, String oql) throws BOSException, EASBizException
    {
        return super._delete(ctx, oql);
    }

    public void delete(Context ctx, IObjectPK[] arrayPK) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a794eea5-20f0-46e1-b591-9e4c65ebfe46"), new Object[]{ctx, arrayPK});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()){
            _delete(ctx, arrayPK);
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
    protected void _delete(Context ctx, IObjectPK[] arrayPK) throws BOSException, EASBizException
    {
        super._delete(ctx, arrayPK);
    }

    public IRowSet getResourceId(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("06c25d99-4257-4475-8342-913796ff2509"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IRowSet retValue = (IRowSet)_getResourceId(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (IRowSet)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract IRowSet _getResourceId(Context ctx, String oql) throws BOSException;

    public void updateQty(Context ctx, BigDecimal issueQty, String entryId, BigDecimal noIssueQty, String transType, String oprateType) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("3911c7bd-66ef-42b7-bcc2-1c96675d6828"), new Object[]{ctx, issueQty, entryId, noIssueQty, transType, oprateType});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _updateQty(ctx, issueQty, entryId, noIssueQty, transType, oprateType);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _updateQty(Context ctx, BigDecimal issueQty, String entryId, BigDecimal noIssueQty, String transType, String oprateType) throws BOSException;

    public void checkQty(Context ctx, BigDecimal qty, String entryID, String transType, String oprateType) throws BOSException, RsmRsException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("d8e3585d-3eed-4a10-a6c7-6befe30b4b7d"), new Object[]{ctx, qty, entryID, transType, oprateType});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _checkQty(ctx, qty, entryID, transType, oprateType);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _checkQty(Context ctx, BigDecimal qty, String entryID, String transType, String oprateType) throws BOSException, RsmRsException;

    public CoreBillEntryBaseCollection getCoreBillEntryBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBillEntryBaseCollection)(getRepairWORWOSparepartEntryCollection(ctx).cast(CoreBillEntryBaseCollection.class));
    }
    public CoreBillEntryBaseCollection getCoreBillEntryBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBillEntryBaseCollection)(getRepairWORWOSparepartEntryCollection(ctx, view).cast(CoreBillEntryBaseCollection.class));
    }
    public CoreBillEntryBaseCollection getCoreBillEntryBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBillEntryBaseCollection)(getRepairWORWOSparepartEntryCollection(ctx, oql).cast(CoreBillEntryBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getRepairWORWOSparepartEntryCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getRepairWORWOSparepartEntryCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getRepairWORWOSparepartEntryCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}