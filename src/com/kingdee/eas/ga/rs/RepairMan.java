package com.kingdee.eas.ga.rs;

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
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.template.base.SimpleBizBill;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.rs.app.*;
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;

public class RepairMan extends SimpleBizBill implements IRepairMan
{
    public RepairMan()
    {
        super();
        registerInterface(IRepairMan.class, this);
    }
    public RepairMan(Context ctx)
    {
        super(ctx);
        registerInterface(IRepairMan.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("40C83D22");
    }
    private RepairManController getController() throws BOSException
    {
        return (RepairManController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public RepairManInfo getRepairManInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairManInfo(getContext(), pk);
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
    public RepairManInfo getRepairManInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairManInfo(getContext(), pk, selector);
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
    public RepairManInfo getRepairManInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairManInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public RepairManCollection getRepairManCollection() throws BOSException
    {
        try {
            return getController().getRepairManCollection(getContext());
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
    public RepairManCollection getRepairManCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getRepairManCollection(getContext(), view);
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
    public RepairManCollection getRepairManCollection(String oql) throws BOSException
    {
        try {
            return getController().getRepairManCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}