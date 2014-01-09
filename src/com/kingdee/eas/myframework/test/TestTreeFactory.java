package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class TestTreeFactory
{
    private TestTreeFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.ITestTree getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestTree)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("C0244E38") ,com.kingdee.eas.myframework.test.ITestTree.class);
    }
    
    public static com.kingdee.eas.myframework.test.ITestTree getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestTree)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("C0244E38") ,com.kingdee.eas.myframework.test.ITestTree.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.ITestTree getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestTree)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("C0244E38"));
    }
    public static com.kingdee.eas.myframework.test.ITestTree getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestTree)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("C0244E38"));
    }
}