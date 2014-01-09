package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurTestEntryFactory
{
    private PurTestEntryFactory()
    {
    }
    public static com.kingdee.eas.train.IPurTestEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurTestEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("FAC291EB") ,com.kingdee.eas.train.IPurTestEntry.class);
    }
    
    public static com.kingdee.eas.train.IPurTestEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurTestEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("FAC291EB") ,com.kingdee.eas.train.IPurTestEntry.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurTestEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurTestEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("FAC291EB"));
    }
    public static com.kingdee.eas.train.IPurTestEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurTestEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("FAC291EB"));
    }
}