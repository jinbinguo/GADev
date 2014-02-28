package com.kingdee.eas.ga.rs;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

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
import com.kingdee.eas.ga.rs.app.*;
import com.kingdee.eas.myframework.template.base.ISimpleDatabase;

public class RepairPackage extends SimpleDatabase implements IRepairPackage
{
    public RepairPackage()
    {
        super();
        registerInterface(IRepairPackage.class, this);
    }
    public RepairPackage(Context ctx)
    {
        super(ctx);
        registerInterface(IRepairPackage.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("988AA80E");
    }
    private RepairPackageController getController() throws BOSException
    {
        return (RepairPackageController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public RepairPackageInfo getRepairPackageInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairPackageInfo(getContext(), pk);
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
    public RepairPackageInfo getRepairPackageInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairPackageInfo(getContext(), pk, selector);
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
    public RepairPackageInfo getRepairPackageInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairPackageInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public RepairPackageCollection getRepairPackageCollection() throws BOSException
    {
        try {
            return getController().getRepairPackageCollection(getContext());
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
    public RepairPackageCollection getRepairPackageCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getRepairPackageCollection(getContext(), view);
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
    public RepairPackageCollection getRepairPackageCollection(String oql) throws BOSException
    {
        try {
            return getController().getRepairPackageCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}