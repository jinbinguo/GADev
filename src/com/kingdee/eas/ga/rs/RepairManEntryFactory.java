package com.kingdee.eas.ga.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairManEntryFactory
{
    private RepairManEntryFactory()
    {
    }
    public static com.kingdee.eas.ga.rs.IRepairManEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairManEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("51DAC070") ,com.kingdee.eas.ga.rs.IRepairManEntry.class);
    }
    
    public static com.kingdee.eas.ga.rs.IRepairManEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairManEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("51DAC070") ,com.kingdee.eas.ga.rs.IRepairManEntry.class, objectCtx);
    }
    public static com.kingdee.eas.ga.rs.IRepairManEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairManEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("51DAC070"));
    }
    public static com.kingdee.eas.ga.rs.IRepairManEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairManEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("51DAC070"));
    }
}