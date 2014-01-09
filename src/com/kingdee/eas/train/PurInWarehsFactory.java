package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurInWarehsFactory
{
    private PurInWarehsFactory()
    {
    }
    public static com.kingdee.eas.train.IPurInWarehs getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehs)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("13788182") ,com.kingdee.eas.train.IPurInWarehs.class);
    }
    
    public static com.kingdee.eas.train.IPurInWarehs getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehs)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("13788182") ,com.kingdee.eas.train.IPurInWarehs.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurInWarehs getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehs)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("13788182"));
    }
    public static com.kingdee.eas.train.IPurInWarehs getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehs)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("13788182"));
    }
}