package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class TestBillEntryFactory
{
    private TestBillEntryFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.ITestBillEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBillEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("9E6F1651") ,com.kingdee.eas.myframework.test.ITestBillEntry.class);
    }
    
    public static com.kingdee.eas.myframework.test.ITestBillEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBillEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("9E6F1651") ,com.kingdee.eas.myframework.test.ITestBillEntry.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.ITestBillEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBillEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("9E6F1651"));
    }
    public static com.kingdee.eas.myframework.test.ITestBillEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBillEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("9E6F1651"));
    }
}