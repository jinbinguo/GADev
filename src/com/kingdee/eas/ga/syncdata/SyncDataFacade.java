package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.vo.ServerReturnInfo;
import com.kingdee.eas.ga.syncdata.app.*;

public class SyncDataFacade extends AbstractBizCtrl implements ISyncDataFacade
{
    public SyncDataFacade()
    {
        super();
        registerInterface(ISyncDataFacade.class, this);
    }
    public SyncDataFacade(Context ctx)
    {
        super(ctx);
        registerInterface(ISyncDataFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("76F81EA6");
    }
    private SyncDataFacadeController getController() throws BOSException
    {
        return (SyncDataFacadeController)getBizController();
    }
    /**
     *同步Wip单-User defined method
     *@param serviceOrgInfo 公司
     *@param dmsWipBillId DMSWip单号ID
     *@return
     */
    public ServerReturnInfo syncWipBill(ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsWipBillId) throws BOSException, EASBizException
    {
        try {
            return getController().syncWipBill(getContext(), serviceOrgInfo, dmsWipBillId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *同步交易查询-User defined method
     *@param serviceOrgInfo 公司
     *@param dmsTradeInqireId DMS交易查询ID
     *@return
     */
    public ServerReturnInfo syncTradeInquire(ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsTradeInqireId) throws BOSException, EASBizException
    {
        try {
            return getController().syncTradeInquire(getContext(), serviceOrgInfo, dmsTradeInqireId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *自动同步-User defined method
     */
    public void autosync() throws BOSException, EASBizException
    {
        try {
            getController().autosync(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}