package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSWipBillEntry2Factory
{
    private DMSWipBillEntry2Factory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2 getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("305D8182") ,com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2 getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("305D8182") ,com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2 getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("305D8182"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2 getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry2)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("305D8182"));
    }
}