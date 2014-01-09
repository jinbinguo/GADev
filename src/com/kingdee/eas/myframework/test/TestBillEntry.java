package com.kingdee.eas.myframework.test;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.myframework.test.app.*;
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

public class TestBillEntry extends SimpleBizBillEntry implements ITestBillEntry
{
    public TestBillEntry()
    {
        super();
        registerInterface(ITestBillEntry.class, this);
    }
    public TestBillEntry(Context ctx)
    {
        super(ctx);
        registerInterface(ITestBillEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("9E6F1651");
    }
    private TestBillEntryController getController() throws BOSException
    {
        return (TestBillEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public TestBillEntryInfo getTestBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getTestBillEntryInfo(getContext(), pk);
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
    public TestBillEntryInfo getTestBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getTestBillEntryInfo(getContext(), pk, selector);
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
    public TestBillEntryInfo getTestBillEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getTestBillEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public TestBillEntryCollection getTestBillEntryCollection() throws BOSException
    {
        try {
            return getController().getTestBillEntryCollection(getContext());
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
    public TestBillEntryCollection getTestBillEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getTestBillEntryCollection(getContext(), view);
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
    public TestBillEntryCollection getTestBillEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getTestBillEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}