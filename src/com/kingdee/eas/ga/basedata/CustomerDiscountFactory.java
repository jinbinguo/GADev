package com.kingdee.eas.ga.basedata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class CustomerDiscountFactory
{
    private CustomerDiscountFactory()
    {
    }
    public static com.kingdee.eas.ga.basedata.ICustomerDiscount getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscount)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("E9F46AD0") ,com.kingdee.eas.ga.basedata.ICustomerDiscount.class);
    }
    
    public static com.kingdee.eas.ga.basedata.ICustomerDiscount getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscount)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("E9F46AD0") ,com.kingdee.eas.ga.basedata.ICustomerDiscount.class, objectCtx);
    }
    public static com.kingdee.eas.ga.basedata.ICustomerDiscount getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscount)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("E9F46AD0"));
    }
    public static com.kingdee.eas.ga.basedata.ICustomerDiscount getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscount)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("E9F46AD0"));
    }
}