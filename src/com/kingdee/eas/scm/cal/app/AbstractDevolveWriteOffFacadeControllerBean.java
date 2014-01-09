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
import com.kingdee.eas.scm.cal.info.ReturnInfo;
import com.kingdee.eas.scm.cal.info.DevolveWriteOffResultInfo;
import java.util.HashMap;
import com.kingdee.eas.scm.common.app.ManualWriteoffCommonFacadeControllerBean;



public abstract class AbstractDevolveWriteOffFacadeControllerBean extends ManualWriteoffCommonFacadeControllerBean implements DevolveWriteOffFacadeController
{
    protected AbstractDevolveWriteOffFacadeControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("31A35008");
    }

    public ReturnInfo devolveWriteOff(Context ctx, DevolveWriteOffResultInfo info) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("faa01005-c5c4-4705-ad96-870fbc487c53"), new Object[]{ctx, info});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            ReturnInfo retValue = (ReturnInfo)_devolveWriteOff(ctx, info);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (ReturnInfo)svcCtx.getMethodReturnValue();
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
    protected abstract ReturnInfo _devolveWriteOff(Context ctx, DevolveWriteOffResultInfo info) throws BOSException, EASBizException;

    public boolean inverseWriteOff(Context ctx, String[] ids) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("70ccece7-7671-44db-a69d-494ed4611114"), new Object[]{ctx, ids});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_inverseWriteOff(ctx, ids);
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
    protected abstract boolean _inverseWriteOff(Context ctx, String[] ids) throws BOSException, EASBizException;

    public boolean updateMaterialReqBill(Context ctx, String costAdjustId) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("ddb8697d-773c-4d56-82fd-e5c14b9945ac"), new Object[]{ctx, costAdjustId});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_updateMaterialReqBill(ctx, costAdjustId);
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
    protected boolean _updateMaterialReqBill(Context ctx, String costAdjustId) throws BOSException, EASBizException
    {    	
        return false;
    }

    public ReturnInfo batchDevolveWriteOff(Context ctx, HashMap hashInfo) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1d910957-bb0f-45cc-af97-888d4742865c"), new Object[]{ctx, hashInfo});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            ReturnInfo retValue = (ReturnInfo)_batchDevolveWriteOff(ctx, hashInfo);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (ReturnInfo)svcCtx.getMethodReturnValue();
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
    protected ReturnInfo _batchDevolveWriteOff(Context ctx, HashMap hashInfo) throws BOSException, EASBizException
    {    	
        return null;
    }

}