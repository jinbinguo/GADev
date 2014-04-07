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
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.template.base.SimpleBizBill;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.syncdata.app.*;
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;

public class DMSPrintContent extends SimpleBizBill implements IDMSPrintContent
{
    public DMSPrintContent()
    {
        super();
        registerInterface(IDMSPrintContent.class, this);
    }
    public DMSPrintContent(Context ctx)
    {
        super(ctx);
        registerInterface(IDMSPrintContent.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("B165134F");
    }
    private DMSPrintContentController getController() throws BOSException
    {
        return (DMSPrintContentController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public DMSPrintContentInfo getDMSPrintContentInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSPrintContentInfo(getContext(), pk);
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
    public DMSPrintContentInfo getDMSPrintContentInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSPrintContentInfo(getContext(), pk, selector);
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
    public DMSPrintContentInfo getDMSPrintContentInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getDMSPrintContentInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public DMSPrintContentCollection getDMSPrintContentCollection() throws BOSException
    {
        try {
            return getController().getDMSPrintContentCollection(getContext());
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
    public DMSPrintContentCollection getDMSPrintContentCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getDMSPrintContentCollection(getContext(), view);
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
    public DMSPrintContentCollection getDMSPrintContentCollection(String oql) throws BOSException
    {
        try {
            return getController().getDMSPrintContentCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}