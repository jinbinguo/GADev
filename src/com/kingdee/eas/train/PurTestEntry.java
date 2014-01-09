package com.kingdee.eas.train;

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
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.train.app.*;

public class PurTestEntry extends CoreBillEntryBase implements IPurTestEntry
{
    public PurTestEntry()
    {
        super();
        registerInterface(IPurTestEntry.class, this);
    }
    public PurTestEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IPurTestEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("FAC291EB");
    }
    private PurTestEntryController getController() throws BOSException
    {
        return (PurTestEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public PurTestEntryInfo getPurTestEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurTestEntryInfo(getContext(), pk);
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
    public PurTestEntryInfo getPurTestEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurTestEntryInfo(getContext(), pk, selector);
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
    public PurTestEntryInfo getPurTestEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurTestEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PurTestEntryCollection getPurTestEntryCollection() throws BOSException
    {
        try {
            return getController().getPurTestEntryCollection(getContext());
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
    public PurTestEntryCollection getPurTestEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurTestEntryCollection(getContext(), view);
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
    public PurTestEntryCollection getPurTestEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurTestEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}