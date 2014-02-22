package com.kingdee.eas.auto4s.arp.aar;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class ReceivingBillContentFactory
{
    private ReceivingBillContentFactory()
    {
    }
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("4C561EE5") ,com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent.class);
    }
    
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("4C561EE5") ,com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("4C561EE5"));
    }
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBillContent)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("4C561EE5"));
    }
}