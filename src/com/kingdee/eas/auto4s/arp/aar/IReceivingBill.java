package com.kingdee.eas.auto4s.arp.aar;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;
import com.kingdee.eas.auto4s.vip.ct.ClubActPayCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.bos.util.*;
import com.kingdee.eas.auto4s.bdm.vip.AccountCFGInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.auto4s.vip.mb.CardSecurityInfo;
import com.kingdee.eas.auto4s.vip.mb.CardInfo;
import com.kingdee.eas.auto4s.vam.AgentServiceCollection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import java.util.List;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.eas.auto4s.vip.mb.CardCashAccountInfo;

public interface IReceivingBill extends ICoreBillBase
{
    public ReceivingBillCollection getReceivingBillCollection() throws BOSException;
    public ReceivingBillCollection getReceivingBillCollection(EntityViewInfo view) throws BOSException;
    public ReceivingBillCollection getReceivingBillCollection(String oql) throws BOSException;
    public ReceivingBillInfo getReceivingBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public ReceivingBillInfo getReceivingBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public ReceivingBillInfo getReceivingBillInfo(String oql) throws BOSException, EASBizException;
    public void audit(ReceivingBillInfo model) throws BOSException, EASBizException;
    public void autiAudit(ReceivingBillInfo model) throws BOSException, EASBizException;
    public void commitSettle(ReceivingBillInfo model) throws BOSException, EASBizException;
    public void rec(ReceivingBillEntryCollection entrys) throws BOSException, EASBizException;
    public void cancelRec(ReceivingBillEntryCollection entrys) throws BOSException, EASBizException;
    public String getKdPanelsLoadCount(String customerId) throws BOSException, EASBizException;
    public ClubActPayCollection getClubReceivingList(String customerId) throws BOSException, EASBizException;
    public CardCashAccountInfo getCash(CardInfo cardInfo, AccountCFGInfo accountCFG) throws BOSException, EASBizException;
    public Map getCreateNewData(Map map) throws BOSException, EASBizException;
    public CoreBillBaseCollection getEASReceivingBillCollection(List idList) throws BOSException, EASBizException;
    public CoreBillBaseCollection getEASReceivingBillCollection(String id) throws BOSException, EASBizException;
    public CoreBillBaseCollection getCollectionByEntrysIds(List entryIds) throws BOSException, EASBizException;
    public void batchAudit(List ids) throws BOSException, EASBizException;
    public void batchFAudit(List ids) throws BOSException, EASBizException;
    public Map getRSEntryCollection(Map map) throws BOSException, EASBizException;
    public List getRBTypeList(List list, Set set) throws BOSException, EASBizException;
    public Map getCardInfoByPysicalNum(String physicalNum) throws BOSException, EASBizException;
    public AgentServiceCollection getAgentServiceCollection(AgentServiceCollection coll) throws BOSException, EASBizException;
    public AdminOrgUnitInfo getAdminOrgByPersonId(String personId) throws BOSException, EASBizException;
    public CardSecurityInfo checkCardHavePassWord(CardInfo card) throws BOSException, EASBizException;
}