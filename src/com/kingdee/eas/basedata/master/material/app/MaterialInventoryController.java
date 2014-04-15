package com.kingdee.eas.basedata.master.material.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.basedata.master.material.MaterialInventoryCollection;
import java.util.HashMap;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import java.util.List;
import com.kingdee.eas.basedata.master.material.MaterialInventoryInfo;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface MaterialInventoryController extends MaterialPropertyBaseController
{
    public MaterialInventoryInfo getMaterialInventoryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public MaterialInventoryInfo getMaterialInventoryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public MaterialInventoryInfo getMaterialInventoryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public MaterialInventoryCollection getMaterialInventoryCollection(Context ctx) throws BOSException, RemoteException;
    public MaterialInventoryCollection getMaterialInventoryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public MaterialInventoryCollection getMaterialInventoryCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public MaterialInventoryInfo first(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public MaterialInventoryInfo previous(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public MaterialInventoryInfo next(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public MaterialInventoryInfo last(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public void updateCheck(Context ctx, IObjectPK pk, MaterialInventoryInfo newInfo) throws BOSException, EASBizException, RemoteException;
    public MaterialInventoryInfo getInventoryInfo(Context ctx, String matid, String ouid) throws BOSException, EASBizException, RemoteException;
    public List getMaterialsExistInvInfo(Context ctx, String[] materialIDs, String inventoryID) throws BOSException, RemoteException;
    public HashMap verifyMaterialInvInfos(Context ctx, HashMap hashMap) throws BOSException, RemoteException;
}