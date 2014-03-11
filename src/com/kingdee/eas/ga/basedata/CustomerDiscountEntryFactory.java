package com.kingdee.eas.ga.basedata;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class CustomerDiscountEntryFactory
{
    private CustomerDiscountEntryFactory()
    {
    }
    public static com.kingdee.eas.ga.basedata.ICustomerDiscountEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscountEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("772DEF82") ,com.kingdee.eas.ga.basedata.ICustomerDiscountEntry.class);
    }
    
    public static com.kingdee.eas.ga.basedata.ICustomerDiscountEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscountEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("772DEF82") ,com.kingdee.eas.ga.basedata.ICustomerDiscountEntry.class, objectCtx);
    }
    public static com.kingdee.eas.ga.basedata.ICustomerDiscountEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscountEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("772DEF82"));
    }
    public static com.kingdee.eas.ga.basedata.ICustomerDiscountEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.ga.basedata.ICustomerDiscountEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("772DEF82"));
    }
}