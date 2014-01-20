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
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.template.base.SimpleBizBill;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.syncdata.app.*;
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;

public class DMSInOutQuery extends SimpleBizBill implements IDMSInOutQuery
{
    public DMSInOutQuery()
    {
        super();
        registerInterface(IDMSInOutQuery.class, this);
    }
    public DMSInOutQuery(Context ctx)
    {
        super(ctx);
        registerInterface(IDMSInOutQuery.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("9FEBF162");
    }
    private DMSInOutQueryController getController() throws BOSException
    {
        return (DMSInOutQueryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public DMSInOutQueryInfo getDMSInOutQueryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSInOutQueryInfo(getContext(), pk);
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
    public DMSInOutQueryInfo getDMSInOutQueryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSInOutQueryInfo(getContext(), pk, selector);
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
    public DMSInOutQueryInfo getDMSInOutQueryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSInOutQueryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public DMSInOutQueryCollection getDMSInOutQueryCollection() throws BOSException
    {
        try {
            return getController().getDMSInOutQueryCollection(getContext());
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
    public DMSInOutQueryCollection getDMSInOutQueryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getDMSInOutQueryCollection(getContext(), view);
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
    public DMSInOutQueryCollection getDMSInOutQueryCollection(String oql) throws BOSException
    {
        try {
            return getController().getDMSInOutQueryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}