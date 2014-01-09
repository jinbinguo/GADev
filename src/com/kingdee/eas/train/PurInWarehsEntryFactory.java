package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class PurInWarehsEntryFactory
{
    private PurInWarehsEntryFactory()
    {
    }
    public static com.kingdee.eas.train.IPurInWarehsEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehsEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("5A863810") ,com.kingdee.eas.train.IPurInWarehsEntry.class);
    }
    
    public static com.kingdee.eas.train.IPurInWarehsEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehsEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("5A863810") ,com.kingdee.eas.train.IPurInWarehsEntry.class, objectCtx);
    }
    public static com.kingdee.eas.train.IPurInWarehsEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehsEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("5A863810"));
    }
    public static com.kingdee.eas.train.IPurInWarehsEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.train.IPurInWarehsEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("5A863810"));
    }
}