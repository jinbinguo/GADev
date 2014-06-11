package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SyncLogEntryFactory
{
    private SyncLogEntryFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.ISyncLogEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLogEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("2DA9E1D0") ,com.kingdee.eas.ga.syncdata.ISyncLogEntry.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.ISyncLogEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLogEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("2DA9E1D0") ,com.kingdee.eas.ga.syncdata.ISyncLogEntry.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.ISyncLogEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLogEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("2DA9E1D0"));
    }
    public static com.kingdee.eas.ga.syncdata.ISyncLogEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.ISyncLogEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("2DA9E1D0"));
    }
}