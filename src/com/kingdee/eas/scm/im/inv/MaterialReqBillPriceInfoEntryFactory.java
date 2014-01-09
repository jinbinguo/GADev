package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class MaterialReqBillPriceInfoEntryFactory
{
    private MaterialReqBillPriceInfoEntryFactory()
    {
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("97A53E9B") ,com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry.class);
    }
    
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("97A53E9B") ,com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry.class, objectCtx);
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("97A53E9B"));
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBillPriceInfoEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("97A53E9B"));
    }
}