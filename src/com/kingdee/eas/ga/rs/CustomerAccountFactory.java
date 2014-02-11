package com.kingdee.eas.ga.rs;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class CustomerAccountFactory
{
    private CustomerAccountFactory()
    {
    }
    public static com.kingdee.eas.ga.rs.ICustomerAccount getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.rs.ICustomerAccount)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("8B9E1F44") ,com.kingdee.eas.ga.rs.ICustomerAccount.class);
    }
    
    public static com.kingdee.eas.ga.rs.ICustomerAccount getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.ICustomerAccount)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("8B9E1F44") ,com.kingdee.eas.ga.rs.ICustomerAccount.class, objectCtx);
    }
    public static com.kingdee.eas.ga.rs.ICustomerAccount getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.ICustomerAccount)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("8B9E1F44"));
    }
    public static com.kingdee.eas.ga.rs.ICustomerAccount getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.rs.ICustomerAccount)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("8B9E1F44"));
    }
}