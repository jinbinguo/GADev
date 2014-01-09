package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.scm.im.inv.app.*;

public class MaterialReqBillEntry extends InvBillBaseEntry implements IMaterialReqBillEntry
{
    public MaterialReqBillEntry()
    {
        super();
        registerInterface(IMaterialReqBillEntry.class, this);
    }
    public MaterialReqBillEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IMaterialReqBillEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("11774BB4");
    }
    private MaterialReqBillEntryController getController() throws BOSException
    {
        return (MaterialReqBillEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public MaterialReqBillEntryInfo getMaterialReqBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillEntryInfo(getContext(), pk, selector);
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
    public MaterialReqBillEntryInfo getMaterialReqBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillEntryInfo(getContext(), pk);
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
    public MaterialReqBillEntryInfo getMaterialReqBillEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public MaterialReqBillEntryCollection getMaterialReqBillEntryCollection() throws BOSException
    {
        try {
            return getController().getMaterialReqBillEntryCollection(getContext());
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
    public MaterialReqBillEntryCollection getMaterialReqBillEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getMaterialReqBillEntryCollection(getContext(), view);
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
    public MaterialReqBillEntryCollection getMaterialReqBillEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getMaterialReqBillEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}