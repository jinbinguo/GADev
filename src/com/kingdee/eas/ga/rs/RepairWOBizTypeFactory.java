package com.kingdee.eas.ga.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWOBizTypeFactory
{
    private RepairWOBizTypeFactory()
    {
    }
    public static com.kingdee.eas.ga.rs.IRepairWOBizType getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairWOBizType)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("8CCB6ADD") ,com.kingdee.eas.ga.rs.IRepairWOBizType.class);
    }
    
    public static com.kingdee.eas.ga.rs.IRepairWOBizType getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairWOBizType)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("8CCB6ADD") ,com.kingdee.eas.ga.rs.IRepairWOBizType.class, objectCtx);
    }
    public static com.kingdee.eas.ga.rs.IRepairWOBizType getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairWOBizType)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("8CCB6ADD"));
    }
    public static com.kingdee.eas.ga.rs.IRepairWOBizType getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.IRepairWOBizType)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("8CCB6ADD"));
    }
}