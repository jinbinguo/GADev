package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class TestGroupTreeFactory
{
    private TestGroupTreeFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.ITestGroupTree getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroupTree)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("DB103C63") ,com.kingdee.eas.myframework.test.ITestGroupTree.class);
    }
    
    public static com.kingdee.eas.myframework.test.ITestGroupTree getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroupTree)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("DB103C63") ,com.kingdee.eas.myframework.test.ITestGroupTree.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.ITestGroupTree getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroupTree)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("DB103C63"));
    }
    public static com.kingdee.eas.myframework.test.ITestGroupTree getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestGroupTree)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("DB103C63"));
    }
}