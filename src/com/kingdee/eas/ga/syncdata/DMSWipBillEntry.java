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
import com.kingdee.eas.myframework.template.base.ISimpleBizBillEntry;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.template.base.SimpleBizBillEntry;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.syncdata.app.*;

public class DMSWipBillEntry extends SimpleBizBillEntry implements IDMSWipBillEntry
{
    public DMSWipBillEntry()
    {
        super();
        registerInterface(IDMSWipBillEntry.class, this);
    }
    public DMSWipBillEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IDMSWipBillEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("D84514B0");
    }
    private DMSWipBillEntryController getController() throws BOSException
    {
        return (DMSWipBillEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSWipBillEntryInfo(getContext(), pk);
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
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSWipBillEntryInfo(getContext(), pk, selector);
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
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSWipBillEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection() throws BOSException
    {
        try {
            return getController().getDMSWipBillEntryCollection(getContext());
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
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getDMSWipBillEntryCollection(getContext(), view);
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
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getDMSWipBillEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}