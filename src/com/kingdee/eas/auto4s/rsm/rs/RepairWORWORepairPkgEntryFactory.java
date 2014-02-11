package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORWORepairPkgEntryFactory
{
    private RepairWORWORepairPkgEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("15E83473") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("15E83473") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("15E83473"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairPkgEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("15E83473"));
    }
}