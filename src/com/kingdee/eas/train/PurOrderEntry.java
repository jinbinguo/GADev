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

public class PurOrderEntry extends CoreBillEntryBase implements IPurOrderEntry
{
    public PurOrderEntry()
    {
        super();
        registerInterface(IPurOrderEntry.class, this);
    }
    public PurOrderEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IPurOrderEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("3A709C39");
    }
    private PurOrderEntryController getController() throws BOSException
    {
        return (PurOrderEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public PurOrderEntryInfo getPurOrderEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurOrderEntryInfo(getContext(), pk);
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
    public PurOrderEntryInfo getPurOrderEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurOrderEntryInfo(getContext(), pk, selector);
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
    public PurOrderEntryInfo getPurOrderEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurOrderEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PurOrderEntryCollection getPurOrderEntryCollection() throws BOSException
    {
        try {
            return getController().getPurOrderEntryCollection(getContext());
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
    public PurOrderEntryCollection getPurOrderEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurOrderEntryCollection(getContext(), view);
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
    public PurOrderEntryCollection getPurOrderEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurOrderEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}