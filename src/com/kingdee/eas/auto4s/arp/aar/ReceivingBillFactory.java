package com.kingdee.eas.auto4s.arp.aar;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class ReceivingBillFactory
{
    private ReceivingBillFactory()
    {
    }
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBill getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBill)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("E97C32F4") ,com.kingdee.eas.auto4s.arp.aar.IReceivingBill.class);
    }
    
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBill getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBill)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("E97C32F4") ,com.kingdee.eas.auto4s.arp.aar.IReceivingBill.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBill getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBill)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("E97C32F4"));
    }
    public static com.kingdee.eas.auto4s.arp.aar.IReceivingBill getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.arp.aar.IReceivingBill)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("E97C32F4"));
    }
}