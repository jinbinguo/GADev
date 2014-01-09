package com.kingdee.eas.myframework.test;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.myframework.test.app.*;
import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.myframework.template.base.SimpleDatabase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.ISimpleDatabase;

public class TestDatabase extends SimpleDatabase implements ITestDatabase
{
    public TestDatabase()
    {
        super();
        registerInterface(ITestDatabase.class, this);
    }
    public TestDatabase(Context ctx)
    {
        super(ctx);
        registerInterface(ITestDatabase.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("74940F15");
    }
    private TestDatabaseController getController() throws BOSException
    {
        return (TestDatabaseController)getBizController();
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public TestDatabaseInfo getTestDatabaseInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getTestDatabaseInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
     *@return
     */
    public TestDatabaseInfo getTestDatabaseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getTestDatabaseInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param oql ȡֵ
     *@return
     */
    public TestDatabaseInfo getTestDatabaseInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getTestDatabaseInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public TestDatabaseCollection getTestDatabaseCollection() throws BOSException
    {
        try {
            return getController().getTestDatabaseCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param view ȡ����
     *@return
     */
    public TestDatabaseCollection getTestDatabaseCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getTestDatabaseCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param oql ȡ����
     *@return
     */
    public TestDatabaseCollection getTestDatabaseCollection(String oql) throws BOSException
    {
        try {
            return getController().getTestDatabaseCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}