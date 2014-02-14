package com.kingdee.eas.base.permission;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class UserFactory
{
    private UserFactory()
    {
    }
    public static com.kingdee.eas.base.permission.IUser getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.base.permission.IUser)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("13B7DE7F") ,com.kingdee.eas.base.permission.IUser.class);
    }
    
    public static com.kingdee.eas.base.permission.IUser getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.base.permission.IUser)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("13B7DE7F") ,com.kingdee.eas.base.permission.IUser.class, objectCtx);
    }
    public static com.kingdee.eas.base.permission.IUser getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.base.permission.IUser)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("13B7DE7F"));
    }
    public static com.kingdee.eas.base.permission.IUser getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.base.permission.IUser)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("13B7DE7F"));
    }
}