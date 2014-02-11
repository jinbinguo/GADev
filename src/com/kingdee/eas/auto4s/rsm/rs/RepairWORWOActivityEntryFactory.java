package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORWOActivityEntryFactory
{
    private RepairWORWOActivityEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("3572D6E3") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("3572D6E3") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("3572D6E3"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOActivityEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("3572D6E3"));
    }
}