package com.kingdee.eas.myframework.test;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.myframework.test.app.*;
import java.lang.String;
import com.kingdee.eas.myframework.template.base.IGroupDatabaseTree;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.GroupDatabaseTree;

public class TestGroupTree extends GroupDatabaseTree implements ITestGroupTree
{
    public TestGroupTree()
    {
        super();
        registerInterface(ITestGroupTree.class, this);
    }
    public TestGroupTree(Context ctx)
    {
        super(ctx);
        registerInterface(ITestGroupTree.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("DB103C63");
    }
    private TestGroupTreeController getController() throws BOSException
    {
        return (TestGroupTreeController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public TestGroupTreeInfo getTestGroupTreeInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getTestGroupTreeInfo(getContext(), pk);
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
    public TestGroupTreeInfo getTestGroupTreeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getTestGroupTreeInfo(getContext(), pk, selector);
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
    public TestGroupTreeInfo getTestGroupTreeInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getTestGroupTreeInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public TestGroupTreeCollection getTestGroupTreeCollection() throws BOSException
    {
        try {
            return getController().getTestGroupTreeCollection(getContext());
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
    public TestGroupTreeCollection getTestGroupTreeCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getTestGroupTreeCollection(getContext(), view);
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
    public TestGroupTreeCollection getTestGroupTreeCollection(String oql) throws BOSException
    {
        try {
            return getController().getTestGroupTreeCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}