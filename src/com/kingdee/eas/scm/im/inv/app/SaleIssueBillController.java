package com.kingdee.eas.scm.im.inv.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

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
import com.kingdee.eas.scm.im.inv.SaleIssueBillInfo;
import com.kingdee.eas.scm.im.inv.SaleIssueBillCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface SaleIssueBillController extends InvBillBaseController
{
    public SaleIssueBillInfo getSaleIssueBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public SaleIssueBillInfo getSaleIssueBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public SaleIssueBillInfo getSaleIssueBillInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public SaleIssueBillCollection getSaleIssueBillCollection(Context ctx) throws BOSException, RemoteException;
    public SaleIssueBillCollection getSaleIssueBillCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public SaleIssueBillCollection getSaleIssueBillCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public HashMap splitBillByWrittenOffQty(Context ctx, String[] idList, HashMap param) throws BOSException, EASBizException, RemoteException;
    public void checkPreReceived(Context ctx, Set saleOrderIds) throws BOSException, EASBizException, RemoteException;
    public IObjectCollection createNewAuditBillBySettle(Context ctx, Map settledEntriesMap, Set srcBillIdSet) throws BOSException, EASBizException, RemoteException;
    public void deleteBillByUnSettle(Context ctx, Set billIdSet) throws BOSException, EASBizException, RemoteException;
    public void changePrice(Context ctx, IObjectPK pk, String description, List list) throws BOSException, EASBizException, RemoteException;
    public String checkChangePrice(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
}