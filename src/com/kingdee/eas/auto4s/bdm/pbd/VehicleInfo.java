package com.kingdee.eas.auto4s.bdm.pbd;

import java.io.Serializable;

public class VehicleInfo extends AbstractVehicleInfo implements Serializable 
{
    public VehicleInfo()
    {
        super();
    }
    protected VehicleInfo(String pkField)
    {
        super(pkField);
    }
}