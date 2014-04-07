package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSPrintContentEntryFactory
{
    private DMSPrintContentEntryFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("5C9DBE63") ,com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("5C9DBE63") ,com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("5C9DBE63"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContentEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("5C9DBE63"));
    }
}