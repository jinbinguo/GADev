package com.kingdee.eas.train;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.eas.framework.CoreBillEntryBase;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.ICoreBillEntryBase;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.train.app.*;

public class PurInWarehsEntry extends CoreBillEntryBase implements IPurInWarehsEntry
{
    public PurInWarehsEntry()
    {
        super();
        registerInterface(IPurInWarehsEntry.class, this);
    }
    public PurInWarehsEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IPurInWarehsEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("5A863810");
    }
    private PurInWarehsEntryController getController() throws BOSException
    {
        return (PurInWarehsEntryController)getBizController();
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getPurInWarehsEntryInfo(getContext(), pk);
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
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getPurInWarehsEntryInfo(getContext(), pk, selector);
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
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPurInWarehsEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection() throws BOSException
    {
        try {
            return getController().getPurInWarehsEntryCollection(getContext());
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
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getPurInWarehsEntryCollection(getContext(), view);
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
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getPurInWarehsEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}