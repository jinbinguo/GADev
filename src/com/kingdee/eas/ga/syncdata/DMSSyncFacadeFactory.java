package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSSyncFacadeFactory
{
    private DMSSyncFacadeFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSSyncFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSSyncFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("1829B218") ,com.kingdee.eas.ga.syncdata.IDMSSyncFacade.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSSyncFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSSyncFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("1829B218") ,com.kingdee.eas.ga.syncdata.IDMSSyncFacade.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSSyncFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSSyncFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("1829B218"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSSyncFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSSyncFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("1829B218"));
    }
}