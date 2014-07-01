package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;
import java.util.HashMap;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.framework.*;
import java.util.List;
import com.kingdee.eas.scm.im.inv.app.*;

public class SaleIssueBill extends InvBillBase implements ISaleIssueBill
{
    public SaleIssueBill()
    {
        super();
        registerInterface(ISaleIssueBill.class, this);
    }
    public SaleIssueBill(Context ctx)
    {
        super(ctx);
        registerInterface(ISaleIssueBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("CC3E933B");
    }
    private SaleIssueBillController getController() throws BOSException
    {
        return (SaleIssueBillController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public SaleIssueBillInfo getSaleIssueBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSaleIssueBillInfo(getContext(), pk);
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
    public SaleIssueBillInfo getSaleIssueBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSaleIssueBillInfo(getContext(), pk, selector);
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
    public SaleIssueBillInfo getSaleIssueBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSaleIssueBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public SaleIssueBillCollection getSaleIssueBillCollection() throws BOSException
    {
        try {
            return getController().getSaleIssueBillCollection(getContext());
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
    public SaleIssueBillCollection getSaleIssueBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSaleIssueBillCollection(getContext(), view);
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
    public SaleIssueBillCollection getSaleIssueBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getSaleIssueBillCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *按核销数量分单-User defined method
     *@param idList idList
     *@param param param
     *@return
     */
    public HashMap splitBillByWrittenOffQty(String[] idList, HashMap param) throws BOSException, EASBizException
    {
        try {
            return getController().splitBillByWrittenOffQty(getContext(), idList, param);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *校验销售订单预收款，前台根据异常和参数给出提示-User defined method
     *@param saleOrderIds 当前单据核心单为销售订单，并且非冲销单时的id列表
     */
    public void checkPreReceived(Set saleOrderIds) throws BOSException, EASBizException
    {
        try {
            getController().checkPreReceived(getContext(), saleOrderIds);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *结算出库生成审核的销售出库单，由销售组对即时库存执行完结算后调用的接口-User defined method
     *@param settledEntriesMap 结算分录的Map(key为销售出库单分录id,value为本次结算数量)
     *@param srcBillIdSet 源单据ID集合
     *@return
     */
    public IObjectCollection createNewAuditBillBySettle(Map settledEntriesMap, Set srcBillIdSet) throws BOSException, EASBizException
    {
        try {
            return getController().createNewAuditBillBySettle(getContext(), settledEntriesMap, srcBillIdSet);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *反审核结算出库的销售出库单-User defined method
     *@param billIdSet 单据ID集合
     */
    public void deleteBillByUnSettle(Set billIdSet) throws BOSException, EASBizException
    {
        try {
            getController().deleteBillByUnSettle(getContext(), billIdSet);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *价格变更-User defined method
     *@param pk pk
     *@param description description
     *@param list list
     */
    public void changePrice(IObjectPK pk, String description, List list) throws BOSException, EASBizException
    {
        try {
            getController().changePrice(getContext(), pk, description, list);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *检查该销售出库单是否能进行价格变更， 不能的话返回错误信息-User defined method
     *@param pk pk
     *@return
     */
    public String checkChangePrice(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().checkChangePrice(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}