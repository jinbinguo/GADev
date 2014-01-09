package com.kingdee.eas.scm.cal;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.scm.im.inv.IInvBillBase;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface ICostAdjustBill extends IInvBillBase
{
    public CostAdjustBillInfo getCostAdjustBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public CostAdjustBillInfo getCostAdjustBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public CostAdjustBillInfo getCostAdjustBillInfo(String oql) throws BOSException, EASBizException;
    public CostAdjustBillCollection getCostAdjustBillCollection() throws BOSException;
    public CostAdjustBillCollection getCostAdjustBillCollection(EntityViewInfo view) throws BOSException;
    public CostAdjustBillCollection getCostAdjustBillCollection(String oql) throws BOSException;
    public boolean IsJoinWithOtherBill(String[] billIDs) throws BOSException;
}