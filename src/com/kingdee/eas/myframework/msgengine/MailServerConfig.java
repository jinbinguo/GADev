package com.kingdee.eas.myframework.msgengine;

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
import com.kingdee.eas.myframework.template.base.ISimpleDatabase;
import com.kingdee.eas.myframework.msgengine.app.*;

public class MailServerConfig extends SimpleDatabase implements IMailServerConfig
{
    public MailServerConfig()
    {
        super();
        registerInterface(IMailServerConfig.class, this);
    }
    public MailServerConfig(Context ctx)
    {
        super(ctx);
        registerInterface(IMailServerConfig.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("BFC18CB5");
    }
    private MailServerConfigController getController() throws BOSException
    {
        return (MailServerConfigController)getBizController();
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public MailServerConfigInfo getMailServerConfigInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getMailServerConfigInfo(getContext(), pk);
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
    public MailServerConfigInfo getMailServerConfigInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getMailServerConfigInfo(getContext(), pk, selector);
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
    public MailServerConfigInfo getMailServerConfigInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getMailServerConfigInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public MailServerConfigCollection getMailServerConfigCollection() throws BOSException
    {
        try {
            return getController().getMailServerConfigCollection(getContext());
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
    public MailServerConfigCollection getMailServerConfigCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getMailServerConfigCollection(getContext(), view);
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
    public MailServerConfigCollection getMailServerConfigCollection(String oql) throws BOSException
    {
        try {
            return getController().getMailServerConfigCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}