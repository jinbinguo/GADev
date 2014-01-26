package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.vo.ServerReturnInfo;

public interface ISyncDataFacade extends IBizCtrl
{
    public ServerReturnInfo syncWipBill(ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsWipBillPk) throws BOSException, EASBizException;
    public ServerReturnInfo syncTradeInquire(ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsTradeInqirePk) throws BOSException, EASBizException;
    public void autosync() throws BOSException, EASBizException;
}