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
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.auto4s.autoframework.core.AutoBillBaseCollection;
import java.util.Map;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.auto4s.autoframework.core.app.AutoBillBaseControllerBean;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.auto4s.rsm.rs.RsmRsException;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOCollection;



public abstract class AbstractRepairWOControllerBean extends AutoBillBaseControllerBean implements RepairWOController
{
    protected AbstractRepairWOControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("FDBE5ECA");
    }

    public RepairWOCollection getRepairWOCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e2aeeefe-6fb8-4612-b047-2f5ec4be07f7"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWOCollection retValue = (RepairWOCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWOCollection)svcCtx.getMethodReturnValue();
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

    public RepairWOCollection getRepairWOCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("880c197e-950e-4aaa-9aa6-963d5224e8a7"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWOCollection retValue = (RepairWOCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWOCollection)svcCtx.getMethodReturnValue();
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

    public RepairWOCollection getRepairWOCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("9b051ed8-89b2-4058-8125-8bfdb7888171"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWOCollection retValue = (RepairWOCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWOCollection)svcCtx.getMethodReturnValue();
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

    public RepairWOInfo getRepairWOInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("299fd80d-08fe-48f5-bdb6-ded1f096d132"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWOInfo retValue = (RepairWOInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWOInfo)svcCtx.getMethodReturnValue();
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

    public RepairWOInfo getRepairWOInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("46072e08-b8aa-45d9-b7ed-269d0c4ea3ec"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWOInfo retValue = (RepairWOInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWOInfo)svcCtx.getMethodReturnValue();
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

    public RepairWOInfo getRepairWOInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("3d6ec8c5-784d-4113-b94b-93f56d503993"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            RepairWOInfo retValue = (RepairWOInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (RepairWOInfo)svcCtx.getMethodReturnValue();
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

    public void uninvalid(Context ctx, RepairWOInfo model) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1ae6b07b-ab78-413c-b07b-b2f0c7bf37bc"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _uninvalid(ctx, model);
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
    protected abstract void _uninvalid(Context ctx, IObjectValue model) throws BOSException, EASBizException;

    public void invalid(Context ctx, RepairWOInfo model) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("051dd96a-baa4-46e5-8066-8aa157b02d5e"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _invalid(ctx, model);
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
    protected abstract void _invalid(Context ctx, IObjectValue model) throws BOSException, EASBizException;

    public void assign(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("407caa19-e8c0-411d-a083-34d19cb2053b"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _assign(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _assign(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void repairBreak(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("72081ea3-ebff-44ac-92c3-cf063d63ec55"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _repairBreak(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _repairBreak(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void breakRestore(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a28efe56-8660-48a2-bb93-41ad084b51aa"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _breakRestore(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _breakRestore(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void inspect(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7e1de6bd-56a0-4174-a550-7c5051d7ba92"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _inspect(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _inspect(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void wash(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("5ee53f18-761d-41d2-90bf-5d6e49211a81"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _wash(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _wash(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void workTime(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7778d3b2-3770-4f15-b21b-3db3895e247d"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _workTime(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _workTime(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void dispatching(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a736cec8-e9e8-4f09-8fa4-9153d3cb510c"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _dispatching(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _dispatching(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void timeBooking(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("ce0343e0-bf14-4374-a921-0c47e0a16e5a"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _timeBooking(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _timeBooking(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void deleteRepairWO(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("17510389-57cf-4936-9530-05571092b287"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _deleteRepairWO(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _deleteRepairWO(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void isB(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("765feb75-5d29-40aa-b3a4-aa90e269fb15"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _isB(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _isB(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void cancelB(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("dc6f2fe6-6612-4ba5-92c9-f48ba790de73"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _cancelB(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _cancelB(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void addEnter(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("244c654b-f28c-4dcb-8338-dd6bd267db61"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _addEnter(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _addEnter(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void adjust(Context ctx, RepairWOInfo model) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a0a01b2e-0216-4edc-abe1-a3f2a6d8468f"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _adjust(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _adjust(Context ctx, IObjectValue model) throws BOSException;

    public void itemIssue(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("b065136b-e403-46af-b094-5b8bf62a26d0"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _itemIssue(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _itemIssue(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void cancelAssign(Context ctx, Map cancelMap) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("55afe45e-1045-4b87-8f4b-11e8a1d33768"), new Object[]{ctx, cancelMap});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _cancelAssign(ctx, cancelMap);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _cancelAssign(Context ctx, Map cancelMap) throws BOSException, RsmRsException, EASBizException;

    public boolean isAllIssue(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("bcea2196-566e-4049-943d-d259cbd63cfc"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_isAllIssue(ctx, model);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (RsmRsException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } catch (EASBizException ex1) {
            this.setRollbackOnly();
            throw ex1;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _isAllIssue(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException;

    public void unWorkTime(Context ctx, Map paramMap) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("ef3d6e20-385a-4f84-8993-719cd300e6e3"), new Object[]{ctx, paramMap});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _unWorkTime(ctx, paramMap);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _unWorkTime(Context ctx, Map paramMap) throws BOSException;

    public void unWash(Context ctx, RepairWOInfo model) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("df71673a-47c0-49ba-915d-b1606a167721"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _unWash(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _unWash(Context ctx, IObjectValue model) throws BOSException;

    public void unInspect(Context ctx, Map paramMap) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7fc9a406-d98e-44e1-95f2-b70b25fac776"), new Object[]{ctx, paramMap});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _unInspect(ctx, paramMap);
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
    protected abstract void _unInspect(Context ctx, Map paramMap) throws BOSException, EASBizException;

    public void updateRWOVersion(Context ctx, RepairWOInfo model) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("c3bab8d0-6311-43d8-b025-80d6377ef094"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _updateRWOVersion(ctx, model);
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
    protected abstract void _updateRWOVersion(Context ctx, IObjectValue model) throws BOSException, EASBizException;

    public Map getRWOFromRepairBooking(Context ctx, Map paramMap) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("d6ff349e-e747-4ada-a803-e462692638b5"), new Object[]{ctx, paramMap});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            Map retValue = (Map)_getRWOFromRepairBooking(ctx, paramMap);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (Map)svcCtx.getMethodReturnValue();
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
    protected abstract Map _getRWOFromRepairBooking(Context ctx, Map paramMap) throws BOSException, EASBizException;

    public void audit(Context ctx, RepairWOInfo model) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e54a3bcf-2175-41a8-bcb5-0bf9bd6db134"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _audit(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _audit(Context ctx, IObjectValue model) throws BOSException
    {    	
        return;
    }

    public void unAudit(Context ctx, RepairWOInfo model) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("953887c4-0ff5-44bf-a210-04102b6f4fdc"), new Object[]{ctx, model});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _unAudit(ctx, model);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _unAudit(Context ctx, IObjectValue model) throws BOSException
    {    	
        return;
    }

					protected com.kingdee.eas.basedata.org.OrgUnitInfo getMainBizOrgUnit(Context ctx,com.kingdee.eas.framework.CoreBillBaseInfo model) {
			Object obj = model.get("OrgUnit");
			if (obj != null && obj instanceof com.kingdee.eas.basedata.org.OrgUnitInfo)
				return (com.kingdee.eas.basedata.org.OrgUnitInfo)obj;
			else
				return null;
		}

    public AutoBillBaseCollection getAutoBillBaseCollection (Context ctx) throws BOSException
    {
    	return (AutoBillBaseCollection)(getRepairWOCollection(ctx).cast(AutoBillBaseCollection.class));
    }
    public AutoBillBaseCollection getAutoBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (AutoBillBaseCollection)(getRepairWOCollection(ctx, view).cast(AutoBillBaseCollection.class));
    }
    public AutoBillBaseCollection getAutoBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (AutoBillBaseCollection)(getRepairWOCollection(ctx, oql).cast(AutoBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBillBaseCollection)(getRepairWOCollection(ctx).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBillBaseCollection)(getRepairWOCollection(ctx, view).cast(CoreBillBaseCollection.class));
    }
    public CoreBillBaseCollection getCoreBillBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBillBaseCollection)(getRepairWOCollection(ctx, oql).cast(CoreBillBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx) throws BOSException
    {
    	return (ObjectBaseCollection)(getRepairWOCollection(ctx).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (ObjectBaseCollection)(getRepairWOCollection(ctx, view).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (ObjectBaseCollection)(getRepairWOCollection(ctx, oql).cast(ObjectBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getRepairWOCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getRepairWOCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getRepairWOCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}