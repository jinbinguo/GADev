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

public class PurOrder extends CoreBillBase implements IPurOrder
{
    public PurOrder()
    {
        super();
        registerInterface(IPurOrder.class, this);
    }
    public PurOrder(Context ctx)
    {
        super(ctx);
        registerInterface(IPurOrder.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("696867B9");
    }
    private PurOrderController getController() throws BOSException
    {
        return (PurOrderController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PurOrderCollection getPurOrderCollection() throws BOSException
    {
        try {
            return getController().getPurOrderCollection(getContext());
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
    public PurOrderCollection getPurOrderCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurOrderCollection(getContext(), view);
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
    public PurOrderCollection getPurOrderCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurOrderCollection(getContext(), oql);
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
    public PurOrderInfo getPurOrderInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurOrderInfo(getContext(), pk);
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
    public PurOrderInfo getPurOrderInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurOrderInfo(getContext(), pk, selector);
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
    public PurOrderInfo getPurOrderInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurOrderInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *审核-User defined method
     *@param model model
     */
    public void audit(PurOrderInfo model) throws BOSException, EASBizException
    {
        try {
            getController().audit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *反审核-User defined method
     *@param model model
     */
    public void unAudit(PurOrderInfo model) throws BOSException, EASBizException
    {
        try {
            getController().unAudit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}