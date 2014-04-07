package com.kingdee.eas.ga.syncdata.app;

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

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface SyncDataFacadeController extends BizController
{
    public ServerReturnInfo syncWipBill(Context ctx, ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsWipBillPk) throws BOSException, EASBizException, RemoteException;
    public ServerReturnInfo syncTradeInquire(Context ctx, ServiceOrgUnitInfo serviceOrgInfo, IObjectPK dmsTradeInqirePk) throws BOSException, EASBizException, RemoteException;
    public ServerReturnInfo syncPrintContent(Context ctx, ServiceOrgUnitInfo serviceOrgUnitInfo, IObjectPK dmsPrintContentPK) throws BOSException, EASBizException, RemoteException;
    public void autosync(Context ctx) throws BOSException, EASBizException, RemoteException;
}