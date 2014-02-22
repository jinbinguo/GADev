package com.kingdee.eas.auto4s.arp.aar.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.auto4s.arp.aar.ReceivingBillEntryCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.auto4s.vip.mb.CardSecurityInfo;
import com.kingdee.eas.auto4s.arp.aar.ReceivingBillCollection;
import java.util.List;
import com.kingdee.eas.auto4s.vip.mb.CardCashAccountInfo;
import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.util.Map;
import com.kingdee.eas.framework.app.CoreBillBaseController;
import com.kingdee.eas.auto4s.vip.ct.ClubActPayCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import java.util.Set;
import com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo;
import com.kingdee.eas.auto4s.bdm.vip.AccountCFGInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.auto4s.vip.mb.CardInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.auto4s.vam.AgentServiceCollection;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface ReceivingBillController extends CoreBillBaseController
{
    public ReceivingBillCollection getReceivingBillCollection(Context ctx) throws BOSException, RemoteException;
    public ReceivingBillCollection getReceivingBillCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public ReceivingBillCollection getReceivingBillCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public ReceivingBillInfo getReceivingBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public ReceivingBillInfo getReceivingBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public ReceivingBillInfo getReceivingBillInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void audit(Context ctx, ReceivingBillInfo model) throws BOSException, EASBizException, RemoteException;
    public void autiAudit(Context ctx, ReceivingBillInfo model) throws BOSException, EASBizException, RemoteException;
    public void commitSettle(Context ctx, ReceivingBillInfo model) throws BOSException, EASBizException, RemoteException;
    public void rec(Context ctx, ReceivingBillEntryCollection entrys) throws BOSException, EASBizException, RemoteException;
    public void cancelRec(Context ctx, ReceivingBillEntryCollection entrys) throws BOSException, EASBizException, RemoteException;
    public String getKdPanelsLoadCount(Context ctx, String customerId) throws BOSException, EASBizException, RemoteException;
    public ClubActPayCollection getClubReceivingList(Context ctx, String customerId) throws BOSException, EASBizException, RemoteException;
    public CardCashAccountInfo getCash(Context ctx, CardInfo cardInfo, AccountCFGInfo accountCFG) throws BOSException, EASBizException, RemoteException;
    public Map getCreateNewData(Context ctx, Map map) throws BOSException, EASBizException, RemoteException;
    public CoreBillBaseCollection getEASReceivingBillCollection(Context ctx, List idList) throws BOSException, EASBizException, RemoteException;
    public CoreBillBaseCollection getEASReceivingBillCollection(Context ctx, String id) throws BOSException, EASBizException, RemoteException;
    public CoreBillBaseCollection getCollectionByEntrysIds(Context ctx, List entryIds) throws BOSException, EASBizException, RemoteException;
    public void batchAudit(Context ctx, List ids) throws BOSException, EASBizException, RemoteException;
    public void batchFAudit(Context ctx, List ids) throws BOSException, EASBizException, RemoteException;
    public Map getRSEntryCollection(Context ctx, Map map) throws BOSException, EASBizException, RemoteException;
    public List getRBTypeList(Context ctx, List list, Set set) throws BOSException, EASBizException, RemoteException;
    public Map getCardInfoByPysicalNum(Context ctx, String physicalNum) throws BOSException, EASBizException, RemoteException;
    public AgentServiceCollection getAgentServiceCollection(Context ctx, AgentServiceCollection coll) throws BOSException, EASBizException, RemoteException;
    public AdminOrgUnitInfo getAdminOrgByPersonId(Context ctx, String personId) throws BOSException, EASBizException, RemoteException;
    public CardSecurityInfo checkCardHavePassWord(Context ctx, CardInfo card) throws BOSException, EASBizException, RemoteException;
}