package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORWORepairItemEntryFactory
{
    private RepairWORWORepairItemEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("185AD052") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("185AD052") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("185AD052"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWORepairItemEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("185AD052"));
    }
}