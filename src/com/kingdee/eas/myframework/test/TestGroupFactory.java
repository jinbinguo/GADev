package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class TestGroupFactory
{
    private TestGroupFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.ITestGroup getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroup)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("43AE6F25") ,com.kingdee.eas.myframework.test.ITestGroup.class);
    }
    
    public static com.kingdee.eas.myframework.test.ITestGroup getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroup)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("43AE6F25") ,com.kingdee.eas.myframework.test.ITestGroup.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.ITestGroup getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroup)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("43AE6F25"));
    }
    public static com.kingdee.eas.myframework.test.ITestGroup getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroup)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("43AE6F25"));
    }
}