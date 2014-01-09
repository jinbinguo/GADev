package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class MaterialReqBillEntryFactory
{
    private MaterialReqBillEntryFactory()
    {
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("11774BB4") ,com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry.class);
    }
    
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("11774BB4") ,com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry.class, objectCtx);
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("11774BB4"));
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("11774BB4"));
    }
}