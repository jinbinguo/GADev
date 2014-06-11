package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SyncLogFactory
{
    private SyncLogFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.ISyncLog getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLog)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("2BA1BFC2") ,com.kingdee.eas.ga.syncdata.ISyncLog.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.ISyncLog getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLog)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("2BA1BFC2") ,com.kingdee.eas.ga.syncdata.ISyncLog.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.ISyncLog getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLog)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("2BA1BFC2"));
    }
    public static com.kingdee.eas.ga.syncdata.ISyncLog getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLog)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("2BA1BFC2"));
    }
}