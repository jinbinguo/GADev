package com.kingdee.eas.myframework.common;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class SQLExecutorFactory
{
    private SQLExecutorFactory()
    {
    }
    public static com.kingdee.eas.myframework.common.ISQLExecutor getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.common.ISQLExecutor)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("FD8403F2") ,com.kingdee.eas.myframework.common.ISQLExecutor.class);
    }
    
    public static com.kingdee.eas.myframework.common.ISQLExecutor getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.common.ISQLExecutor)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("FD8403F2") ,com.kingdee.eas.myframework.common.ISQLExecutor.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.common.ISQLExecutor getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.common.ISQLExecutor)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("FD8403F2"));
    }
    public static com.kingdee.eas.myframework.common.ISQLExecutor getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.common.ISQLExecutor)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("FD8403F2"));
    }
}