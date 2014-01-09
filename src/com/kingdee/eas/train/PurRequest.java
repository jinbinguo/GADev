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

public class PurRequest extends CoreBillBase implements IPurRequest
{
    public PurRequest()
    {
        super();
        registerInterface(IPurRequest.class, this);
    }
    public PurRequest(Context ctx)
    {
        super(ctx);
        registerInterface(IPurRequest.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("3A2F643A");
    }
    private PurRequestController getController() throws BOSException
    {
        return (PurRequestController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PurRequestCollection getPurRequestCollection() throws BOSException
    {
        try {
            return getController().getPurRequestCollection(getContext());
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
    public PurRequestCollection getPurRequestCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurRequestCollection(getContext(), view);
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
    public PurRequestCollection getPurRequestCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurRequestCollection(getContext(), oql);
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
    public PurRequestInfo getPurRequestInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurRequestInfo(getContext(), pk);
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
    public PurRequestInfo getPurRequestInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurRequestInfo(getContext(), pk, selector);
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
    public PurRequestInfo getPurRequestInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurRequestInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *审核-User defined method
     *@param model model
     */
    public void audit(PurRequestInfo model) throws BOSException, EASBizException
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
    public void unAudit(PurRequestInfo model) throws BOSException, EASBizException
    {
        try {
            getController().unAudit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}