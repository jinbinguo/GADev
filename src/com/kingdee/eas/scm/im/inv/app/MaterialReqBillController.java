package com.kingdee.eas.scm.im.inv.app;

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
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.scm.im.inv.MaterialReqBillInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.scm.im.inv.MaterialReqBillCollection;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface MaterialReqBillController extends InvBillBaseController
{
    public MaterialReqBillInfo getMaterialReqBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public MaterialReqBillInfo getMaterialReqBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public MaterialReqBillInfo getMaterialReqBillInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public MaterialReqBillCollection getMaterialReqBillCollection(Context ctx) throws BOSException, RemoteException;
    public MaterialReqBillCollection getMaterialReqBillCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public MaterialReqBillCollection getMaterialReqBillCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public CostCenterOrgUnitInfo getDefaultCostCenter(Context ctx, String userID) throws BOSException, EASBizException, RemoteException;
    public Set getAdminOrgsHashSet(Context ctx, String userID) throws BOSException, EASBizException, RemoteException;
    public boolean deleteable(Context ctx, String billId) throws BOSException, EASBizException, RemoteException;
    public String getLotsByMaterial(Context ctx, String storageOrgUnitID, String materialID) throws BOSException, EASBizException, RemoteException;
    public void bachLoopAduit(Context ctx, IObjectPK[] pk) throws BOSException, EASBizException, RemoteException;
    public MaterialReqBillPriceInfoEntryCollection getPriceInfo(Context ctx, MaterialReqBillPriceInfoEntryCollection priceInfos, Map orgUnits) throws BOSException, EASBizException, RemoteException;
}