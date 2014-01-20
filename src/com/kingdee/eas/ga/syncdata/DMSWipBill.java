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

public class DMSWipBill extends SimpleBizBill implements IDMSWipBill
{
    public DMSWipBill()
    {
        super();
        registerInterface(IDMSWipBill.class, this);
    }
    public DMSWipBill(Context ctx)
    {
        super(ctx);
        registerInterface(IDMSWipBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("71CA40E2");
    }
    private DMSWipBillController getController() throws BOSException
    {
        return (DMSWipBillController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public DMSWipBillInfo getDMSWipBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSWipBillInfo(getContext(), pk);
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
    public DMSWipBillInfo getDMSWipBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSWipBillInfo(getContext(), pk, selector);
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
    public DMSWipBillInfo getDMSWipBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSWipBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public DMSWipBillCollection getDMSWipBillCollection() throws BOSException
    {
        try {
            return getController().getDMSWipBillCollection(getContext());
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
    public DMSWipBillCollection getDMSWipBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getDMSWipBillCollection(getContext(), view);
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
    public DMSWipBillCollection getDMSWipBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getDMSWipBillCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}