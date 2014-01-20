package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSWipBillFactory
{
    private DMSWipBillFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBill getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBill)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("71CA40E2") ,com.kingdee.eas.ga.syncdata.IDMSWipBill.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSWipBill getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBill)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("71CA40E2") ,com.kingdee.eas.ga.syncdata.IDMSWipBill.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBill getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBill)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("71CA40E2"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipBill getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipBill)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("71CA40E2"));
    }
}