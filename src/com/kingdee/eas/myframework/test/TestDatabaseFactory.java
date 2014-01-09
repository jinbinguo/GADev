package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class TestDatabaseFactory
{
    private TestDatabaseFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.ITestDatabase getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestDatabase)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("74940F15") ,com.kingdee.eas.myframework.test.ITestDatabase.class);
    }
    
    public static com.kingdee.eas.myframework.test.ITestDatabase getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestDatabase)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("74940F15") ,com.kingdee.eas.myframework.test.ITestDatabase.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.ITestDatabase getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestDatabase)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("74940F15"));
    }
    public static com.kingdee.eas.myframework.test.ITestDatabase getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestDatabase)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("74940F15"));
    }
}