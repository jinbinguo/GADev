package com.kingdee.eas.auto4s.arp.aar;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.auto4s.vip.mb.CardSecurityInfo;
import java.util.List;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.eas.auto4s.vip.mb.CardCashAccountInfo;
import com.kingdee.eas.auto4s.arp.aar.app.*;
import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.util.Map;
import com.kingdee.eas.auto4s.vip.ct.ClubActPayCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import java.util.Set;
import com.kingdee.eas.framework.CoreBillBase;
import com.kingdee.eas.auto4s.bdm.vip.AccountCFGInfo;
import com.kingdee.eas.auto4s.vip.mb.CardInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.auto4s.vam.AgentServiceCollection;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;

public class ReceivingBill extends CoreBillBase implements IReceivingBill
{
    public ReceivingBill()
    {
        super();
        registerInterface(IReceivingBill.class, this);
    }
    public ReceivingBill(Context ctx)
    {
        super(ctx);
        registerInterface(IReceivingBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("E97C32F4");
    }
    private ReceivingBillController getController() throws BOSException
    {
        return (ReceivingBillController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public ReceivingBillCollection getReceivingBillCollection() throws BOSException
    {
        try {
            return getController().getReceivingBillCollection(getContext());
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
    public ReceivingBillCollection getReceivingBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getReceivingBillCollection(getContext(), view);
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
    public ReceivingBillCollection getReceivingBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getReceivingBillCollection(getContext(), oql);
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
    public ReceivingBillInfo getReceivingBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getReceivingBillInfo(getContext(), pk);
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
    public ReceivingBillInfo getReceivingBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getReceivingBillInfo(getContext(), pk, selector);
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
    public ReceivingBillInfo getReceivingBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getReceivingBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *审核-User defined method
     *@param model model
     */
    public void audit(ReceivingBillInfo model) throws BOSException, EASBizException
    {
        try {
            getController().audit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *反审核-User defined method
     *@param model model
     */
    public void autiAudit(ReceivingBillInfo model) throws BOSException, EASBizException
    {
        try {
            getController().autiAudit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *提交结算中心-User defined method
     *@param model model
     */
    public void commitSettle(ReceivingBillInfo model) throws BOSException, EASBizException
    {
        try {
            getController().commitSettle(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *收款-User defined method
     *@param entrys entrys
     */
    public void rec(ReceivingBillEntryCollection entrys) throws BOSException, EASBizException
    {
        try {
            getController().rec(getContext(), entrys);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取消收款-User defined method
     *@param entrys entrys
     */
    public void cancelRec(ReceivingBillEntryCollection entrys) throws BOSException, EASBizException
    {
        try {
            getController().cancelRec(getContext(), entrys);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *收款单在加装未收款单据时，计算每个页签加载数据条数-User defined method
     *@param customerId 客户ID
     *@return
     */
    public String getKdPanelsLoadCount(String customerId) throws BOSException, EASBizException
    {
        try {
            return getController().getKdPanelsLoadCount(getContext(), customerId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *会员收款包括八种收款类型-User defined method
     *@param customerId 客户Id
     *@return
     */
    public ClubActPayCollection getClubReceivingList(String customerId) throws BOSException, EASBizException
    {
        try {
            return getController().getClubReceivingList(getContext(), customerId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getCash-User defined method
     *@param cardInfo cardInfo
     *@param accountCFG accountCFG
     *@return
     */
    public CardCashAccountInfo getCash(CardInfo cardInfo, AccountCFGInfo accountCFG) throws BOSException, EASBizException
    {
        try {
            return getController().getCash(getContext(), cardInfo, accountCFG);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取初始化数据-User defined method
     *@param map map
     *@return
     */
    public Map getCreateNewData(Map map) throws BOSException, EASBizException
    {
        try {
            return getController().getCreateNewData(getContext(), map);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取标准收款单列表-User defined method
     *@param idList idList
     *@return
     */
    public CoreBillBaseCollection getEASReceivingBillCollection(List idList) throws BOSException, EASBizException
    {
        try {
            return getController().getEASReceivingBillCollection(getContext(), idList);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取标准收款单列表-User defined method
     *@param id id
     *@return
     */
    public CoreBillBaseCollection getEASReceivingBillCollection(String id) throws BOSException, EASBizException
    {
        try {
            return getController().getEASReceivingBillCollection(getContext(), id);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取收款单-User defined method
     *@param entryIds entryIds
     *@return
     */
    public CoreBillBaseCollection getCollectionByEntrysIds(List entryIds) throws BOSException, EASBizException
    {
        try {
            return getController().getCollectionByEntrysIds(getContext(), entryIds);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *批量审核-User defined method
     *@param ids ids
     */
    public void batchAudit(List ids) throws BOSException, EASBizException
    {
        try {
            getController().batchAudit(getContext(), ids);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *批量反审核-User defined method
     *@param ids ids
     */
    public void batchFAudit(List ids) throws BOSException, EASBizException
    {
        try {
            getController().batchFAudit(getContext(), ids);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取维修结算分录列表-User defined method
     *@param map map
     *@return
     */
    public Map getRSEntryCollection(Map map) throws BOSException, EASBizException
    {
        try {
            return getController().getRSEntryCollection(getContext(), map);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取收款类型列表-User defined method
     *@param list list
     *@param set set
     *@return
     */
    public List getRBTypeList(List list, Set set) throws BOSException, EASBizException
    {
        try {
            return getController().getRBTypeList(getContext(), list, set);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *物理卡号获取卡信息-User defined method
     *@param physicalNum physicalNum
     *@return
     */
    public Map getCardInfoByPysicalNum(String physicalNum) throws BOSException, EASBizException
    {
        try {
            return getController().getCardInfoByPysicalNum(getContext(), physicalNum);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取-User defined method
     *@param coll coll
     *@return
     */
    public AgentServiceCollection getAgentServiceCollection(AgentServiceCollection coll) throws BOSException, EASBizException
    {
        try {
            return getController().getAgentServiceCollection(getContext(), coll);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *通过人员获取部门-User defined method
     *@param personId personId
     *@return
     */
    public AdminOrgUnitInfo getAdminOrgByPersonId(String personId) throws BOSException, EASBizException
    {
        try {
            return getController().getAdminOrgByPersonId(getContext(), personId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *检查会员卡是否设置密码-User defined method
     *@param card card
     *@return
     */
    public CardSecurityInfo checkCardHavePassWord(CardInfo card) throws BOSException, EASBizException
    {
        try {
            return getController().checkCardHavePassWord(getContext(), card);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}