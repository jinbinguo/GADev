package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class MaterialReqBillFactory
{
    private MaterialReqBillFactory()
    {
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBill getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBill)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("500AB75E") ,com.kingdee.eas.scm.im.inv.IMaterialReqBill.class);
    }
    
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBill getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBill)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("500AB75E") ,com.kingdee.eas.scm.im.inv.IMaterialReqBill.class, objectCtx);
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBill getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBill)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("500AB75E"));
    }
    public static com.kingdee.eas.scm.im.inv.IMaterialReqBill getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.IMaterialReqBill)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("500AB75E"));
    }
}