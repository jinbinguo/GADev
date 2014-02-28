package com.kingdee.eas.ga.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairPackageFactory
{
    private RepairPackageFactory()
    {
    }
    public static com.kingdee.eas.ga.rs.IRepairPackage getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairPackage)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("988AA80E") ,com.kingdee.eas.ga.rs.IRepairPackage.class);
    }
    
    public static com.kingdee.eas.ga.rs.IRepairPackage getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairPackage)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("988AA80E") ,com.kingdee.eas.ga.rs.IRepairPackage.class, objectCtx);
    }
    public static com.kingdee.eas.ga.rs.IRepairPackage getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairPackage)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("988AA80E"));
    }
    public static com.kingdee.eas.ga.rs.IRepairPackage getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairPackage)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("988AA80E"));
    }
}