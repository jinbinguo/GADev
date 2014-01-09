package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurRequestFactory
{
    private PurRequestFactory()
    {
    }
    public static com.kingdee.eas.train.IPurRequest getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequest)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("3A2F643A") ,com.kingdee.eas.train.IPurRequest.class);
    }
    
    public static com.kingdee.eas.train.IPurRequest getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequest)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("3A2F643A") ,com.kingdee.eas.train.IPurRequest.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurRequest getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequest)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("3A2F643A"));
    }
    public static com.kingdee.eas.train.IPurRequest getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurRequest)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("3A2F643A"));
    }
}