package com.kingdee.eas.auto4s.bdm.pbd.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.DataBaseController;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.auto4s.bdm.pbd.BdmPbdException;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface VehicleController extends DataBaseController
{
    public VehicleInfo getVehicleInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public VehicleInfo getVehicleInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public VehicleInfo getVehicleInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public VehicleCollection getVehicleCollection(Context ctx) throws BOSException, RemoteException;
    public VehicleCollection getVehicleCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public VehicleCollection getVehicleCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public VehicleInfo getVehicleByVin(Context ctx, String VIN) throws BOSException, BdmPbdException, EASBizException, RemoteException;
    public boolean isVehicleExist(Context ctx, String vin, String vehicleId) throws BOSException, BdmPbdException, EASBizException, RemoteException;
    public Set callBackFilterForLog(Context ctx, String orgUnitId) throws BOSException, EASBizException, RemoteException;
    public IRowSet getVehicleMiles(Context ctx, String vehicleId) throws BOSException, EASBizException, RemoteException;
    public void checkVehicleCodingRuleIsUse(Context ctx, VehicleInfo model) throws BOSException, EASBizException, RemoteException;
}