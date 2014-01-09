package com.kingdee.eas.train;

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
import com.kingdee.eas.train.app.*;
import com.kingdee.eas.framework.ICoreBillBase;

public class PurInWarehs extends CoreBillBase implements IPurInWarehs
{
    public PurInWarehs()
    {
        super();
        registerInterface(IPurInWarehs.class, this);
    }
    public PurInWarehs(Context ctx)
    {
        super(ctx);
        registerInterface(IPurInWarehs.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("13788182");
    }
    private PurInWarehsController getController() throws BOSException
    {
        return (PurInWarehsController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PurInWarehsCollection getPurInWarehsCollection() throws BOSException
    {
        try {
            return getController().getPurInWarehsCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public PurInWarehsCollection getPurInWarehsCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurInWarehsCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public PurInWarehsCollection getPurInWarehsCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurInWarehsCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public PurInWarehsInfo getPurInWarehsInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurInWarehsInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public PurInWarehsInfo getPurInWarehsInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurInWarehsInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public PurInWarehsInfo getPurInWarehsInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurInWarehsInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}