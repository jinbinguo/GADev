package com.kingdee.eas.scm.cal;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DevolveWriteOffFacadeFactory
{
    private DevolveWriteOffFacadeFactory()
    {
    }
    public static com.kingdee.eas.scm.cal.IDevolveWriteOffFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.scm.cal.IDevolveWriteOffFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("31A35008") ,com.kingdee.eas.scm.cal.IDevolveWriteOffFacade.class);
    }
    
    public static com.kingdee.eas.scm.cal.IDevolveWriteOffFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.scm.cal.IDevolveWriteOffFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("31A35008") ,com.kingdee.eas.scm.cal.IDevolveWriteOffFacade.class, objectCtx);
    }
    public static com.kingdee.eas.scm.cal.IDevolveWriteOffFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.scm.cal.IDevolveWriteOffFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("31A35008"));
    }
    public static com.kingdee.eas.scm.cal.IDevolveWriteOffFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.scm.cal.IDevolveWriteOffFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("31A35008"));
    }
}