package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class TestBillFactory
{
    private TestBillFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.ITestBill getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBill)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("C01BFEA1") ,com.kingdee.eas.myframework.test.ITestBill.class);
    }
    
    public static com.kingdee.eas.myframework.test.ITestBill getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBill)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("C01BFEA1") ,com.kingdee.eas.myframework.test.ITestBill.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.ITestBill getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBill)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("C01BFEA1"));
    }
    public static com.kingdee.eas.myframework.test.ITestBill getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.ITestBill)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("C01BFEA1"));
    }
}