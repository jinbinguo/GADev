package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SaleIssueBillFactory
{
    private SaleIssueBillFactory()
    {
    }
    public static com.kingdee.eas.scm.im.inv.ISaleIssueBill getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.ISaleIssueBill)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("CC3E933B") ,com.kingdee.eas.scm.im.inv.ISaleIssueBill.class);
    }
    
    public static com.kingdee.eas.scm.im.inv.ISaleIssueBill getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.ISaleIssueBill)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("CC3E933B") ,com.kingdee.eas.scm.im.inv.ISaleIssueBill.class, objectCtx);
    }
    public static com.kingdee.eas.scm.im.inv.ISaleIssueBill getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.ISaleIssueBill)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("CC3E933B"));
    }
    public static com.kingdee.eas.scm.im.inv.ISaleIssueBill getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.scm.im.inv.ISaleIssueBill)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("CC3E933B"));
    }
}