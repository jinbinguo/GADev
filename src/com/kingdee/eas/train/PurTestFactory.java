package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurTestFactory
{
    private PurTestFactory()
    {
    }
    public static com.kingdee.eas.train.IPurTest getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurTest)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("D1DC2AC7") ,com.kingdee.eas.train.IPurTest.class);
    }
    
    public static com.kingdee.eas.train.IPurTest getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurTest)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("D1DC2AC7") ,com.kingdee.eas.train.IPurTest.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurTest getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurTest)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("D1DC2AC7"));
    }
    public static com.kingdee.eas.train.IPurTest getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurTest)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("D1DC2AC7"));
    }
}