package com.kingdee.eas.basedata.master.material;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class MaterialInventoryMaterialLocFactory
{
    private MaterialInventoryMaterialLocFactory()
    {
    }
    public static com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("928F7517") ,com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc.class);
    }
    
    public static com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("928F7517") ,com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc.class, objectCtx);
    }
    public static com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("928F7517"));
    }
    public static com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventoryMaterialLoc)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("928F7517"));
    }
}