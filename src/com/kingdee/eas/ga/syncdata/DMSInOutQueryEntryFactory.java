package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSInOutQueryEntryFactory
{
    private DMSInOutQueryEntryFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("861DB430") ,com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("861DB430") ,com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("861DB430"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSInOutQueryEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("861DB430"));
    }
}