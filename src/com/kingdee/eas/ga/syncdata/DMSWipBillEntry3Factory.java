package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSWipBillEntry3Factory
{
    private DMSWipBillEntry3Factory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3 getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("305D8183") ,com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3 getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("305D8183") ,com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3 getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("305D8183"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3 getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBillEntry3)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("305D8183"));
    }
}