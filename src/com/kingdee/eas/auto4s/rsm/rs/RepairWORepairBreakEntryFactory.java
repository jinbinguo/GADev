package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORepairBreakEntryFactory
{
    private RepairWORepairBreakEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("892C9F62") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("892C9F62") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("892C9F62"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORepairBreakEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("892C9F62"));
    }
}