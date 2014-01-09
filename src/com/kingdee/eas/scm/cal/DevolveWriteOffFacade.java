package com.kingdee.eas.scm.cal;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.scm.common.IManualWriteoffCommonFacade;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.scm.cal.info.ReturnInfo;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.scm.cal.info.DevolveWriteOffResultInfo;
import com.kingdee.bos.framework.*;
import java.util.HashMap;
import com.kingdee.eas.scm.cal.app.*;
import com.kingdee.eas.scm.common.ManualWriteoffCommonFacade;

public class DevolveWriteOffFacade extends ManualWriteoffCommonFacade implements IDevolveWriteOffFacade
{
    public DevolveWriteOffFacade()
    {
        super();
        registerInterface(IDevolveWriteOffFacade.class, this);
    }
    public DevolveWriteOffFacade(Context ctx)
    {
        super(ctx);
        registerInterface(IDevolveWriteOffFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("31A35008");
    }
    private DevolveWriteOffFacadeController getController() throws BOSException
    {
        return (DevolveWriteOffFacadeController)getBizController();
    }
    /**
     *ί�ⷢ�Ϻ���-User defined method
     *@param info ���Ϻ�����Ϣ
     *@return
     */
    public ReturnInfo devolveWriteOff(DevolveWriteOffResultInfo info) throws BOSException, EASBizException
    {
        try {
            return getController().devolveWriteOff(getContext(), info);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���Ϸ�����-User defined method
     *@param ids ��������¼ID����
     *@return
     */
    public boolean inverseWriteOff(String[] ids) throws BOSException, EASBizException
    {
        try {
            return getController().inverseWriteOff(getContext(), ids);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��д���ϵ�������-User defined method
     *@param costAdjustId ��������
     *@return
     */
    public boolean updateMaterialReqBill(String costAdjustId) throws BOSException, EASBizException
    {
        try {
            return getController().updateMaterialReqBill(getContext(), costAdjustId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����ί�����-User defined method
     *@param hashInfo ���Ϻ�����Ϣ
     *@return
     */
    public ReturnInfo batchDevolveWriteOff(HashMap hashInfo) throws BOSException, EASBizException
    {
        try {
            return getController().batchDevolveWriteOff(getContext(), hashInfo);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}