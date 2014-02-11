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

public class CustomerAccount extends SimpleDatabase implements ICustomerAccount
{
    public CustomerAccount()
    {
        super();
        registerInterface(ICustomerAccount.class, this);
    }
    public CustomerAccount(Context ctx)
    {
        super(ctx);
        registerInterface(ICustomerAccount.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("8B9E1F44");
    }
    private CustomerAccountController getController() throws BOSException
    {
        return (CustomerAccountController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public CustomerAccountInfo getCustomerAccountInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerAccountInfo(getContext(), pk);
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
    public CustomerAccountInfo getCustomerAccountInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerAccountInfo(getContext(), pk, selector);
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
    public CustomerAccountInfo getCustomerAccountInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerAccountInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public CustomerAccountCollection getCustomerAccountCollection() throws BOSException
    {
        try {
            return getController().getCustomerAccountCollection(getContext());
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
    public CustomerAccountCollection getCustomerAccountCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getCustomerAccountCollection(getContext(), view);
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
    public CustomerAccountCollection getCustomerAccountCollection(String oql) throws BOSException
    {
        try {
            return getController().getCustomerAccountCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}