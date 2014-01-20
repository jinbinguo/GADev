package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class AAAEntryFactory
{
    private AAAEntryFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.IAAAEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAAEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("4230DF99") ,com.kingdee.eas.myframework.test.IAAAEntry.class);
    }
    
    public static com.kingdee.eas.myframework.test.IAAAEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAAEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("4230DF99") ,com.kingdee.eas.myframework.test.IAAAEntry.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.IAAAEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAAEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("4230DF99"));
    }
    public static com.kingdee.eas.myframework.test.IAAAEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAAEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("4230DF99"));
    }
}