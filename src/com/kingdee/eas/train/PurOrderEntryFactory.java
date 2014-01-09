package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurOrderEntryFactory
{
    private PurOrderEntryFactory()
    {
    }
    public static com.kingdee.eas.train.IPurOrderEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrderEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("3A709C39") ,com.kingdee.eas.train.IPurOrderEntry.class);
    }
    
    public static com.kingdee.eas.train.IPurOrderEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrderEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("3A709C39") ,com.kingdee.eas.train.IPurOrderEntry.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurOrderEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrderEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("3A709C39"));
    }
    public static com.kingdee.eas.train.IPurOrderEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrderEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("3A709C39"));
    }
}