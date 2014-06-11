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
     *����ͻ���������-User defined method
     *@param maxRecords ÿ�δ��������¼���������������ܴ�ʱ����ȡ�ٶ��������÷�����ȡ
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
     *���복������-User defined method
     *@param maxRecords ÿ�δ��������¼���������������ܴ�ʱ����ȡ�ٶ��������÷�����ȡ
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
     *���복���������ϣ�������������ά������һ����-User defined method
     *@param maxRecords ÿ�δ��������¼���������������ܴ�ʱ����ȡ�ٶ��������÷�����ȡ
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