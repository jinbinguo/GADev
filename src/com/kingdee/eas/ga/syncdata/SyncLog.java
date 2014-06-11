package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBillBase;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.eas.ga.syncdata.app.*;

public class SyncLog extends CoreBillBase implements ISyncLog
{
    public SyncLog()
    {
        super();
        registerInterface(ISyncLog.class, this);
    }
    public SyncLog(Context ctx)
    {
        super(ctx);
        registerInterface(ISyncLog.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("2BA1BFC2");
    }
    private SyncLogController getController() throws BOSException
    {
        return (SyncLogController)getBizController();
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public SyncLogCollection getSyncLogCollection() throws BOSException
    {
        try {
            return getController().getSyncLogCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param view ȡ����
     *@return
     */
    public SyncLogCollection getSyncLogCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSyncLogCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param oql ȡ����
     *@return
     */
    public SyncLogCollection getSyncLogCollection(String oql) throws BOSException
    {
        try {
            return getController().getSyncLogCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public SyncLogInfo getSyncLogInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSyncLogInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
     *@return
     */
    public SyncLogInfo getSyncLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSyncLogInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param oql ȡֵ
     *@return
     */
    public SyncLogInfo getSyncLogInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSyncLogInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}