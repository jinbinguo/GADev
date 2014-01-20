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

public class DMSInOutQueryEntry extends SimpleBizBillEntry implements IDMSInOutQueryEntry
{
    public DMSInOutQueryEntry()
    {
        super();
        registerInterface(IDMSInOutQueryEntry.class, this);
    }
    public DMSInOutQueryEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IDMSInOutQueryEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("861DB430");
    }
    private DMSInOutQueryEntryController getController() throws BOSException
    {
        return (DMSInOutQueryEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSInOutQueryEntryInfo(getContext(), pk);
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
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSInOutQueryEntryInfo(getContext(), pk, selector);
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
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSInOutQueryEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection() throws BOSException
    {
        try {
            return getController().getDMSInOutQueryEntryCollection(getContext());
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
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getDMSInOutQueryEntryCollection(getContext(), view);
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
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getDMSInOutQueryEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}