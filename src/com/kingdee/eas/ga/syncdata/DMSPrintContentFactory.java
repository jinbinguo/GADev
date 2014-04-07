package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSPrintContentFactory
{
    private DMSPrintContentFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContent getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContent)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("B165134F") ,com.kingdee.eas.ga.syncdata.IDMSPrintContent.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContent getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContent)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("B165134F") ,com.kingdee.eas.ga.syncdata.IDMSPrintContent.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContent getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContent)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("B165134F"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSPrintContent getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSPrintContent)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("B165134F"));
    }
}