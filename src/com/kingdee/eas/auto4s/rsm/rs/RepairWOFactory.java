package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWOFactory
{
    private RepairWOFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWO getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWO)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("FDBE5ECA") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWO.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWO getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWO)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("FDBE5ECA") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWO.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWO getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWO)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("FDBE5ECA"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWO getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWO)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("FDBE5ECA"));
    }
}