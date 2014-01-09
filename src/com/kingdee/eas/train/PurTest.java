package com.kingdee.eas.train;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBillBase;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.train.app.*;
import com.kingdee.eas.framework.ICoreBillBase;

public class PurTest extends CoreBillBase implements IPurTest
{
    public PurTest()
    {
        super();
        registerInterface(IPurTest.class, this);
    }
    public PurTest(Context ctx)
    {
        super(ctx);
        registerInterface(IPurTest.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("D1DC2AC7");
    }
    private PurTestController getController() throws BOSException
    {
        return (PurTestController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public PurTestCollection getPurTestCollection() throws BOSException
    {
        try {
            return getController().getPurTestCollection(getContext());
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
    public PurTestCollection getPurTestCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurTestCollection(getContext(), view);
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
    public PurTestCollection getPurTestCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurTestCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public PurTestInfo getPurTestInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurTestInfo(getContext(), pk);
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
    public PurTestInfo getPurTestInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurTestInfo(getContext(), pk, selector);
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
    public PurTestInfo getPurTestInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurTestInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *审核-User defined method
     *@param model model
     */
    public void audit(PurTestInfo model) throws BOSException
    {
        try {
            getController().audit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *test1-User defined method
     */
    public void test1() throws BOSException
    {
        try {
            getController().test1(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *test2-User defined method
     */
    public void test2() throws BOSException
    {
        try {
            getController().test2(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *test3-User defined method
     */
    public void test3() throws BOSException
    {
        try {
            getController().test3(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *test4-User defined method
     */
    public void test4() throws BOSException
    {
        try {
            getController().test4(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *test5-User defined method
     */
    public void test5() throws BOSException
    {
        try {
            getController().test5(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}