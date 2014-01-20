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
import com.kingdee.eas.framework.CoreBillBase;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;

public class AAA extends CoreBillBase implements IAAA
{
    public AAA()
    {
        super();
        registerInterface(IAAA.class, this);
    }
    public AAA(Context ctx)
    {
        super(ctx);
        registerInterface(IAAA.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("9AA38859");
    }
    private AAAController getController() throws BOSException
    {
        return (AAAController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public AAACollection getAAACollection() throws BOSException
    {
        try {
            return getController().getAAACollection(getContext());
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
    public AAACollection getAAACollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getAAACollection(getContext(), view);
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
    public AAACollection getAAACollection(String oql) throws BOSException
    {
        try {
            return getController().getAAACollection(getContext(), oql);
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
    public AAAInfo getAAAInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getAAAInfo(getContext(), pk);
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
    public AAAInfo getAAAInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getAAAInfo(getContext(), pk, selector);
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
    public AAAInfo getAAAInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getAAAInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}