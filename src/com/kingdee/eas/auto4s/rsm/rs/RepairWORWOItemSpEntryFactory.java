package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORWOItemSpEntryFactory
{
    private RepairWORWOItemSpEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("FF1F0E1A") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("FF1F0E1A") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("FF1F0E1A"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOItemSpEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("FF1F0E1A"));
    }
}