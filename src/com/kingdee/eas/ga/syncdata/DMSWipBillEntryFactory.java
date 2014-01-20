package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSWipBillEntryFactory
{
    private DMSWipBillEntryFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("D84514B0") ,com.kingdee.eas.ga.syncdata.IDMSWipBillEntry.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("D84514B0") ,com.kingdee.eas.ga.syncdata.IDMSWipBillEntry.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("D84514B0"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("D84514B0"));
    }
}