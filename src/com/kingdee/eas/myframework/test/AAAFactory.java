package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class AAAFactory
{
    private AAAFactory()
    {
    }
    public static com.kingdee.eas.myframework.test.IAAA getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAA)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("9AA38859") ,com.kingdee.eas.myframework.test.IAAA.class);
    }
    
    public static com.kingdee.eas.myframework.test.IAAA getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAA)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("9AA38859") ,com.kingdee.eas.myframework.test.IAAA.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.test.IAAA getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAA)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("9AA38859"));
    }
    public static com.kingdee.eas.myframework.test.IAAA getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.test.IAAA)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("9AA38859"));
    }
}