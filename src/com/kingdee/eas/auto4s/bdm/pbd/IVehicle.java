package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import java.util.Set;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.eas.framework.IDataBase;

public interface IVehicle extends IDataBase
{
    public VehicleInfo getVehicleInfo(IObjectPK pk) throws BOSException, EASBizException;
    public VehicleInfo getVehicleInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public VehicleInfo getVehicleInfo(String oql) throws BOSException, EASBizException;
    public VehicleCollection getVehicleCollection() throws BOSException;
    public VehicleCollection getVehicleCollection(EntityViewInfo view) throws BOSException;
    public VehicleCollection getVehicleCollection(String oql) throws BOSException;
    public VehicleInfo getVehicleByVin(String VIN) throws BOSException, BdmPbdException, EASBizException;
    public boolean isVehicleExist(String vin, String vehicleId) throws BOSException, BdmPbdException, EASBizException;
    public Set callBackFilterForLog(String orgUnitId) throws BOSException, EASBizException;
    public IRowSet getVehicleMiles(String vehicleId) throws BOSException, EASBizException;
    public void checkVehicleCodingRuleIsUse(VehicleInfo model) throws BOSException, EASBizException;
}