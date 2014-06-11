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




public abstract class AbstractDMSSyncFacadeControllerBean extends AbstractBizControllerBean implements DMSSyncFacadeController
{
    protected AbstractDMSSyncFacadeControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("1829B218");
    }

    public void ImportCustProfiles(Context ctx, int maxRecords) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("56acb60a-3afb-494b-992a-ccd286651e90"), new Object[]{ctx, new Integer(maxRecords)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _ImportCustProfiles(ctx, maxRecords);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _ImportCustProfiles(Context ctx, int maxRecords) throws BOSException
    {    	
        return;
    }

    public void ImportVehicleTypes(Context ctx, int maxRecords) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1ae3fe40-33e3-4c16-901d-cf77a3fc0faf"), new Object[]{ctx, new Integer(maxRecords)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _ImportVehicleTypes(ctx, maxRecords);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _ImportVehicleTypes(Context ctx, int maxRecords) throws BOSException
    {    	
        return;
    }

    public void ImportVehicleProfiles(Context ctx, int maxRecords) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("ec08d4e3-8388-4e55-aeb7-495e01bc6111"), new Object[]{ctx, new Integer(maxRecords)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _ImportVehicleProfiles(ctx, maxRecords);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected void _ImportVehicleProfiles(Context ctx, int maxRecords) throws BOSException
    {    	
        return;
    }

}