package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurRequestEntryFactory
{
    private PurRequestEntryFactory()
    {
    }
    public static com.kingdee.eas.train.IPurRequestEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequestEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("D6C84858") ,com.kingdee.eas.train.IPurRequestEntry.class);
    }
    
    public static com.kingdee.eas.train.IPurRequestEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequestEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("D6C84858") ,com.kingdee.eas.train.IPurRequestEntry.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurRequestEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequestEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("D6C84858"));
    }
    public static com.kingdee.eas.train.IPurRequestEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequestEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("D6C84858"));
    }
}