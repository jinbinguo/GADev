package com.kingdee.eas.auto4s.rsm.rs.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.auto4s.autoframework.core.app.AutoBillBaseController;
import java.util.Map;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.auto4s.rsm.rs.RsmRsException;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface RepairWOController extends AutoBillBaseController
{
    public RepairWOCollection getRepairWOCollection(Context ctx) throws BOSException, RemoteException;
    public RepairWOCollection getRepairWOCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public RepairWOCollection getRepairWOCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public RepairWOInfo getRepairWOInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public RepairWOInfo getRepairWOInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public RepairWOInfo getRepairWOInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void uninvalid(Context ctx, RepairWOInfo model) throws BOSException, EASBizException, RemoteException;
    public void invalid(Context ctx, RepairWOInfo model) throws BOSException, EASBizException, RemoteException;
    public void assign(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void repairBreak(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void breakRestore(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void inspect(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void wash(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void workTime(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void dispatching(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void timeBooking(Context ctx, IObjectValue model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void deleteRepairWO(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void isB(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void cancelB(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void addEnter(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void adjust(Context ctx, RepairWOInfo model) throws BOSException, RemoteException;
    public void itemIssue(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void cancelAssign(Context ctx, Map cancelMap) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public boolean isAllIssue(Context ctx, RepairWOInfo model) throws BOSException, RsmRsException, EASBizException, RemoteException;
    public void unWorkTime(Context ctx, Map paramMap) throws BOSException, RemoteException;
    public void unWash(Context ctx, RepairWOInfo model) throws BOSException, RemoteException;
    public void unInspect(Context ctx, Map paramMap) throws BOSException, EASBizException, RemoteException;
    public void updateRWOVersion(Context ctx, RepairWOInfo model) throws BOSException, EASBizException, RemoteException;
    public Map getRWOFromRepairBooking(Context ctx, Map paramMap) throws BOSException, EASBizException, RemoteException;
}