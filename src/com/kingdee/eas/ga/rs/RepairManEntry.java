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
import com.kingdee.eas.myframework.template.base.ISimpleBizBillEntry;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.template.base.SimpleBizBillEntry;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.rs.app.*;

public class RepairManEntry extends SimpleBizBillEntry implements IRepairManEntry
{
    public RepairManEntry()
    {
        super();
        registerInterface(IRepairManEntry.class, this);
    }
    public RepairManEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IRepairManEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("51DAC070");
    }
    private RepairManEntryController getController() throws BOSException
    {
        return (RepairManEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public RepairManEntryInfo getRepairManEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairManEntryInfo(getContext(), pk);
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
    public RepairManEntryInfo getRepairManEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairManEntryInfo(getContext(), pk, selector);
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
    public RepairManEntryInfo getRepairManEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairManEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public RepairManEntryCollection getRepairManEntryCollection() throws BOSException
    {
        try {
            return getController().getRepairManEntryCollection(getContext());
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
    public RepairManEntryCollection getRepairManEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getRepairManEntryCollection(getContext(), view);
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
    public RepairManEntryCollection getRepairManEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getRepairManEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}