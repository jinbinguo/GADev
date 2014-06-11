package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.syncdata.app.*;

public class DMSSyncFacade extends AbstractBizCtrl implements IDMSSyncFacade
{
    public DMSSyncFacade()
    {
        super();
        registerInterface(IDMSSyncFacade.class, this);
    }
    public DMSSyncFacade(Context ctx)
    {
        super(ctx);
        registerInterface(IDMSSyncFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("1829B218");
    }
    private DMSSyncFacadeController getController() throws BOSException
    {
        return (DMSSyncFacadeController)getBizController();
    }
    /**
     *引入客户档案资料-User defined method
     *@param maxRecords 每次处理的最大记录数，避免数据量很大时，提取速度慢，采用分批提取
     */
    public void ImportCustProfiles(int maxRecords) throws BOSException
    {
        try {
            getController().ImportCustProfiles(getContext(), maxRecords);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *引入车型资料-User defined method
     *@param maxRecords 每次处理的最大记录数，避免数据量很大时，提取速度慢，采用分批提取
     */
    public void ImportVehicleTypes(int maxRecords) throws BOSException
    {
        try {
            getController().ImportVehicleTypes(getContext(), maxRecords);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *引入车辆档案资料，包括车辆所属维修中心一起处理-User defined method
     *@param maxRecords 每次处理的最大记录数，避免数据量很大时，提取速度慢，采用分批提取
     */
    public void ImportVehicleProfiles(int maxRecords) throws BOSException
    {
        try {
            getController().ImportVehicleProfiles(getContext(), maxRecords);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}