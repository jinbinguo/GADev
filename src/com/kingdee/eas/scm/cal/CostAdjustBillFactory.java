package com.kingdee.eas.scm.cal;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class CostAdjustBillFactory
{
    private CostAdjustBillFactory()
    {
    }
    public static com.kingdee.eas.scm.cal.ICostAdjustBill getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.scm.cal.ICostAdjustBill)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("8FA62986") ,com.kingdee.eas.scm.cal.ICostAdjustBill.class);
    }
    
    public static com.kingdee.eas.scm.cal.ICostAdjustBill getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.scm.cal.ICostAdjustBill)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("8FA62986") ,com.kingdee.eas.scm.cal.ICostAdjustBill.class, objectCtx);
    }
    public static com.kingdee.eas.scm.cal.ICostAdjustBill getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.scm.cal.ICostAdjustBill)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("8FA62986"));
    }
    public static com.kingdee.eas.scm.cal.ICostAdjustBill getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.scm.cal.ICostAdjustBill)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("8FA62986"));
    }
}