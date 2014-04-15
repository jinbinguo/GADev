package com.kingdee.eas.basedata.master.material;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class MaterialInventoryFactory
{
    private MaterialInventoryFactory()
    {
    }
    public static com.kingdee.eas.basedata.master.material.IMaterialInventory getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventory)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("557E499F") ,com.kingdee.eas.basedata.master.material.IMaterialInventory.class);
    }
    
    public static com.kingdee.eas.basedata.master.material.IMaterialInventory getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventory)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("557E499F") ,com.kingdee.eas.basedata.master.material.IMaterialInventory.class, objectCtx);
    }
    public static com.kingdee.eas.basedata.master.material.IMaterialInventory getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventory)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("557E499F"));
    }
    public static com.kingdee.eas.basedata.master.material.IMaterialInventory getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.basedata.master.material.IMaterialInventory)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("557E499F"));
    }
}