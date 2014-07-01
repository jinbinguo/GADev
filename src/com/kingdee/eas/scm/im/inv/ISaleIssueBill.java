package com.kingdee.eas.scm.im.inv;

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

public interface ISaleIssueBill extends IInvBillBase
{
    public SaleIssueBillInfo getSaleIssueBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SaleIssueBillInfo getSaleIssueBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SaleIssueBillInfo getSaleIssueBillInfo(String oql) throws BOSException, EASBizException;
    public SaleIssueBillCollection getSaleIssueBillCollection() throws BOSException;
    public SaleIssueBillCollection getSaleIssueBillCollection(EntityViewInfo view) throws BOSException;
    public SaleIssueBillCollection getSaleIssueBillCollection(String oql) throws BOSException;
    public HashMap splitBillByWrittenOffQty(String[] idList, HashMap param) throws BOSException, EASBizException;
    public void checkPreReceived(Set saleOrderIds) throws BOSException, EASBizException;
    public IObjectCollection createNewAuditBillBySettle(Map settledEntriesMap, Set srcBillIdSet) throws BOSException, EASBizException;
    public void deleteBillByUnSettle(Set billIdSet) throws BOSException, EASBizException;
    public void changePrice(IObjectPK pk, String description, List list) throws BOSException, EASBizException;
    public String checkChangePrice(IObjectPK pk) throws BOSException, EASBizException;
}