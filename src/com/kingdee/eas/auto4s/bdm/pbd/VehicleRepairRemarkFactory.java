package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class VehicleRepairRemarkFactory
{
    private VehicleRepairRemarkFactory()
    {
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("73ACC552") ,com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark.class);
    }
    
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("73ACC552") ,com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("73ACC552"));
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("73ACC552"));
    }
}