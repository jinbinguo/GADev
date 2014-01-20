package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSInOutQueryFactory
{
    private DMSInOutQueryFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQuery getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQuery)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("9FEBF162") ,com.kingdee.eas.ga.syncdata.IDMSInOutQuery.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQuery getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQuery)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("9FEBF162") ,com.kingdee.eas.ga.syncdata.IDMSInOutQuery.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQuery getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQuery)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("9FEBF162"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQuery getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQuery)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("9FEBF162"));
    }
}