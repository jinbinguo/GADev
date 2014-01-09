package com.kingdee.eas.scm.cal;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.scm.im.inv.IInvBillBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.scm.im.inv.InvBillBase;
import com.kingdee.eas.scm.cal.app.*;

public class CostAdjustBill extends InvBillBase implements ICostAdjustBill
{
    public CostAdjustBill()
    {
        super();
        registerInterface(ICostAdjustBill.class, this);
    }
    public CostAdjustBill(Context ctx)
    {
        super(ctx);
        registerInterface(ICostAdjustBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("8FA62986");
    }
    private CostAdjustBillController getController() throws BOSException
    {
        return (CostAdjustBillController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public CostAdjustBillInfo getCostAdjustBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getCostAdjustBillInfo(getContext(), pk);
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
    public CostAdjustBillInfo getCostAdjustBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getCostAdjustBillInfo(getContext(), pk, selector);
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
    public CostAdjustBillInfo getCostAdjustBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getCostAdjustBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public CostAdjustBillCollection getCostAdjustBillCollection() throws BOSException
    {
        try {
            return getController().getCostAdjustBillCollection(getContext());
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
    public CostAdjustBillCollection getCostAdjustBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getCostAdjustBillCollection(getContext(), view);
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
    public CostAdjustBillCollection getCostAdjustBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getCostAdjustBillCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *判断凭证是由应付费用发票与成本调整单合并生成的凭证-User defined method
     *@param billIDs billIDs
     *@return
     */
    public boolean IsJoinWithOtherBill(String[] billIDs) throws BOSException
    {
        try {
            return getController().IsJoinWithOtherBill(getContext(), billIDs);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}