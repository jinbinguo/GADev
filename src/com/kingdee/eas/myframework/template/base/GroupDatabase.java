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

public class GroupDatabase extends DataBase implements IGroupDatabase
{
    public GroupDatabase()
    {
        super();
        registerInterface(IGroupDatabase.class, this);
    }
    public GroupDatabase(Context ctx)
    {
        super(ctx);
        registerInterface(IGroupDatabase.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("61E00983");
    }
    private GroupDatabaseController getController() throws BOSException
    {
        return (GroupDatabaseController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public GroupDatabaseInfo getGroupDatabaseInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getGroupDatabaseInfo(getContext(), pk);
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
    public GroupDatabaseInfo getGroupDatabaseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getGroupDatabaseInfo(getContext(), pk, selector);
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
    public GroupDatabaseInfo getGroupDatabaseInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getGroupDatabaseInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public GroupDatabaseCollection getGroupDatabaseCollection() throws BOSException
    {
        try {
            return getController().getGroupDatabaseCollection(getContext());
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
    public GroupDatabaseCollection getGroupDatabaseCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getGroupDatabaseCollection(getContext(), view);
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
    public GroupDatabaseCollection getGroupDatabaseCollection(String oql) throws BOSException
    {
        try {
            return getController().getGroupDatabaseCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}