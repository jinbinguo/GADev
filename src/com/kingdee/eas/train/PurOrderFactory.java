package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurOrderFactory
{
    private PurOrderFactory()
    {
    }
    public static com.kingdee.eas.train.IPurOrder getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrder)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("696867B9") ,com.kingdee.eas.train.IPurOrder.class);
    }
    
    public static com.kingdee.eas.train.IPurOrder getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrder)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("696867B9") ,com.kingdee.eas.train.IPurOrder.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurOrder getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrder)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("696867B9"));
    }
    public static com.kingdee.eas.train.IPurOrder getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurOrder)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("696867B9"));
    }
}