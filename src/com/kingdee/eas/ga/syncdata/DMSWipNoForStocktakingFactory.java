package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class DMSWipNoForStocktakingFactory
{
    private DMSWipNoForStocktakingFactory()
    {
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("18AD6E0D") ,com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking.class);
    }
    
    public static com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("18AD6E0D") ,com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking.class, objectCtx);
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("18AD6E0D"));
    }
    public static com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.syncdata.IDMSWipNoForStocktaking)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("18AD6E0D"));
    }
}