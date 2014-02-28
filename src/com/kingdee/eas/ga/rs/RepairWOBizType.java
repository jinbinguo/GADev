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

public class RepairWOBizType extends SimpleDatabase implements IRepairWOBizType
{
    public RepairWOBizType()
    {
        super();
        registerInterface(IRepairWOBizType.class, this);
    }
    public RepairWOBizType(Context ctx)
    {
        super(ctx);
        registerInterface(IRepairWOBizType.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("8CCB6ADD");
    }
    private RepairWOBizTypeController getController() throws BOSException
    {
        return (RepairWOBizTypeController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWOBizTypeInfo(getContext(), pk);
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
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWOBizTypeInfo(getContext(), pk, selector);
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
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWOBizTypeInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection() throws BOSException
    {
        try {
            return getController().getRepairWOBizTypeCollection(getContext());
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
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getRepairWOBizTypeCollection(getContext(), view);
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
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection(String oql) throws BOSException
    {
        try {
            return getController().getRepairWOBizTypeCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}