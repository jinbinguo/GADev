package com.kingdee.eas.ga.basedata;

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
import com.kingdee.eas.myframework.template.base.ISimpleBizBillEntry;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.template.base.SimpleBizBillEntry;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.ga.basedata.app.*;
import com.kingdee.bos.framework.*;

public class CustomerDiscountEntry extends SimpleBizBillEntry implements ICustomerDiscountEntry
{
    public CustomerDiscountEntry()
    {
        super();
        registerInterface(ICustomerDiscountEntry.class, this);
    }
    public CustomerDiscountEntry(Context ctx)
    {
        super(ctx);
        registerInterface(ICustomerDiscountEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("772DEF82");
    }
    private CustomerDiscountEntryController getController() throws BOSException
    {
        return (CustomerDiscountEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public CustomerDiscountEntryInfo getCustomerDiscountEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerDiscountEntryInfo(getContext(), pk);
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
    public CustomerDiscountEntryInfo getCustomerDiscountEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerDiscountEntryInfo(getContext(), pk, selector);
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
    public CustomerDiscountEntryInfo getCustomerDiscountEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerDiscountEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public CustomerDiscountEntryCollection getCustomerDiscountEntryCollection() throws BOSException
    {
        try {
            return getController().getCustomerDiscountEntryCollection(getContext());
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
    public CustomerDiscountEntryCollection getCustomerDiscountEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getCustomerDiscountEntryCollection(getContext(), view);
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
    public CustomerDiscountEntryCollection getCustomerDiscountEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getCustomerDiscountEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}