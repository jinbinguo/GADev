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
     *ͬ��Wip��-User defined method
     *@param serviceOrgInfo ��˾
     *@param dmsWipBillPk DMSWip����ID
     *@return
     */
    public ServerReturnInfo syncWipBill(ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsWipBillPk) throws BOSException, EASBizException
    {
        try {
            return getController().syncWipBill(getContext(), serviceOrgInfo, dmsWipBillPk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͬ�����ײ�ѯ-User defined method
     *@param serviceOrgInfo ��˾
     *@param dmsTradeInqirePk DMS���ײ�ѯID
     *@return
     */
    public ServerReturnInfo syncTradeInquire(ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsTradeInqirePk) throws BOSException, EASBizException
    {
        try {
            return getController().syncTradeInquire(getContext(), serviceOrgInfo, dmsTradeInqirePk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͬ����ӡ���-User defined method
     *@param serviceOrgUnitInfo ��˾
     *@param dmsPrintContentPK ��ӡ����ID
     *@return
     */
    public ServerReturnInfo syncPrintContent(ServiceOrgUnitInfo serviceOrgUnitInfo, IObjectPK dmsPrintContentPK) throws BOSException, EASBizException
    {
        try {
            return getController().syncPrintContent(getContext(), serviceOrgUnitInfo, dmsPrintContentPK);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�Զ�ͬ��-User defined method
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