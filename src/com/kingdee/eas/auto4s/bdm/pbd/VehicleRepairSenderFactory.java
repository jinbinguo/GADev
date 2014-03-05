package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class VehicleRepairSenderFactory
{
    private VehicleRepairSenderFactory()
    {
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("75621C07") ,com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender.class);
    }
    
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("75621C07") ,com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("75621C07"));
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairSender)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("75621C07"));
    }
}