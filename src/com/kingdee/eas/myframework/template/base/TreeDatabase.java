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
import com.kingdee.eas.framework.ITreeBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.myframework.template.base.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.TreeBase;

public class TreeDatabase extends TreeBase implements ITreeDatabase
{
    public TreeDatabase()
    {
        super();
        registerInterface(ITreeDatabase.class, this);
    }
    public TreeDatabase(Context ctx)
    {
        super(ctx);
        registerInterface(ITreeDatabase.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("672D2DB0");
    }
    private TreeDatabaseController getController() throws BOSException
    {
        return (TreeDatabaseController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public TreeDatabaseInfo getTreeDatabaseInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getTreeDatabaseInfo(getContext(), pk);
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
    public TreeDatabaseInfo getTreeDatabaseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getTreeDatabaseInfo(getContext(), pk, selector);
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
    public TreeDatabaseInfo getTreeDatabaseInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getTreeDatabaseInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public TreeDatabaseCollection getTreeDatabaseCollection() throws BOSException
    {
        try {
            return getController().getTreeDatabaseCollection(getContext());
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
    public TreeDatabaseCollection getTreeDatabaseCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getTreeDatabaseCollection(getContext(), view);
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
    public TreeDatabaseCollection getTreeDatabaseCollection(String oql) throws BOSException
    {
        try {
            return getController().getTreeDatabaseCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}