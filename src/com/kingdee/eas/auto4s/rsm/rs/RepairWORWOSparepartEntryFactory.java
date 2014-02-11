package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORWOSparepartEntryFactory
{
    private RepairWORWOSparepartEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("1BEE6B08") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("1BEE6B08") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("1BEE6B08"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOSparepartEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("1BEE6B08"));
    }
}