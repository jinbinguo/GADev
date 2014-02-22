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
     *ȡ����-System defined method
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
     *ȡ����-System defined method
     *@param view ȡ����
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
     *ȡ����-System defined method
     *@param oql ȡ����
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
     *ȡֵ-System defined method
     *@param pk ȡֵ
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
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
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
     *ȡֵ-System defined method
     *@param oql ȡֵ
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
     *���-User defined method
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
     *�����-User defined method
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
     *�ύ��������-User defined method
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
     *�տ�-User defined method
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
     *ȡ���տ�-User defined method
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
     *�տ�ڼ�װδ�տ��ʱ������ÿ��ҳǩ������������-User defined method
     *@param customerId �ͻ�ID
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
     *��Ա�տ���������տ�����-User defined method
     *@param customerId �ͻ�Id
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
     *��ȡ��ʼ������-User defined method
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
     *��ȡ��׼�տ�б�-User defined method
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
     *��ȡ��׼�տ�б�-User defined method
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
     *��ȡ�տ-User defined method
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
     *�������-User defined method
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
     *���������-User defined method
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
     *��ȡά�޽����¼�б�-User defined method
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
     *��ȡ�տ������б�-User defined method
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
     *�����Ż�ȡ����Ϣ-User defined method
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
     *��ȡ-User defined method
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
     *ͨ����Ա��ȡ����-User defined method
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
     *����Ա���Ƿ���������-User defined method
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