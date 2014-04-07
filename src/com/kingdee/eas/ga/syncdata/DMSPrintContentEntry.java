package com.kingdee.eas.ga.syncdata;

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
import com.kingdee.eas.ga.syncdata.app.*;

public class DMSPrintContentEntry extends SimpleBizBillEntry implements IDMSPrintContentEntry
{
    public DMSPrintContentEntry()
    {
        super();
        registerInterface(IDMSPrintContentEntry.class, this);
    }
    public DMSPrintContentEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IDMSPrintContentEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("5C9DBE63");
    }
    private DMSPrintContentEntryController getController() throws BOSException
    {
        return (DMSPrintContentEntryController)getBizController();
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public DMSPrintContentEntryInfo getDMSPrintContentEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSPrintContentEntryInfo(getContext(), pk);
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
    public DMSPrintContentEntryInfo getDMSPrintContentEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSPrintContentEntryInfo(getContext(), pk, selector);
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
    public DMSPrintContentEntryInfo getDMSPrintContentEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSPrintContentEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public DMSPrintContentEntryCollection getDMSPrintContentEntryCollection() throws BOSException
    {
        try {
            return getController().getDMSPrintContentEntryCollection(getContext());
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
    public DMSPrintContentEntryCollection getDMSPrintContentEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getDMSPrintContentEntryCollection(getContext(), view);
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
    public DMSPrintContentEntryCollection getDMSPrintContentEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getDMSPrintContentEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}