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
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.template.base.SimpleBizBill;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.ga.basedata.app.*;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;

public class CustomerDiscount extends SimpleBizBill implements ICustomerDiscount
{
    public CustomerDiscount()
    {
        super();
        registerInterface(ICustomerDiscount.class, this);
    }
    public CustomerDiscount(Context ctx)
    {
        super(ctx);
        registerInterface(ICustomerDiscount.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("E9F46AD0");
    }
    private CustomerDiscountController getController() throws BOSException
    {
        return (CustomerDiscountController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public CustomerDiscountInfo getCustomerDiscountInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerDiscountInfo(getContext(), pk);
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
    public CustomerDiscountInfo getCustomerDiscountInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerDiscountInfo(getContext(), pk, selector);
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
    public CustomerDiscountInfo getCustomerDiscountInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getCustomerDiscountInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public CustomerDiscountCollection getCustomerDiscountCollection() throws BOSException
    {
        try {
            return getController().getCustomerDiscountCollection(getContext());
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
    public CustomerDiscountCollection getCustomerDiscountCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getCustomerDiscountCollection(getContext(), view);
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
    public CustomerDiscountCollection getCustomerDiscountCollection(String oql) throws BOSException
    {
        try {
            return getController().getCustomerDiscountCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}