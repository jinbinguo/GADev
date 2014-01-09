package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.DataBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.myframework.template.base.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.IDataBase;

public class SimpleDatabase extends DataBase implements ISimpleDatabase
{
    public SimpleDatabase()
    {
        super();
        registerInterface(ISimpleDatabase.class, this);
    }
    public SimpleDatabase(Context ctx)
    {
        super(ctx);
        registerInterface(ISimpleDatabase.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("D082D604");
    }
    private SimpleDatabaseController getController() throws BOSException
    {
        return (SimpleDatabaseController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public SimpleDatabaseInfo getSimpleDatabaseInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleDatabaseInfo(getContext(), pk);
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
    public SimpleDatabaseInfo getSimpleDatabaseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleDatabaseInfo(getContext(), pk, selector);
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
    public SimpleDatabaseInfo getSimpleDatabaseInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleDatabaseInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public SimpleDatabaseCollection getSimpleDatabaseCollection() throws BOSException
    {
        try {
            return getController().getSimpleDatabaseCollection(getContext());
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
    public SimpleDatabaseCollection getSimpleDatabaseCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSimpleDatabaseCollection(getContext(), view);
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
    public SimpleDatabaseCollection getSimpleDatabaseCollection(String oql) throws BOSException
    {
        try {
            return getController().getSimpleDatabaseCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}