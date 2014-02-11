package com.kingdee.eas.ga.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class WFactory
{
    private WFactory()
    {
    }
    public static com.kingdee.eas.ga.rs.IW getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IW)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("9871DC0C") ,com.kingdee.eas.ga.rs.IW.class);
    }
    
    public static com.kingdee.eas.ga.rs.IW getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IW)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("9871DC0C") ,com.kingdee.eas.ga.rs.IW.class, objectCtx);
    }
    public static com.kingdee.eas.ga.rs.IW getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IW)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("9871DC0C"));
    }
    public static com.kingdee.eas.ga.rs.IW getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IW)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("9871DC0C"));
    }
}