package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.eas.framework.CoreBillEntryBase;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.ICoreBillEntryBase;
import com.kingdee.eas.myframework.template.base.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;

public class SimpleBizBillEntry extends CoreBillEntryBase implements ISimpleBizBillEntry
{
    public SimpleBizBillEntry()
    {
        super();
        registerInterface(ISimpleBizBillEntry.class, this);
    }
    public SimpleBizBillEntry(Context ctx)
    {
        super(ctx);
        registerInterface(ISimpleBizBillEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("F41D60E1");
    }
    private SimpleBizBillEntryController getController() throws BOSException
    {
        return (SimpleBizBillEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public SimpleBizBillEntryInfo getSimpleBizBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleBizBillEntryInfo(getContext(), pk);
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
    public SimpleBizBillEntryInfo getSimpleBizBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleBizBillEntryInfo(getContext(), pk, selector);
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
    public SimpleBizBillEntryInfo getSimpleBizBillEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleBizBillEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public SimpleBizBillEntryCollection getSimpleBizBillEntryCollection() throws BOSException
    {
        try {
            return getController().getSimpleBizBillEntryCollection(getContext());
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
    public SimpleBizBillEntryCollection getSimpleBizBillEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSimpleBizBillEntryCollection(getContext(), view);
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
    public SimpleBizBillEntryCollection getSimpleBizBillEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getSimpleBizBillEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}