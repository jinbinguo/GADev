package com.kingdee.eas.myframework.msgengine;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class MailServerConfigFactory
{
    private MailServerConfigFactory()
    {
    }
    public static com.kingdee.eas.myframework.msgengine.IMailServerConfig getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.myframework.msgengine.IMailServerConfig)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("BFC18CB5") ,com.kingdee.eas.myframework.msgengine.IMailServerConfig.class);
    }
    
    public static com.kingdee.eas.myframework.msgengine.IMailServerConfig getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.myframework.msgengine.IMailServerConfig)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("BFC18CB5") ,com.kingdee.eas.myframework.msgengine.IMailServerConfig.class, objectCtx);
    }
    public static com.kingdee.eas.myframework.msgengine.IMailServerConfig getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.myframework.msgengine.IMailServerConfig)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("BFC18CB5"));
    }
    public static com.kingdee.eas.myframework.msgengine.IMailServerConfig getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.myframework.msgengine.IMailServerConfig)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("BFC18CB5"));
    }
}