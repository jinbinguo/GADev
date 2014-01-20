package com.kingdee.eas.myframework.test;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.myframework.test.app.*;
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

public class AAAEntry extends CoreBillEntryBase implements IAAAEntry
{
    public AAAEntry()
    {
        super();
        registerInterface(IAAAEntry.class, this);
    }
    public AAAEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IAAAEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("4230DF99");
    }
    private AAAEntryController getController() throws BOSException
    {
        return (AAAEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public AAAEntryInfo getAAAEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getAAAEntryInfo(getContext(), pk);
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
    public AAAEntryInfo getAAAEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getAAAEntryInfo(getContext(), pk, selector);
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
    public AAAEntryInfo getAAAEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getAAAEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public AAAEntryCollection getAAAEntryCollection() throws BOSException
    {
        try {
            return getController().getAAAEntryCollection(getContext());
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
    public AAAEntryCollection getAAAEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getAAAEntryCollection(getContext(), view);
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
    public AAAEntryCollection getAAAEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getAAAEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}