package com.kingdee.eas.myframework.common.app;

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
import com.kingdee.jdbc.rowset.IRowSet;



public abstract class AbstractSQLExecutorControllerBean extends AbstractBizControllerBean implements SQLExecutorController
{
    protected AbstractSQLExecutorControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("FD8403F2");
    }

    public void execute(Context ctx, String sql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("2c022688-1759-4f95-b2bc-710982e35b51"), new Object[]{ctx, sql});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _execute(ctx, sql);
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
    protected void _execute(Context ctx, String sql) throws BOSException, EASBizException
    {    	
        return;
    }

    public IRowSet executeQuery(Context ctx, String sql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("4dd01c9e-4a96-4943-8ddc-440249539c87"), new Object[]{ctx, sql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IRowSet retValue = (IRowSet)_executeQuery(ctx, sql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (IRowSet)svcCtx.getMethodReturnValue();
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
    protected IRowSet _executeQuery(Context ctx, String sql) throws BOSException, EASBizException
    {    	
        return null;
    }

}