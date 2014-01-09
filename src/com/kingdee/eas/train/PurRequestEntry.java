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

public class PurRequestEntry extends CoreBillEntryBase implements IPurRequestEntry
{
    public PurRequestEntry()
    {
        super();
        registerInterface(IPurRequestEntry.class, this);
    }
    public PurRequestEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IPurRequestEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("D6C84858");
    }
    private PurRequestEntryController getController() throws BOSException
    {
        return (PurRequestEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public PurRequestEntryInfo getPurRequestEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurRequestEntryInfo(getContext(), pk);
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
    public PurRequestEntryInfo getPurRequestEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurRequestEntryInfo(getContext(), pk, selector);
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
    public PurRequestEntryInfo getPurRequestEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurRequestEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PurRequestEntryCollection getPurRequestEntryCollection() throws BOSException
    {
        try {
            return getController().getPurRequestEntryCollection(getContext());
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
    public PurRequestEntryCollection getPurRequestEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurRequestEntryCollection(getContext(), view);
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
    public PurRequestEntryCollection getPurRequestEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurRequestEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}