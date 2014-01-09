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
     *委外发料核销-User defined method
     *@param info 发料核销信息
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
     *发料反核销-User defined method
     *@param ids 反核销记录ID数组
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
     *反写发料调整单号-User defined method
     *@param costAdjustId 调整单号
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
     *批量委外核销-User defined method
     *@param hashInfo 发料核销信息
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