package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SyncDataFacadeFactory
{
    private SyncDataFacadeFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.ISyncDataFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncDataFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("76F81EA6") ,com.kingdee.eas.ga.syncdata.ISyncDataFacade.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.ISyncDataFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncDataFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("76F81EA6") ,com.kingdee.eas.ga.syncdata.ISyncDataFacade.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.ISyncDataFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncDataFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("76F81EA6"));
    }
    public static com.kingdee.eas.ga.syncdata.ISyncDataFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncDataFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("76F81EA6"));
    }
}