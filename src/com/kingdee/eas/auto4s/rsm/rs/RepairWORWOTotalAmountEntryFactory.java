package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORWOTotalAmountEntryFactory
{
    private RepairWORWOTotalAmountEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("CD6110D6") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("CD6110D6") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("CD6110D6"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOTotalAmountEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("CD6110D6"));
    }
}