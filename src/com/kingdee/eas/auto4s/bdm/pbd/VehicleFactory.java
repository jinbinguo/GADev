package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class VehicleFactory
{
    private VehicleFactory()
    {
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicle getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicle)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("9E9FFF45") ,com.kingdee.eas.auto4s.bdm.pbd.IVehicle.class);
    }
    
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicle getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicle)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("9E9FFF45") ,com.kingdee.eas.auto4s.bdm.pbd.IVehicle.class, objectCtx);
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicle getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicle)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("9E9FFF45"));
    }
    public static com.kingdee.eas.auto4s.bdm.pbd.IVehicle getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.IVehicle)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("9E9FFF45"));
    }
}