package com.kingdee.eas.ga.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairManFactory
{
    private RepairManFactory()
    {
    }
    public static com.kingdee.eas.ga.rs.IRepairMan getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairMan)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("40C83D22") ,com.kingdee.eas.ga.rs.IRepairMan.class);
    }
    
    public static com.kingdee.eas.ga.rs.IRepairMan getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairMan)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("40C83D22") ,com.kingdee.eas.ga.rs.IRepairMan.class, objectCtx);
    }
    public static com.kingdee.eas.ga.rs.IRepairMan getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairMan)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("40C83D22"));
    }
    public static com.kingdee.eas.ga.rs.IRepairMan getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairMan)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("40C83D22"));
    }
}