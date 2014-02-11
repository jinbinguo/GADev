package com.kingdee.eas.auto4s.rsm.rs.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import java.math.BigDecimal;
import com.kingdee.eas.framework.app.CoreBillEntryBaseController;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.eas.auto4s.rsm.rs.RsmRsException;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface RepairWORWOSparepartEntryController extends CoreBillEntryBaseController
{
    public boolean exists(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public boolean exists(Context ctx, FilterInfo filter) throws BOSException, EASBizException, RemoteException;
    public boolean exists(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public IObjectPK addnew(Context ctx, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException, RemoteException;
    public void addnew(Context ctx, IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException, RemoteException;
    public void update(Context ctx, IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException, RemoteException;
    public void updatePartial(Context ctx, RepairWORWOSparepartEntryInfo model, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public void updateBigObject(Context ctx, IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException, RemoteException;
    public void delete(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] getPKList(Context ctx) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] getPKList(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] getPKList(Context ctx, FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException, RemoteException;
    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(Context ctx) throws BOSException, RemoteException;
    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public IObjectPK[] delete(Context ctx, FilterInfo filter) throws BOSException, EASBizException, RemoteException;
    public IObjectPK[] delete(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void delete(Context ctx, IObjectPK[] arrayPK) throws BOSException, EASBizException, RemoteException;
    public IRowSet getResourceId(Context ctx, String oql) throws BOSException, RemoteException;
    public void updateQty(Context ctx, BigDecimal issueQty, String entryId, BigDecimal noIssueQty, String transType, String oprateType) throws BOSException, RemoteException;
    public void checkQty(Context ctx, BigDecimal qty, String entryID, String transType, String oprateType) throws BOSException, RsmRsException, RemoteException;
}