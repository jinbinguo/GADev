package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.DataBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.eas.auto4s.bdm.pbd.app.*;

public class Vehicle extends DataBase implements IVehicle
{
    public Vehicle()
    {
        super();
        registerInterface(IVehicle.class, this);
    }
    public Vehicle(Context ctx)
    {
        super(ctx);
        registerInterface(IVehicle.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("9E9FFF45");
    }
    private VehicleController getController() throws BOSException
    {
        return (VehicleController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public VehicleInfo getVehicleInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getVehicleInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public VehicleInfo getVehicleInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getVehicleInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public VehicleInfo getVehicleInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getVehicleInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public VehicleCollection getVehicleCollection() throws BOSException
    {
        try {
            return getController().getVehicleCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public VehicleCollection getVehicleCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getVehicleCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public VehicleCollection getVehicleCollection(String oql) throws BOSException
    {
        try {
            return getController().getVehicleCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getVehicleByVin-User defined method
     *@param VIN VIN
     *@return
     */
    public VehicleInfo getVehicleByVin(String VIN) throws BOSException, BdmPbdException, EASBizException
    {
        try {
            return getController().getVehicleByVin(getContext(), VIN);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *isVehicleExist-User defined method
     *@param vin vin
     *@param vehicleId vehicleId
     *@return
     */
    public boolean isVehicleExist(String vin, String vehicleId) throws BOSException, BdmPbdException, EASBizException
    {
        try {
            return getController().isVehicleExist(getContext(), vin, vehicleId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *callBackFilterForLog-User defined method
     *@param orgUnitId orgUnitId
     *@return
     */
    public Set callBackFilterForLog(String orgUnitId) throws BOSException, EASBizException
    {
        try {
            return getController().callBackFilterForLog(getContext(), orgUnitId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getVehicleMiles-User defined method
     *@param vehicleId vehicleId
     *@return
     */
    public IRowSet getVehicleMiles(String vehicleId) throws BOSException, EASBizException
    {
        try {
            return getController().getVehicleMiles(getContext(), vehicleId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *校验车辆编码规则是否启用-User defined method
     *@param model model
     */
    public void checkVehicleCodingRuleIsUse(VehicleInfo model) throws BOSException, EASBizException
    {
        try {
            getController().checkVehicleCodingRuleIsUse(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}