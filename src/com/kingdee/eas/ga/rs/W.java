package com.kingdee.eas.ga.rs;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.myframework.template.base.SimpleDatabase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.rs.app.*;
import com.kingdee.eas.myframework.template.base.ISimpleDatabase;

public class W extends SimpleDatabase implements IW
{
    public W()
    {
        super();
        registerInterface(IW.class, this);
    }
    public W(Context ctx)
    {
        super(ctx);
        registerInterface(IW.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("9871DC0C");
    }
    private WController getController() throws BOSException
    {
        return (WController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public WInfo getWInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getWInfo(getContext(), pk);
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
    public WInfo getWInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getWInfo(getContext(), pk, selector);
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
    public WInfo getWInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getWInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public WCollection getWCollection() throws BOSException
    {
        try {
            return getController().getWCollection(getContext());
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
    public WCollection getWCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getWCollection(getContext(), view);
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
    public WCollection getWCollection(String oql) throws BOSException
    {
        try {
            return getController().getWCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}