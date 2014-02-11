package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class RepairWORWOAttachmentItemEntryFactory
{
    private RepairWORWOAttachmentItemEntryFactory()
    {
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("A0C5DF7C") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry.class);
    }
    
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("A0C5DF7C") ,com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("A0C5DF7C"));
    }
    public static com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.rsm.rs.IRepairWORWOAttachmentItemEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("A0C5DF7C"));
    }
}