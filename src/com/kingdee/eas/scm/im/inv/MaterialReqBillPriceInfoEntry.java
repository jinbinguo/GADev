package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.scm.common.ISCMBillEntryBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.scm.common.SCMBillEntryBase;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.scm.im.inv.app.*;

public class MaterialReqBillPriceInfoEntry extends SCMBillEntryBase implements IMaterialReqBillPriceInfoEntry
{
    public MaterialReqBillPriceInfoEntry()
    {
        super();
        registerInterface(IMaterialReqBillPriceInfoEntry.class, this);
    }
    public MaterialReqBillPriceInfoEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IMaterialReqBillPriceInfoEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("97A53E9B");
    }
    private MaterialReqBillPriceInfoEntryController getController() throws BOSException
    {
        return (MaterialReqBillPriceInfoEntryController)getBizController();
    }
    /**
     *getValue-System defined method
     *@param pk pk
     *@return
     */
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillPriceInfoEntryInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getValue-System defined method
     *@param pk pk
     *@param selector selector
     *@return
     */
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillPriceInfoEntryInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getValue-System defined method
     *@param oql oql
     *@return
     */
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillPriceInfoEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getCollection-System defined method
     *@return
     */
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection() throws BOSException
    {
        try {
            return getController().getMaterialReqBillPriceInfoEntryCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getCollection-System defined method
     *@param view view
     *@return
     */
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getMaterialReqBillPriceInfoEntryCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getCollection-System defined method
     *@param oql oql
     *@return
     */
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getMaterialReqBillPriceInfoEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}