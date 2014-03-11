package com.kingdee.eas.ga.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairManEntryInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillEntryInfo implements Serializable 
{
    public AbstractRepairManEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairManEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.ga.rs.RepairManInfo getParent()
    {
        return (com.kingdee.eas.ga.rs.RepairManInfo)get("Parent");
    }
    public void setParent(com.kingdee.eas.ga.rs.RepairManInfo item)
    {
        put("Parent", item);
    }
    /**
     * Object: 分录 's 车辆 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo getVehicle()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo)get("vehicle");
    }
    public void setVehicle(com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo item)
    {
        put("vehicle", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("51DAC070");
    }
}